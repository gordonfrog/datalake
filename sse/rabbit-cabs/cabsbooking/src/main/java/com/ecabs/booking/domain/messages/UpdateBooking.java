package com.ecabs.booking.domain.messages;

import com.ecabs.booking.domain.models.Booking;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

/*
  author kamochejackson@gmail.com created on 1/27/20 
*/
@ToString
@NoArgsConstructor
@Getter
@Setter
public class UpdateBooking implements Serializable {
    UUID uuid;
    CreateBooking booking;

    public UpdateBooking(UUID uuid, CreateBooking booking) {
        this.uuid = uuid;
        this.booking = booking;
    }
}
