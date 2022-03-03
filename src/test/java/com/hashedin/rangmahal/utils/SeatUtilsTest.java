package com.hashedin.rangmahal.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import com.hashedin.rangmahal.enums.SeatNamesEnum;
import com.hashedin.rangmahal.enums.SeatTypeEnum;
import com.hashedin.rangmahal.model.request.SeatRequestDto;

class SeatUtilsTest {

	private List<String> sampleSeatNames() {
		List<String> seatNames = Stream.of(SeatNamesEnum.values()).map(SeatNamesEnum::name)
				.collect(Collectors.toList());
		return seatNames;
	}

	@Test
	void testGetNewSetOfSeats() {
		List<SeatRequestDto> entities = SeatUtils.getNewSetOfSeats();
		assertEquals(225, entities.size(), "It should retun 225 number of seats");
	}

	@Test
	void testGetNewSilverSeats() {
		List<SeatRequestDto> entities = SeatUtils.getNewSilverSeats(sampleSeatNames());
		assertEquals(100, entities.size(), "It should retun 100 number of seats");
		assertEquals(SeatTypeEnum.SILVER.name(), entities.get(0).getSeatType());
	}

}
