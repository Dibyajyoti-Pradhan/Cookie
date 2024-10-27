package com.dibyojyoti.cookie.processor;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.dibyojyoti.cookie.model.Cookie;
import com.dibyojyoti.cookie.model.CookieLog;

public class CookieLogProcessor extends LogProcessor<CookieLog, Cookie> {
    private Map<Cookie, Integer> cookieCount = new HashMap<>();
    private LocalDate targetDate;

    public CookieLogProcessor(String date) {
        this.targetDate = LocalDate.parse(date);
    }

    @Override
    public void process(CookieLog cookieLog) {
        if (cookieLog.getTimestamp().toLocalDate().equals(targetDate)) {
            cookieCount.merge(cookieLog.getCookie(), 1, Integer::sum);
        }
    }

    @Override
    public List<Cookie> getResult() {
        if (cookieCount.size() == 0) {
            return Collections.emptyList();
        }
        int maxCount = Collections.max(cookieCount.values(), Integer::compare);
        return cookieCount.entrySet().stream()
                .filter(entry -> entry.getValue().equals(maxCount))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}