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
    private Double latitude;
    private Double longitude;
    private LocalDate registerDate;

    public Metadata( Double latitude, Double longitude, LocalDate registerDate) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.registerDate = registerDate;
    }
}
