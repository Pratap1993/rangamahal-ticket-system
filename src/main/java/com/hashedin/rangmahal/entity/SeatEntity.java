package com.hashedin.rangmahal.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Pratap Bhanu
 *
 */
@Entity
@Table(name = "seat")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class SeatEntity implements Serializable {

	private static final long serialVersionUID = -8479097399113960108L;

	@Id
	@GeneratedValue
	@Column(name = "seat_id")
	private Integer seatId;

	@Column(name = "seat_name")
	private String seatName;

	@Column(name = "seat_type")
	private String seatType;

	@Column(name = "seat_price")
	private Integer seatPrice;

}
