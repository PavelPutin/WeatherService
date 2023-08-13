package edu.vsu.putin_p_a.weatherapp.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

abstract public class ValidationException extends RuntimeException {
    private final BindingResult bindingResult;
    public ValidationException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public List<FieldError> getErrors() {
        return bindingResult.getFieldErrors();
    }
}
