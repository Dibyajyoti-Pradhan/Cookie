package com.dibyojyoti.cookie.model;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class CookieLog implements Log {
    private Cookie cookie;
    private LocalDateTime timestamp;

    public CookieLog(Cookie cookie, LocalDateTime timestamp) {
        this.cookie = cookie;
        this.timestamp = timestamp;
    }
}
