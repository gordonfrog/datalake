package com.ecabs.booking;

import com.ecabs.booking.domain.models.Booking;
import com.ecabs.booking.domain.repository.BookingRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class BookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingApplication.class, args);
    }

    @Component
    class DemoCommandLineRunner implements CommandLineRunner {

        @Autowired
        BookingRepository bookingRepository;

        @Override
        public void run(String... args) throws Exception {
            Booking booking = new Booking();
            booking.setBookingId(UUID.randomUUID());
            booking.setNoOfPassengers(1);
            booking.setPassengerContactNumber("254726646217");
            booking.setPassengerName("Kamoche Jackson");
            booking.setPickupTime(OffsetDateTime.now());
            booking.setPrice(BigDecimal.valueOf(100.00));
            booking.setRating(10);
            booking.setWaitingTime(20);
            booking.setTripWayPoints(new ArrayList<>());

            bookingRepository.save(booking);

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            String json = gson.toJson(booking);
            System.out.println(json);


        }
    }

}
