package edu.vsu.putin_p_a.weatherapp.util;

import java.time.LocalDateTime;

public class SensorValidationErrorResponse extends ValidationErrorResponse{

    public SensorValidationErrorResponse(String message, LocalDateTime timestamp) {
        super(message, timestamp);
    }
}
