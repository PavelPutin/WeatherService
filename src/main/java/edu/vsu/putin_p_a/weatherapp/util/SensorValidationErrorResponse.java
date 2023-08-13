package edu.vsu.putin_p_a.weatherapp.util;

import java.time.LocalDateTime;

public class SensorValidationErrorResponse {
    private String message;
    private LocalDateTime timestamp;

    public SensorValidationErrorResponse(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
