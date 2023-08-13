package edu.vsu.putin_p_a.weatherapp.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MeasurementDTO {
    @Min(value = -100, message = "Measurement's value should be greater or equal to -100")
    @Max(value = 100, message = "Measurement's value should be less or equal to -100")
    @NotNull(message = "Measurement's values should not be null")
    private double value;

    @Column(name = "raining")
    @NotNull(message = "Measurement's raining should not be null")
    private boolean raining;

    @NotNull
    private SensorDTO sensorDTO;
}
