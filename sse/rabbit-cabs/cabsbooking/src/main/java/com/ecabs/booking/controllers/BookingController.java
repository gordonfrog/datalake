package com.ecabs.booking.controllers;

import com.ecabs.booking.domain.messages.CreateBooking;
import com.ecabs.booking.domain.messages.DeleteBooking;
import com.ecabs.booking.domain.messages.UpdateBooking;
import com.ecabs.booking.domain.models.Booking;
import com.ecabs.booking.domain.repository.BookingRepository;
import com.ecabs.booking.exception.ResourceNotFoundException;
import com.ecabs.booking.service.BookingProducerService;
import com.ecabs.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.DateTimeException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static com.ecabs.booking.common.AbstractUtil.convertToJsonString;
import static com.ecabs.booking.common.AbstractUtil.formatPickupTime;
import static com.ecabs.booking.common.CommonUtils.*;

/*
  author kamochejackson@gmail.com created on 1/26/20
*/
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class BookingController {


    @Autowired
    BookingService bookingService;

    @Autowired
    BookingProducerService bookingProducerService;


    @GetMapping("/bookings")
    public List<Booking> getAllBooking() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable(value = "id") UUID bookingId) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(bookingService.findById(bookingId));
    }

    @PostMapping("/bookings")
    public Booking createBooking(@Valid @RequestBody CreateBooking booking) {
        return bookingService.createBooking(booking);
    }

    @PutMapping("bookings/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable(value = "id") UUID bookingId, @RequestBody CreateBooking bookingDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(bookingService.updateBooking(bookingId,bookingDetails));
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable(value = "id") UUID bookingId) throws ResourceNotFoundException {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.ok("deleted successfully");
    }

    @PostMapping("/async/bookings")
    public ResponseEntity<?> createBookingAsyc(@Valid @RequestBody CreateBooking booking) {
        try {
            formatPickupTime(booking.getPickupTime());
            bookingProducerService.sendMessage(BOOKING_ADD_QUEUE_KEY, booking);
        } catch (DateTimeException e) {
            return new ResponseEntity<>("Invalid pickup time. Valid format yyyy-MM-dd HH:mm:ss", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/async/bookings/{id}")
    public ResponseEntity<?> updateBookingAsync(@PathVariable(value = "id") UUID bookingId, @RequestBody CreateBooking bookingDetails) {
        try {
            formatPickupTime(bookingDetails.getPickupTime());
            bookingProducerService.sendMessage(BOOKING_EDIT_QUEUE_KEY, new UpdateBooking(bookingId, bookingDetails));
        } catch (DateTimeException e) {
            return new ResponseEntity<>("Invalid pickup time. Valid format yyyy-MM-dd HH:mm:ss", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/async/bookings/{id}")
    public ResponseEntity<?> deleteBookingAsync(@PathVariable(value = "id") UUID bookingId) {
        bookingProducerService.sendMessage(BOOKING_DELETE_QUEUE_KEY, new DeleteBooking(bookingId));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
