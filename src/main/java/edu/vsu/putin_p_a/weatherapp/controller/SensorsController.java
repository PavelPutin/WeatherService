package edu.vsu.putin_p_a.sprintrestservice.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensors")
public class SensorsController {
    private final ModelMapper modelMapper;

    public SensorsController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public void registration(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        sensorValidator.validate(sensorDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new SensorValidationException(bindingResult);
        }

        sensorsService.registration(convertToSensor(sensorDTO));
    }

    @ExceptionHandler
    private SensorValidationErrorResponse handleException(SensorValidationException e) {

    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
