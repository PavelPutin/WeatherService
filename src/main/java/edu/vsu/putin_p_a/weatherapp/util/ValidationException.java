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

    public String formMessage() {
        StringBuilder message = new StringBuilder();
        for (FieldError fe : this.getErrors()) {
            message.append("Invalid value on field ").append(fe.getField())
                    .append(": ").append(fe.getDefaultMessage()).append(";");
        }
        return message.toString();
    }
}
