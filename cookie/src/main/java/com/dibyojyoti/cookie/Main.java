package com.dibyojyoti.cookie;

import java.nio.file.*;
import java.util.List;

import com.dibyojyoti.cookie.model.Cookie;
import com.dibyojyoti.cookie.model.CookieLog;
import com.dibyojyoti.cookie.parser.CookieLogParser;
import com.dibyojyoti.cookie.parser.LogParser;
import com.dibyojyoti.cookie.processor.CookieLogProcessor;
import com.dibyojyoti.cookie.processor.LogProcessor;

public class Main {
    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: java Main -f <filename> -d <date>");
            return;
        }

        String filename = null, date = null;
        for (int i = 0; i < args.length; i++) {
            if ("-f".equals(args[i]) && i + 1 < args.length) {
                filename = args[i + 1];
            } else if ("-d".equals(args[i]) && i + 1 < args.length) {
                date = args[i + 1];
            }
        }

        if (filename == null || date == null) {
            System.out.println("Invalid arguments. Please ensure both -f and -d are provided.");
            return;
        }

        try {
            LogProcessor<CookieLog, Cookie> processor = new CookieLogProcessor(date);
            LogParser<CookieLog> parser = new CookieLogParser();

            Path path = Paths.get(filename);
            Files.lines(path).map(parser::parse).forEach(processor::process);

            List<Cookie> mostActiveCookies = processor.getResult();
            mostActiveCookies.forEach(cookie -> System.out.println(cookie.getCookieId())); // Modified this line
        } catch (Exception e) {
            System.out.println("Error Message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
