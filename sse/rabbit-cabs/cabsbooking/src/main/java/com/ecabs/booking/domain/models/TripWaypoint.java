package com.ecabs.booking.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;
/*
  author kamochejackson@gmail.com created on 1/26/20
*/
@Entity
@Table(name = "TripWaypoint")
@Setter @Getter @NoArgsConstructor
public class TripWaypoint {
    @Id
    private UUID tripWayPointId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    private Booking booking;
    private Boolean lastStop;
    private String locality;
    private Double lat;
    private Double lng;
    private Instant tripWayPointTimestamp;
}
