package edu.vsu.putin_p_a.weatherapp.controller;

import edu.vsu.putin_p_a.weatherapp.dto.MeasurementDTO;
import edu.vsu.putin_p_a.weatherapp.dto.MeasurementResponseDTO;
import edu.vsu.putin_p_a.weatherapp.dto.RainyDaysDTO;
import edu.vsu.putin_p_a.weatherapp.model.Measurement;
import edu.vsu.putin_p_a.weatherapp.servise.MeasurementsService;
import edu.vsu.putin_p_a.weatherapp.util.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping
    public List<MeasurementResponseDTO> getAll() {
        return measurementsService.getAll().stream().map(this::convertToMeasurementResponseDTO).toList();
    }

    @GetMapping("/rainyDaysCount")
    public RainyDaysDTO rainyDaysCount() {
        return new RainyDaysDTO(measurementsService.rainyDaysCount());
    }

    @ExceptionHandler
    private MeasurementValidationErrorResponse handleException(MeasurementValidationException e) {
        return new MeasurementValidationErrorResponse(e.formMessage(), LocalDateTime.now());
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementResponseDTO convertToMeasurementResponseDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementResponseDTO.class);
    }
}
