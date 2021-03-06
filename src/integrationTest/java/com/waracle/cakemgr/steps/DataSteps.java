package com.waracle.cakemgr.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.entity.CakeEntity;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class DataSteps {

    @Autowired
    private ObjectMapper objectMapper;

    @DataTableType
    public CakeEntity transformCake(Map<String, String> entry) {
        return objectMapper.convertValue(entry, CakeEntity.class);
    }

    @Given("^the following cakes:$")
    public void givenTheFollowingCakes(List<CakeEntity> cakes) {

    }
}
