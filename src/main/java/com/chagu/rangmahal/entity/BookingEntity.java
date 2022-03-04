package com.chagu.rangmahal.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Pratap Bhanu
 *
 */
@Entity
@Table(name = "booking")
@NoArgsConstructor
@Getter
@Setter
public class BookingEntity implements Serializable {

	private static final long serialVersionUID = -6865280446162715458L;

	@Id
	@GeneratedValue
	@Column(name = "booking_id")
	private Integer bookingId;

	@Column(name = "booking_refference", unique = true)
	private String bookingRefference;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity userEntity;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "booking_seats", joinColumns = { @JoinColumn(name = "booking_id") }, inverseJoinColumns = {
			@JoinColumn(name = "seat_id") })
	private List<SeatEntity> seats = new ArrayList<SeatEntity>();

	@Column(name = "booking_date")
	private LocalDateTime bookingDate;

	@Column(name = "show_time")
	private String showTime;

	@Column(name = "total_amount")
	private Integer totalAmount;

	@Column(name = "payment_status")
	private String paymentStatus;

}
