package com.hashedin.rangmahal.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.hashedin.rangmahal.enums.SeatNamesEnum;
import com.hashedin.rangmahal.enums.SeatTypeEnum;
import com.hashedin.rangmahal.model.request.BookingRequestDto;
import com.hashedin.rangmahal.model.request.SeatRequestDto;

/**
 * @author Pratap Bhanu
 *
 */
public class SeatUtils {

	public static List<SeatRequestDto> getNewSetOfSeats() {
		List<String> seatNames = Stream.of(SeatNamesEnum.values()).map(SeatNamesEnum::name)
				.collect(Collectors.toList());
		List<SeatRequestDto> returnType = new ArrayList<>();
		returnType.addAll(getNewSilverSeats(seatNames));
		returnType.addAll(getNewGoldSeats(seatNames));
		returnType.addAll(getNewDiamondSeats(seatNames));
		return returnType;
	}

	public static List<SeatRequestDto> getNewSilverSeats(List<String> seatNames) {
		List<SeatRequestDto> returnType = new ArrayList<>();
		int silverSeatPrice = 250;
		int silverRows = 4;
		String seatType = SeatTypeEnum.SILVER.name();
		for (int i = 1; i < silverRows + 1; i++) {
			for (String str : seatNames) {
				SeatRequestDto dto = new SeatRequestDto();
				dto.setSeatType(seatType);
				dto.setSeatPrice(silverSeatPrice);
				dto.setSeatName(i + str);
				returnType.add(dto);
			}
		}
		return returnType;
	}

	public static List<SeatRequestDto> getNewGoldSeats(List<String> seatNames) {
		List<SeatRequestDto> returnType = new ArrayList<>();
		int goldSeatPrice = 500;
		int goldRows = 3;
		String seatType = SeatTypeEnum.GOLD.name();
		for (int i = 1; i < goldRows + 1; i++) {
			for (String str : seatNames) {
				SeatRequestDto dto = new SeatRequestDto();
				dto.setSeatType(seatType);
				dto.setSeatPrice(goldSeatPrice);
				dto.setSeatName(i + str);
				returnType.add(dto);
			}
		}
		return returnType;
	}

	public static List<SeatRequestDto> getNewDiamondSeats(List<String> seatNames) {
		List<SeatRequestDto> returnType = new ArrayList<>();
		int diamondSeatPrice = 750;
		int diamondRows = 2;
		String seatType = SeatTypeEnum.DIAMOND.name();
		for (int i = 1; i < diamondRows + 1; i++) {
			for (String str : seatNames) {
				SeatRequestDto dto = new SeatRequestDto();
				dto.setSeatType(seatType);
				dto.setSeatPrice(diamondSeatPrice);
				dto.setSeatName(i + str);
				returnType.add(dto);
			}
		}
		return returnType;
	}
	
	public static String getBookingReferrence(BookingRequestDto bookingResponseDto) {
		int randomNumber = new Random().nextInt(1000);
		StringBuilder sb = new StringBuilder();
		sb.append("RM");
		sb.append(bookingResponseDto.getBookingDateTime().substring(8, 10));
		sb.append(bookingResponseDto.getBookingDateTime().substring(5, 7));
		sb.append(bookingResponseDto.getBookingDateTime().substring(2, 4));
		sb.append(bookingResponseDto.getShowTime().substring(0, 3));
		sb.append(randomNumber);
		return sb.toString();
	}
}
