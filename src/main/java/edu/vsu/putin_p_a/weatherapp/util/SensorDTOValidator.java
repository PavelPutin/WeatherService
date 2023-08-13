package edu.vsu.putin_p_a.weatherapp.util;

import edu.vsu.putin_p_a.weatherapp.dto.SensorDTO;
import edu.vsu.putin_p_a.weatherapp.repository.SensorsRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorDTOValidator implements Validator {
    private final SensorsRepository sensorsRepository;

    public SensorDTOValidator(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;

        if (sensorsRepository.findByName(sensorDTO.getName()).isPresent())
            errors.rejectValue("name", "", "Sensor with name " + sensorDTO.getName() + " already registered");

    }
}
