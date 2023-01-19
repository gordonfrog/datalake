package com.ecabs.booking.service;

import com.ecabs.booking.domain.messages.CreateBooking;
import com.ecabs.booking.domain.models.Booking;
import com.ecabs.booking.domain.repository.BookingRepository;
import com.ecabs.booking.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static com.ecabs.booking.common.AbstractUtil.formatPickupTime;

/**
 * * Created by jackson.kamoche@sportpesa.com on Jan, 2020
 **/
@Slf4j
@Service
public class BookingService {
    private BookingRepository bookingRepository;


    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAllBookings(){
        return (List<Booking>) bookingRepository.findAll();
    }

    public Booking findById(UUID bookingId) throws ResourceNotFoundException {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("FAILED TO RETRIEVE Booking:: REASON id :: " + bookingId + " not found"));
        return booking;
    }

    public Booking createBooking(CreateBooking createBooking) {
        Booking booking = new Booking();
        booking.setPassengerName(createBooking.getPassengerName());
        booking.setBookingId(UUID.randomUUID());
        booking.setPassengerContactNumber(createBooking.getPassengerContactNumber());
        booking.setPickupTime(formatPickupTime(createBooking.getPickupTime()));
        booking.setAsap(createBooking.getAsap());
        booking.setWaitingTime(createBooking.getWaitingTime());
        booking.setNoOfPassengers(createBooking.getNoOfPassengers());
        booking.setPrice(createBooking.getPrice());
        booking.setRating(createBooking.getRating());
        booking.setTripWayPoints(createBooking.getTripWayPoints());
        booking.setLastModifiedOn(Instant.now());
        bookingRepository.save(booking);
        return booking;
    }

    public Booking updateBooking(UUID bookingId, CreateBooking createBooking) throws ResourceNotFoundException {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("FAILED TO UPDATE Booking:: REASON id :: " + bookingId + " not found"));
        booking.setPassengerName(createBooking.getPassengerName());
        booking.setPassengerContactNumber(createBooking.getPassengerContactNumber());
        booking.setPickupTime(formatPickupTime(createBooking.getPickupTime()));
        booking.setAsap(createBooking.getAsap());
        booking.setWaitingTime(createBooking.getWaitingTime());
        booking.setNoOfPassengers(createBooking.getNoOfPassengers());
        booking.setPrice(createBooking.getPrice());
        booking.setRating(createBooking.getRating());
        booking.setTripWayPoints(createBooking.getTripWayPoints());
        booking.setLastModifiedOn(Instant.now());
        return bookingRepository.save(booking);
    }

    public void deleteBooking(UUID bookingId) throws ResourceNotFoundException {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException("FAILED TO DELETE Booking:: REASON id :: " + bookingId + " not found"));
        bookingRepository.delete(booking);
    }
}
