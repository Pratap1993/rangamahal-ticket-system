package com.hashedin.rangmahal.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hashedin.rangmahal.config.ExecutorServiceProvider;
import com.hashedin.rangmahal.config.PaymentClass;
import com.hashedin.rangmahal.entity.BookingEntity;
import com.hashedin.rangmahal.entity.SeatEntity;
import com.hashedin.rangmahal.entity.UserEntity;
import com.hashedin.rangmahal.enums.PaymentStatusEnum;
import com.hashedin.rangmahal.exceptions.BookingNotAcceptedException;
import com.hashedin.rangmahal.exceptions.ErrorMessages;
import com.hashedin.rangmahal.exceptions.SeatNotAvailableException;
import com.hashedin.rangmahal.model.request.AvailableSeatsRequestDto;
import com.hashedin.rangmahal.model.request.BookingRequestDto;
import com.hashedin.rangmahal.model.request.SeatRequestDto;
import com.hashedin.rangmahal.model.request.UserRequestDto;
import com.hashedin.rangmahal.model.response.AvailableSeatsResponseDto;
import com.hashedin.rangmahal.model.response.BookingResponseDto;
import com.hashedin.rangmahal.repository.BookingRepository;
import com.hashedin.rangmahal.repository.SeatRepository;
import com.hashedin.rangmahal.repository.UserRepository;
import com.hashedin.rangmahal.utils.DateTimeUtil;
import com.hashedin.rangmahal.utils.SeatUtils;
import com.hashedin.rangmahal.utils.ShowTimeUtil;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Pratap Bhanu
 *
 */
@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

	@NonNull
	private SeatRepository seatRepository;

	@NonNull
	private UserRepository userRepository;

	@NonNull
	private BookingRepository bookingRepository;

	private ExecutorService executerService = ExecutorServiceProvider.getInstance().getExecutorService();

	static final Logger logger = LoggerFactory.getLogger(SeatServiceImpl.class);

	@Override
	public AvailableSeatsResponseDto findAvailableSeats(AvailableSeatsRequestDto dto) {
		logger.info("Returning available seats for : {}", dto.getShowTime());
		Boolean isBookingOpen = ShowTimeUtil.isBookingOpen(dto.getShowTime());
		if (!isBookingOpen)
			throw new BookingNotAcceptedException(ErrorMessages.BOOKING_NOT_OPENED.getErrorMessage());
		List<SeatRequestDto> returnList = new ArrayList<SeatRequestDto>();
		List<SeatEntity> bookedSeats = seatRepository.findBookedSeatsForDateAndTime(dto.getBookingDate(),
				dto.getShowTime());
		List<SeatEntity> allSeats = seatRepository.findAll();
		allSeats.removeAll(bookedSeats);
		ModelMapper mapper = new ModelMapper();
		allSeats.forEach(seat -> {
			SeatRequestDto seatDto = mapper.map(seat, SeatRequestDto.class);
			returnList.add(seatDto);
		});
		AvailableSeatsResponseDto returnType = new AvailableSeatsResponseDto();
		returnType.setSeatList(returnList);
		return returnType;
	}

	@Override
	public BookingResponseDto newBooking(BookingRequestDto bookingRequestDto) {
		logger.info("Proceeding to new booking !!!");
		Boolean isBookingOpen = ShowTimeUtil.isBookingOpen(bookingRequestDto.getShowTime().toUpperCase());
		if (!isBookingOpen)
			throw new BookingNotAcceptedException(ErrorMessages.BOOKING_NOT_OPENED.getErrorMessage());
		LocalDateTime bookingDate = DateTimeUtil.getFromString(bookingRequestDto.getBookingDateTime());
		List<SeatEntity> seatsSelected = getSelectedSeats(bookingRequestDto);
		synchronized (this) {
			BookingEntity availableEntity = bookingRepository.findIfAvailable(bookingDate,
					bookingRequestDto.getShowTime(), seatsSelected);
			if (availableEntity != null)
				throw new SeatNotAvailableException(ErrorMessages.SEAT_NOT_AVAILABLE.getErrorMessage());
		}
		ModelMapper mapper = new ModelMapper();
		BookingEntity entity = new BookingEntity();
		entity.setBookingDate(bookingDate);
		entity.setBookingRefference(SeatUtils.getBookingReferrence(bookingRequestDto));
		entity.setSeats(seatsSelected);
		entity.setShowTime(bookingRequestDto.getShowTime());
		entity.setTotalAmount(totalAmount(seatsSelected));
		entity.setUserEntity(getUserByEmail(bookingRequestDto.getUser()));
		BookingEntity savedEntity = bookingRepository.save(entity);
		String bookingStatus = null;
		Callable<String> task = new PaymentClass(savedEntity);
		Future<String> future = executerService.submit(task);
		try {
			bookingStatus = future.get(3, TimeUnit.SECONDS);
		} catch (TimeoutException ex) {
			logger.error("Canceling booking as payment took more than 3 minutes !!!");
			future.cancel(true);
			bookingStatus = PaymentStatusEnum.FAILED.name();
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
		}
		if (bookingStatus != null) {
			savedEntity.setPaymentStatus(bookingStatus);
			bookingRepository.save(savedEntity);
		}
		BookingResponseDto returnValue = new BookingResponseDto();
		returnValue.setBookingReferrence(savedEntity.getBookingRefference());
		returnValue.setBookingStatus(bookingStatus != null ? bookingStatus : PaymentStatusEnum.FAILED.name());
		seatsSelected.forEach(seat -> {
			SeatRequestDto seatDto = mapper.map(seat, SeatRequestDto.class);
			returnValue.getSeats().add(seatDto);
		});
		return returnValue;
	}

	private List<SeatEntity> getSelectedSeats(BookingRequestDto bookingRequestDto) {
		List<SeatEntity> entityList = new ArrayList<>();
		bookingRequestDto.getSeats().forEach(seat -> {
			Optional<SeatEntity> entity = seatRepository.findBySeatNameAndSeatType(seat.getSeatName(),
					seat.getSeatType().toUpperCase());
			if (entity.isPresent()) {
				entityList.add(entity.get());
			}
		});
		return entityList;
	}

	private Integer totalAmount(List<SeatEntity> seatsSelected) {
		Integer returnValue = 0;
		for (SeatEntity amount : seatsSelected) {
			returnValue += amount.getSeatPrice();
		}
		return returnValue;
	}

	private UserEntity getUserByEmail(UserRequestDto dto) {
		UserEntity user = null;
		Optional<UserEntity> entity = userRepository.findByEmail(dto.getEmail());
		if (entity.isPresent()) {
			user = entity.get();
		} else {
			UserEntity newUser = new UserEntity();
			newUser.setEmail(dto.getEmail());
			newUser.setFullName(dto.getFullName());
			newUser.setPhone(dto.getPhone());
			user = userRepository.save(newUser);
		}
		return user;
	}

}
