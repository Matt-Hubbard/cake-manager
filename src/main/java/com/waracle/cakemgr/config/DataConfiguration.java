package com.waracle.cakemgr.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.entity.CakeEntity;
import com.waracle.cakemgr.repository.CakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class DataConfiguration {

    @Value("${waracle.data.remove.duplicates:false}")
    private boolean sanitiseData;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CakeRepository cakeRepository;

    public static final String DATA_URL = "https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json";

    @Bean
    public CommandLineRunner loadInitialData() {
        return args -> {
            List<CakeEntity> cakeEntities = objectMapper.readValue(new URL(DATA_URL), new TypeReference<List<CakeEntity>>() {
            });
            if (sanitiseData) {
                cakeEntities = cakeEntities.stream().distinct().collect(Collectors.toList());
            }
            cakeRepository.saveAll(cakeEntities);
        };
    }
}
