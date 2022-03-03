package com.hashedin.rangmahal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hashedin.rangmahal.enums.PaymentStatusEnum;
import com.hashedin.rangmahal.model.request.BookingRequestDto;
import com.hashedin.rangmahal.model.request.SeatRequestDto;
import com.hashedin.rangmahal.model.request.UserRequestDto;
import com.hashedin.rangmahal.model.response.AvailableSeatsResponseDto;
import com.hashedin.rangmahal.model.response.BookingResponseDto;
import com.hashedin.rangmahal.service.SeatService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Pratap Bhanu
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = BookingController.class)
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeatService seatService;

    List<SeatRequestDto> seatList = Arrays.asList(new SeatRequestDto(1, "1A", "SILVER", 250),
            new SeatRequestDto(2, "1B", "SILVER", 250), new SeatRequestDto(3, "1C", "SILVER", 250));

    AvailableSeatsResponseDto availableSeat = new AvailableSeatsResponseDto(seatList);

    BookingResponseDto bookingResponse = new BookingResponseDto("RM130420MOR001", PaymentStatusEnum.SUCCESS.name(),
            seatList);

    BookingRequestDto bookingRequest = new BookingRequestDto("2020-04-13 15:00",
            new UserRequestDto(null, "chagu@gmail.com", "Pratap Bhanu", "9113516428"), seatList, "MATINEE");

    @Test
    public void testGetAvailableSeats() throws Exception {
        Mockito.when(seatService.findAvailableSeats(Mockito.any())).thenReturn(availableSeat);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/booking/2020-04-21 12:00/MORNING")
                .characterEncoding("UTF-8").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        ObjectMapper mapper = new ObjectMapper();
        String expected = mapper.writeValueAsString(availableSeat);
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void testNewBooking() throws Exception {
        Mockito.when(seatService.newBooking(Mockito.any())).thenReturn(bookingResponse);
        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(bookingRequest);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/booking/newBooking")
                .accept(MediaType.APPLICATION_JSON).content(payload).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        String returnExpected = mapper.writeValueAsString(bookingResponse);
        JSONAssert.assertEquals(returnExpected, result.getResponse().getContentAsString(), false);
    }

}
