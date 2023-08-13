package edu.vsu.putin_p_a.weatherapp.servise;

import edu.vsu.putin_p_a.weatherapp.model.Measurement;
import edu.vsu.putin_p_a.weatherapp.repository.MeasurementsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;

    public MeasurementsService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }

    @Transactional
    public void add(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementsRepository.save(measurement);
    }

    public List<Measurement> getAll() {
        return measurementsRepository.findAll();
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setCreatedAt(LocalDateTime.now());
    }

    public int rainyDaysCount() {
        return measurementsRepository.countMeasurementsByRaining(true);
    }
}
