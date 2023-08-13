package edu.vsu.putin_p_a.weatherapp.repository;

import edu.vsu.putin_p_a.weatherapp.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
}
