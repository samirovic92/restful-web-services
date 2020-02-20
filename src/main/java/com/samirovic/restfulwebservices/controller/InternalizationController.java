package com.samirovic.restfulwebservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class InternalizationController {

    @Autowired
    MessageSource messageSource;

    @GetMapping("/hello-world-i18n")
    public String helloWorld(@RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        String message = messageSource.getMessage("good.morning.message", null, locale);

        return message;
    }
}
