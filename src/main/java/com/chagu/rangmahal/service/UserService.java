package com.chagu.rangmahal.service;

import com.chagu.rangmahal.entity.UserEntity;
import com.chagu.rangmahal.model.request.UserRequestDto;

/**
 * @author Pratap Bhanu
 *
 */
public interface UserService {

	void saveUserFromDto(UserRequestDto dto);

	UserEntity findUserByEmail(String email);

}
