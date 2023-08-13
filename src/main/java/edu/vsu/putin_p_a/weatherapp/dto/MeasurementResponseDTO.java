package edu.vsu.putin_p_a.weatherapp.dto;

import java.time.LocalDateTime;

public class MeasurementResponseDTO extends MeasurementDTO {
    private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
