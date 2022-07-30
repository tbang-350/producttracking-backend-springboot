package com.example.demo.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "metadata")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long metadata_id;
    private String name;
    private Double latitude;
    private Double longitude;
    private String description;
    private LocalDate registerDate;

    public Metadata(String name, Double latitude, Double longitude, String description, LocalDate registerDate) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.registerDate = registerDate;
    }
}
