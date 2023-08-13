package edu.vsu.putin_p_a.weatherapp.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class SensorValidationException extends ValidationException {

    public SensorValidationException(BindingResult bindingResult) {
        super(bindingResult);
    }
}
