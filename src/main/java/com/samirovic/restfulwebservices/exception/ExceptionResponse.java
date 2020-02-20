package com.samirovic.restfulwebservices.exception;

import java.time.LocalDate;

public class ExceptionResponse {

    private LocalDate timeStamp;
    private String message;
    private String detail;

    public ExceptionResponse(LocalDate timeStamp, String message, String detail) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.detail = detail;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }
}
