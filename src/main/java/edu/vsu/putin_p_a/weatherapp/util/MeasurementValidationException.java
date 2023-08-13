package edu.vsu.putin_p_a.weatherapp.util;

import org.springframework.validation.BindingResult;

public class MeasurementValidationException extends ValidationException{
    public MeasurementValidationException(BindingResult bindingResult) {
        super(bindingResult);
    }
}
