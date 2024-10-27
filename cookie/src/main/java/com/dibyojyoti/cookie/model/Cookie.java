package com.dibyojyoti.cookie.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Cookie {
    private String cookieId;

    public Cookie(String cookieId) {
        this.cookieId = cookieId;
    }

    public String getCookieId() {
        return cookieId;
    }
}
