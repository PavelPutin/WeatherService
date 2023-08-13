package edu.vsu.putin_p_a.weatherapp.servise;

import edu.vsu.putin_p_a.weatherapp.model.Sensor;
import edu.vsu.putin_p_a.weatherapp.repository.SensorsRepository;
import org.springframework.stereotype.Service;

@Service
public class SensorsService {
    private final SensorsRepository sensorsRepository;

    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    public void register(Sensor sensor) {
        sensorsRepository.save(sensor);
    }
}
