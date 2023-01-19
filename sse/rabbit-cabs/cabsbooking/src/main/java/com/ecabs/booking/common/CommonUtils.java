package com.ecabs.booking.common;

import org.springframework.stereotype.Component;

/*
  author kamochejackson@gmail.com created on 1/26/20 
*/
@Component
public class CommonUtils {

    public static final String BOOKING_ADD_QUEUE_KEY="messages.BookingAddQueueKey";
    public static final String BOOKING_EDIT_QUEUE_KEY = "messages.BookingEditQueueKey";
    public static final String BOOKING_DELETE_QUEUE_KEY = "messages.BookingDeleteQueueKey";
    public static final String MESSAGE_EXCHANGE = "MessageExchange";
    public static final String BOOKING_ADD_QUEUE = "BookingAddQueue";
    public static final String BOOKING_EDIT_QUEUE = "BookingEditQueue";
    public static final String BOOKING_DELETE_QUEUE = "BookingDeleteQueue";
    public static final String MESSAGE_AUDIT_QUEUE = "MessageAuditQueue";
}
