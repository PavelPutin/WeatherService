package edu.vsu.putin_p_a.weatherapp.servise;

import edu.vsu.putin_p_a.weatherapp.model.Measurement;
import edu.vsu.putin_p_a.weatherapp.repository.MeasurementsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

    private void enrichMeasurement(Measurement measurement) {
        measurement.setCratedAt(LocalDateTime.now());
    }

    public int rainyDaysCount() {
        return measurementsRepository.countMeasurementsByRaining(true);
    }
}
