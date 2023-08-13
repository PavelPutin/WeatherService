package edu.vsu.putin_p_a.weatherapp.util;

import java.time.LocalDateTime;

public class MeasurementValidationErrorResponse extends ValidationErrorResponse {
    public MeasurementValidationErrorResponse(String message, LocalDateTime timestamp) {
        super(message, timestamp);
    }
}
