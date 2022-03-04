package com.chagu.rangmahal.config;

import java.util.concurrent.Callable;

import com.chagu.rangmahal.entity.BookingEntity;
import com.chagu.rangmahal.enums.PaymentStatusEnum;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Pratap Bhanu
 *
 */
@RequiredArgsConstructor
public class PaymentClass implements Callable<String> {

	@NonNull
	private BookingEntity bookingEntity;

	@Override
	public String call() throws Exception {
		// Here we can customise out payment status stuff
		String paymentStatus = PaymentStatusEnum.SUCCESS.name();
		return paymentStatus;
	}	
}
