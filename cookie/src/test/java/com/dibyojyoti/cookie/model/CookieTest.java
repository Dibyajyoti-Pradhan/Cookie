package com.dibyojyoti.cookie.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CookieTest {

    @Test
    public void testGetCookieId() {
        Cookie cookie = new Cookie("AtY0laUfhglK3lC7");
        assertEquals("AtY0laUfhglK3lC7", cookie.getCookieId(), "Cookie ID should match the constructor input.");
    }
}
