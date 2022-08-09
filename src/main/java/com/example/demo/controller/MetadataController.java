package com.example.demo.controller;


import com.example.demo.Dto.MetadataDto;
import com.example.demo.entity.Metadata;
import com.example.demo.entity.MetadataChartdata;
import com.example.demo.repository.MetadataRepository;
import com.example.demo.service.MetadataService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/meta")
@CrossOrigin("http://localhost:4200")
public class MetadataController {

    @Autowired
    private final MetadataRepository metadataRepository;

    @Autowired
    private final MetadataService metadataService;

    private ModelMapper mapper;

//    @PostConstruct
//    public void initMetadata(){
//        metadataService.initMetadata();
//    }

    @PostMapping("/addMetadata")
    public Metadata addMetadata(@RequestBody MetadataDto metadataDto){
        Metadata metadata = mapper.map(metadataDto,Metadata.class);
        metadata.setRegisterDate(LocalDate.now());
        return metadataRepository.save(metadata);
    }

    @GetMapping("/getMetadata")
    public List<Metadata> getAllMetadata(){
        return metadataRepository.findAll();
    }

    @GetMapping("/countMetadata")
    public int countMetadata(){
        return metadataRepository.countMetadata();
    }

    @GetMapping("/getMetachartdata")
    public MetadataChartdata getMetaChartdata(){
        return metadataRepository.getMetachartdata();
    }
}
