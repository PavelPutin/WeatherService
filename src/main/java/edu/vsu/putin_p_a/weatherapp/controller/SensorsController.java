package edu.vsu.putin_p_a.weatherapp.controller;

import edu.vsu.putin_p_a.weatherapp.dto.SensorDTO;
import edu.vsu.putin_p_a.weatherapp.model.Sensor;
import edu.vsu.putin_p_a.weatherapp.servise.SensorsService;
import edu.vsu.putin_p_a.weatherapp.util.SensorDTOValidator;
import edu.vsu.putin_p_a.weatherapp.util.SensorValidationErrorResponse;
import edu.vsu.putin_p_a.weatherapp.util.SensorValidationException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/sensors")
public class SensorsController {
    private final ModelMapper modelMapper;
    private final SensorDTOValidator sensorDTOValidator;
    private final SensorsService sensorsService;

    public SensorsController(ModelMapper modelMapper, SensorDTOValidator sensorDTOValidator, SensorsService sensorsService) {
        this.modelMapper = modelMapper;
        this.sensorDTOValidator = sensorDTOValidator;
        this.sensorsService = sensorsService;
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        sensorDTOValidator.validate(sensorDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new SensorValidationException(bindingResult);
        }

        sensorsService.register(convertToSensor(sensorDTO));
    }

    @ExceptionHandler
    private SensorValidationErrorResponse handleException(SensorValidationException e) {
        StringBuilder message = new StringBuilder();
        for (FieldError fe : e.getErrors()) {
            message.append("Invalid value on field ").append(fe.getField())
                    .append(": ").append(fe.getDefaultMessage()).append(";");
        }
        return new SensorValidationErrorResponse(message.toString(), LocalDateTime.now());
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
