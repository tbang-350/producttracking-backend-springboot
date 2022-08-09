package com.example.demo.service;


import com.example.demo.entity.Metadata;
import com.example.demo.repository.MetadataRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class MetadataService {

    @Autowired
    private final MetadataRepository metadataRepository;

    public void initMetadata() {
        Metadata meta1 = new Metadata();
        meta1.setLatitude(-5.999518554627731);
        meta1.setLongitude(39.294092875230476);
        meta1.setRegisterDate(LocalDate.now());
        metadataRepository.save(meta1);

        Metadata meta2 = new Metadata();
        meta2.setLatitude(-6.076880092147241);
        meta2.setLongitude(39.38501361000966);
        meta2.setRegisterDate(LocalDate.now());
        metadataRepository.save(meta2);

        Metadata meta3 = new Metadata();
        meta3.setLatitude(-6.070594880346951);
        meta3.setLongitude(39.24061009006625);
        meta3.setRegisterDate(LocalDate.now());
        metadataRepository.save(meta3);
    }
}
