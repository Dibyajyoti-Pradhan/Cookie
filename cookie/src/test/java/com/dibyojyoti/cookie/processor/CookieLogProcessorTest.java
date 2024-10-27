package com.dibyojyoti.cookie.processor;

import com.dibyojyoti.cookie.model.Cookie;
import com.dibyojyoti.cookie.model.CookieLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CookieLogProcessorTest {
    private CookieLogProcessor processor;

    @BeforeEach
    public void setUp() {
        processor = new CookieLogProcessor("2018-12-09");
    }

    @Test
    public void testCookiesOnTargetDateThenNonEmptyResult() {
        processor.process(new CookieLog(new Cookie("AtY0laUfhglK3lC7"), LocalDateTime.parse("2018-12-09T14:19:00")));
        processor.process(new CookieLog(new Cookie("SAZuXPGUrfbcn5UA"), LocalDateTime.parse("2018-12-09T10:13:00")));
        processor.process(new CookieLog(new Cookie("5UAVanZf6UtGyKVS"), LocalDateTime.parse("2018-12-09T07:25:00")));
        processor.process(new CookieLog(new Cookie("AtY0laUfhglK3lC7"), LocalDateTime.parse("2018-12-09T06:19:00")));

        List<Cookie> result = processor.getResult();
        assertEquals(1, result.size());
        assertEquals("AtY0laUfhglK3lC7", result.get(0).getCookieId());
    }

    @Test
    public void testNoCookiesOnGivenDateThenEmptyResult() {
        processor.process(new CookieLog(new Cookie("AtY0laUfhglK3lC7"), LocalDateTime.parse("2018-12-08T14:19:00")));
        processor.process(new CookieLog(new Cookie("SAZuXPGUrfbcn5UA"), LocalDateTime.parse("2018-12-08T10:13:00")));

        List<Cookie> result = processor.getResult();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testMultipleMostActiveCookiesThenCorrectResult() {
        processor.process(new CookieLog(new Cookie("AtY0laUfhglK3lC7"), LocalDateTime.parse("2018-12-09T14:19:00")));
        processor.process(new CookieLog(new Cookie("AtY0laUfhglK3lC7"), LocalDateTime.parse("2018-12-09T15:19:00")));
        processor.process(new CookieLog(new Cookie("SAZuXPGUrfbcn5UA"), LocalDateTime.parse("2018-12-09T10:13:00")));
        processor.process(new CookieLog(new Cookie("SAZuXPGUrfbcn5UA"), LocalDateTime.parse("2018-12-09T11:13:00")));

        List<Cookie> result = processor.getResult();
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(cookie -> cookie.getCookieId().equals("AtY0laUfhglK3lC7")));
        assertTrue(result.stream().anyMatch(cookie -> cookie.getCookieId().equals("SAZuXPGUrfbcn5UA")));
    }

    @Test
    public void testNoCookiesProcessedThenEmptyResult() {
        List<Cookie> result = processor.getResult();
        assertTrue(result.isEmpty());
    }
}