package com.ecabs.booking.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * * Created by jackson.kamoche@sportpesa.com on Jan, 2020
 **/
public class AbstractUtil {

    public static String convertToJsonString(Object o) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.toJson(o);
    }

    public static OffsetDateTime formatPickupTime(String pickupTime) throws DateTimeParseException {
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZoneOffset offset = OffsetDateTime.now().getOffset();
        LocalDateTime date = LocalDateTime.parse(pickupTime, DATE_TIME_FORMATTER);
        OffsetDateTime offsetDateTime = date.atOffset(offset);
        return offsetDateTime;
    }
}
