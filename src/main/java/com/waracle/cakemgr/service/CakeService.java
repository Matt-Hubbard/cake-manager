package com.waracle.cakemgr.service;

import com.waracle.cakemgr.dto.CakeDto;
import com.waracle.cakemgr.repository.CakeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CakeService {

    @Autowired
    private final CakeRepository cakeRepository;

    public List<CakeDto> getAllCakes() {
        throw new UnsupportedOperationException();
    }

    public CakeDto createCake(CakeDto cakeDto) {
        throw new UnsupportedOperationException();
    }
}
