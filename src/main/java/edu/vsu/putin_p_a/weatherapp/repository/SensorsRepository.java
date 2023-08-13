package edu.vsu.putin_p_a.weatherapp.repository;

import edu.vsu.putin_p_a.weatherapp.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SensorsRepository extends JpaRepository<Sensor, String> {
    Optional<Sensor> findByName(String name);
}
