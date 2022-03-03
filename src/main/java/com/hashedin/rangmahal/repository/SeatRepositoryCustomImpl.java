package com.hashedin.rangmahal.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hashedin.rangmahal.entity.BookingEntity;
import com.hashedin.rangmahal.entity.SeatEntity;
import com.hashedin.rangmahal.enums.PaymentStatusEnum;

/**
 * @author Pratap Bhanu
 *
 */
public class SeatRepositoryCustomImpl implements SeatRepositoryCustom {

	static final Logger logger = LoggerFactory.getLogger(SeatRepositoryCustomImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<SeatEntity> findBookedSeatsForDateAndTime(LocalDateTime bookingDate, String showTime) {
		logger.info("Returning list of booked seats for : {}", showTime);
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<SeatEntity> query = builder.createQuery(SeatEntity.class);
		Root<BookingEntity> root = query.from(BookingEntity.class);
		Join<BookingEntity, SeatEntity> seatRoot = root.join("seats", JoinType.INNER);
		List<Predicate> predicateList = new ArrayList<>();
		predicateList.add(builder.equal(root.get("bookingDate"), bookingDate));
		predicateList.add(builder.equal(root.get("showTime"), showTime));
		predicateList.add(builder.and(builder.equal(root.get("paymentStatus"), PaymentStatusEnum.FAILED.name()),
				builder.lessThan(root.get("bookingDate"), LocalDateTime.now().minusMinutes(3))));
		query.select(seatRoot).where(builder.and(predicateList.toArray(new Predicate[predicateList.size()])));
		List<SeatEntity> entities = entityManager.createQuery(query).getResultList();
		return entities;
	}

}
