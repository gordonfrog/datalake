package com.ecabs.booking.service;

import com.ecabs.booking.domain.messages.CreateBooking;
import com.ecabs.booking.domain.messages.DeleteBooking;
import com.ecabs.booking.domain.messages.UpdateBooking;
import com.ecabs.booking.domain.models.Booking;
import com.ecabs.booking.domain.repository.BookingRepository;
import com.ecabs.booking.exception.ResourceNotFoundException;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static com.ecabs.booking.common.CommonUtils.*;

/*
  author kamochejackson@gmail.com created on 1/26/20
*/
@Service
@Slf4j
public class BookingConsumerService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingService bookingService;

    @RabbitListener(queues = MESSAGE_AUDIT_QUEUE)
    public void receiveAuditMessage(Message message){
        log.info("AUDIT MESSAGE :: {}  message :: {}", message.getMessageProperties().getReceivedRoutingKey(), message.toString());

    }

    @RabbitListener(queues = BOOKING_ADD_QUEUE)
    public void receiveAddBookingMessage(final CreateBooking createBooking){
        bookingService.createBooking(createBooking);
//        bookingRepository.save(createBooking.);
        log.info("ReceivedAddBookingMessage {} and created it successfully", createBooking.toString());
    }

    @RabbitListener(queues = BOOKING_EDIT_QUEUE)
    public void receiveEditBookingMessage(final UpdateBooking updateBooking){
        try {
            bookingService.updateBooking(updateBooking.getUuid(),updateBooking.getBooking());
            log.info("ReceivedEditBookingMessage {} and updated it successfully", updateBooking.toString());
        }catch (ResourceNotFoundException e){
            log.trace(e.getMessage());
        }catch (Exception e){
            log.error(e.getMessage());
        }

    }

    @RabbitListener(queues = BOOKING_DELETE_QUEUE)
    public void receiveDeleteBookingMessage(DeleteBooking deleteBooking){
        try {
            bookingService.deleteBooking(deleteBooking.getUuid());
            log.info("ReceivedDeleteBookingMessage {} and deleted it successfully", deleteBooking.toString());
        } catch (ResourceNotFoundException e) {
            log.trace(e.getMessage());
        } catch (Exception e){
            log.error(e.getLocalizedMessage());
        }

    }
}
