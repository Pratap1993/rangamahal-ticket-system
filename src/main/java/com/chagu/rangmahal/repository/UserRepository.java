package com.chagu.rangmahal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chagu.rangmahal.entity.UserEntity;

/**
 * @author Pratap Bhanu
 *
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	Optional<UserEntity> findByEmail(String email);

}
