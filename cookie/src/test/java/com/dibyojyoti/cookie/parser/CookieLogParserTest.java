package com.dibyojyoti.cookie.parser;

import com.dibyojyoti.cookie.model.CookieLog;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CookieLogParserTest {

    @Test
    public void testParse() {
        CookieLogParser parser = new CookieLogParser();
        String input = "AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00";
        CookieLog log = parser.parse(input);

        assertNotNull(log);
        assertEquals("AtY0laUfhglK3lC7", log.getCookie().getCookieId());
        assertEquals(LocalDateTime.parse("2018-12-09T14:19:00"), log.getTimestamp().withNano(0));
    }
}
