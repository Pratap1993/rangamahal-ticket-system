package com.hashedin.rangmahal.config;

import java.util.concurrent.Callable;

import com.hashedin.rangmahal.entity.BookingEntity;
import com.hashedin.rangmahal.enums.PaymentStatusEnum;

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
