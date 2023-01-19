package com.ecabs.booking.domain.messages;

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
@Getter @Setter @NoArgsConstructor
public class DeleteBooking implements Serializable {

    private static final long serialVersionUID = -6726199441033980663L;
    UUID uuid;

    public DeleteBooking(UUID uuid) {
        this.uuid = uuid;
    }
}
