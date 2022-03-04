package com.chagu.rangmahal.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.chagu.rangmahal.entity.BookingEntity;
import com.chagu.rangmahal.entity.SeatEntity;
import com.chagu.rangmahal.enums.PaymentStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Pratap Bhanu
 *
 */
public class BookingRepositoryCustomImpl implements BookingRepositoryCustom {

	static final Logger logger = LoggerFactory.getLogger(BookingRepositoryCustomImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public BookingEntity findIfAvailable(LocalDateTime bookingDate, String showTime, List<SeatEntity> seatsSelected) {
		logger.info("Checking if selected seats are available for : {}", showTime);
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BookingEntity> query = builder.createQuery(BookingEntity.class);
		Root<BookingEntity> root = query.from(BookingEntity.class);
		Join<BookingEntity, SeatEntity> seatRoot = root.join("seats", JoinType.INNER);
		List<Predicate> predicateList = new ArrayList<>();
		In<Integer> in = builder.in(seatRoot.get("seatId"));
		seatsSelected.forEach(seat -> in.value(seat.getSeatId()));
		predicateList.add(in);
		predicateList.add(builder.equal(root.get("bookingDate"), bookingDate));
		predicateList.add(builder.equal(root.get("showTime"), showTime));
		predicateList.add(builder.equal(root.get("paymentStatus"), PaymentStatusEnum.SUCCESS.name()));
		query.select(root).where(builder.and(predicateList.toArray(new Predicate[predicateList.size()])));
		BookingEntity entity = null;
		try {
			entity = entityManager.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
		} catch (NonUniqueResultException e) {
			logger.error("NonUniqueResultException occured in findByEmailForDateAndTime() for : {}",
					bookingDate + ", " + showTime + ". ");
		}
		return entity;
	}

}
