package com.ecabs.booking.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/*
  author kamochejackson@gmail.com created on 1/26/20
*/
@Entity
@Table(name = "booking")
@Getter @Setter @NoArgsConstructor
public class Booking {
    @Id
    private UUID bookingId;
    private String passengerName;
    private String passengerContactNumber;
    private OffsetDateTime pickupTime;
    private Boolean asap = true;
    private Integer waitingTime;
    private Integer noOfPassengers;
    private BigDecimal price;
    private Integer rating;
    @CreationTimestamp
    private Instant createdOn;
    private Instant lastModifiedOn;
    @OneToMany(mappedBy = "booking", cascade = {CascadeType.ALL})
    private List<TripWaypoint> tripWayPoints;


}
