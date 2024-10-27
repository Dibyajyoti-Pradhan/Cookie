package com.dibyojyoti.cookie.model;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CookieLogTest {

    @Test
    public void testGetCookie() {
        Cookie cookie = new Cookie("AtY0laUfhglK3lC7");
        LocalDateTime timestamp = LocalDateTime.parse("2018-12-09T14:19:00");
        CookieLog log = new CookieLog(cookie, timestamp);

        assertEquals(cookie, log.getCookie(), "Should return the correct Cookie object.");
        assertEquals(timestamp, log.getTimestamp(), "Timestamp should match the constructor input.");
    }
}
