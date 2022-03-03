package com.hashedin.rangmahal.service;

import com.hashedin.rangmahal.entity.UserEntity;
import com.hashedin.rangmahal.model.request.UserRequestDto;

/**
 * @author Pratap Bhanu
 *
 */
public interface UserService {

	void saveUserFromDto(UserRequestDto dto);

	UserEntity findUserByEmail(String email);

}
