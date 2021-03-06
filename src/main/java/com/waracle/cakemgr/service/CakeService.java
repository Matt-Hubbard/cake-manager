package com.waracle.cakemgr.service;

import com.waracle.cakemgr.dto.CakeDto;
import com.waracle.cakemgr.repository.CakeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CakeService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private final CakeRepository cakeRepository;

    public List<CakeDto> getAllCakes() {
        return modelMapper.map(cakeRepository.findAll(), new TypeToken<List<CakeDto>>() {
        }.getType());
    }

    public CakeDto createCake(CakeDto cakeDto) {
        throw new UnsupportedOperationException();
    }
}
