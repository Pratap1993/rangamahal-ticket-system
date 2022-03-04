package com.chagu.rangmahal.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Pratap Bhanu
 *
 */
@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 442487168003718408L;

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer userId;

	@Column(name = "full_name")
	@Size(min = 4, max = 50)
	private String fullName;

	@Column(name = "email", unique = true)
	@Email(message = "Email shold be valid !!!")
	private String email;

	@Column(name = "phone", unique = true)
	private String phone;

}
