package com.hashedin.rangmahal.config;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hashedin.rangmahal.entity.SeatEntity;
import com.hashedin.rangmahal.model.request.SeatRequestDto;
import com.hashedin.rangmahal.repository.SeatRepository;
import com.hashedin.rangmahal.utils.SeatUtils;

/**
 * @author Pratap Bhanu
 *
 */
@Component
public class DatabaseLoader implements CommandLineRunner {

	private SeatRepository seatRepository;

	public DatabaseLoader(SeatRepository seatRepository) {
		this.seatRepository = seatRepository;
	}

	@Override
	public void run(String... args) {
		saveSeats();
	}

	private void saveSeats() {
		List<SeatRequestDto> seats = SeatUtils.getNewSetOfSeats();
		List<SeatEntity> entityList = new ArrayList<SeatEntity>();
		ModelMapper mapper = new ModelMapper();
		seats.forEach(seat -> {
			SeatEntity entity = mapper.map(seat, SeatEntity.class);
			entityList.add(entity);
		});
		seatRepository.saveAll(entityList);
	}

}
