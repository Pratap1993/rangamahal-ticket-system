package com.hashedin.rangmahal.model.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Pratap Bhanu
 *
 */
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserRequestDto implements Serializable {

	private static final long serialVersionUID = 5840153489982491136L;

	private Integer userId;

	private String email;

	private String fullName;

	private String phone;

}
