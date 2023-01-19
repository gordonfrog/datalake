package com.ecabs.booking.domain.repository;

/*
  author kamochejackson@gmail.com created on 1/26/20
*/

import com.ecabs.booking.domain.models.Booking;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BookingRepository extends CrudRepository<Booking, UUID> {
}
