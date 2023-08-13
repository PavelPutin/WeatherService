package edu.vsu.putin_p_a.weatherapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "sensor")
public class Sensor {
    @Column(name = "name")
    @Id
    @NotEmpty(message = "Sensor's name should not be empty")
    @Size(min = 3, max = 30, message = "Sensors's name length should be between 3 and 30 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
