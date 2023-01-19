package com.ecabs.booking.domain.messages;

import com.ecabs.booking.domain.models.Booking;
import com.ecabs.booking.domain.models.TripWaypoint;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/*
  author kamochejackson@gmail.com created on 1/27/20 
*/
@ToString
@NoArgsConstructor
@Getter @Setter
public class CreateBooking implements Serializable {
    @NotNull
    private String passengerName;
    private String passengerContactNumber;
    @NotNull
    private String pickupTime;
    private Boolean asap = true;
    private Integer waitingTime;
    private Integer noOfPassengers;
    private BigDecimal price;
    private Integer rating;
    private List<TripWaypoint> tripWayPoints;
}
