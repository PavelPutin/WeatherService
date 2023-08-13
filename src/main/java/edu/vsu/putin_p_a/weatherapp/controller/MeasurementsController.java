package edu.vsu.putin_p_a.weatherapp.controller;

import edu.vsu.putin_p_a.weatherapp.dto.MeasurementDTO;
import edu.vsu.putin_p_a.weatherapp.model.Measurement;
import edu.vsu.putin_p_a.weatherapp.servise.MeasurementsService;
import edu.vsu.putin_p_a.weatherapp.util.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementDTOValidator measurementDTOValidator;
    private final MeasurementsService measurementsService;
    private final ModelMapper modelMapper;

    public MeasurementsController(MeasurementDTOValidator measurementDTOValidator, MeasurementsService measurementsService, ModelMapper modelMapper) {
        this.measurementDTOValidator = measurementDTOValidator;
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        measurementDTOValidator.validate(measurementDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new MeasurementValidationException(bindingResult);
        }

        measurementsService.add(convertToMeasurement(measurementDTO));
    }

    @ExceptionHandler
    private MeasurementValidationErrorResponse handleException(MeasurementValidationException e) {
        StringBuilder message = new StringBuilder();
        for (FieldError fe : e.getErrors()) {
            message.append("Invalid value on field ").append(fe.getField())
                    .append(": ").append(fe.getDefaultMessage()).append(";");
        }
        return new MeasurementValidationErrorResponse(message.toString(), LocalDateTime.now());
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
