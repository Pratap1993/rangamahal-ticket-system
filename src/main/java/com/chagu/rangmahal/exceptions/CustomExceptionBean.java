package com.chagu.rangmahal.exceptions;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Pratap Bhanu
 *
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class CustomExceptionBean {

	@NonNull
	private Date timeStamp;

	@NonNull
	private String message;

}
