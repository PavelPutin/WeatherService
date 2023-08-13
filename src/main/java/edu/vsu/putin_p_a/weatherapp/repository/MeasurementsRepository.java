package edu.vsu.putin_p_a.weatherapp.repository;

import edu.vsu.putin_p_a.weatherapp.model.Measurement;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
    int countMeasurementsByRaining(@NotNull(message = "Measurement's raining should not be null") boolean raining);
}
