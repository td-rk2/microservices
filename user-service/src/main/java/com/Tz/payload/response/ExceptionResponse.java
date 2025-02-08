package com.Tz.payload.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ExceptionResponse {
    private String message;
    private String error;
    private LocalDateTime timestamp;

    public ExceptionResponse(String message, String error, LocalDateTime timestamp) {
        this.message = message;
        this.error = error;
        this.timestamp = timestamp;
    }

    public ExceptionResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
