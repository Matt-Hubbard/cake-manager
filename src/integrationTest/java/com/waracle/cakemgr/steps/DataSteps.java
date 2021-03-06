package com.waracle.cakemgr.steps;

import com.waracle.cakemgr.CakeEntity;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;

import java.util.List;
import java.util.Map;

public class DataSteps {

    @DataTableType
    public CakeEntity transformCake(Map<String, String> entry) {
        // TODO Add fields to entity
        CakeEntity cakeEntity = new CakeEntity();
        return cakeEntity;
    }

    @Given("^the following cakes:$")
    public void givenTheFollowingCakes(List<CakeEntity> cakes) {

    }
}
