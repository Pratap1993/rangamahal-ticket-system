package com.hashedin.rangmahal.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hashedin.rangmahal.entity.UserEntity;
import com.hashedin.rangmahal.model.request.UserRequestDto;
import com.hashedin.rangmahal.repository.UserRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Pratap Bhanu
 *
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@NonNull
	private UserRepository userRepository;

	@Override
	public void saveUserFromDto(UserRequestDto dto) {
		ModelMapper mapper = new ModelMapper();
		UserEntity entity = mapper.map(dto, UserEntity.class);
		userRepository.save(entity);
	}

	@Override
	public UserEntity findUserByEmail(String email) {

		return null;
	}

}
