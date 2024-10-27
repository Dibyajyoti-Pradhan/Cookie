package com.dibyojyoti.cookie.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.dibyojyoti.cookie.model.Cookie;
import com.dibyojyoti.cookie.model.CookieLog;

public class CookieLogParser extends LogParser<CookieLog> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    @Override
    public CookieLog parse(String record) {
        String[] parts = record.split(",");
        Cookie cookie = new Cookie(parts[0]);
        LocalDateTime timestamp = LocalDateTime.parse(parts[1], FORMATTER);
        return new CookieLog(cookie, timestamp);
    }
}
