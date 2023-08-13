package edu.vsu.putin_p_a.weatherapp.util;

import edu.vsu.putin_p_a.weatherapp.dto.MeasurementDTO;
import edu.vsu.putin_p_a.weatherapp.repository.SensorsRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementDTOValidator implements Validator {
    private final SensorsRepository sensorsRepository;

    public MeasurementDTOValidator(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;

        String sensorName = measurementDTO.getSensor().getName();
        if (!sensorsRepository.existsById(sensorName)) {
            errors.rejectValue("sensor", "", "Sensor with name " + sensorName + " doesn't exist");
        }
    }
}
