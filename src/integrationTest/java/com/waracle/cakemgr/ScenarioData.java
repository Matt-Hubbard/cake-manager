package com.waracle.cakemgr;

import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ScenarioData {

    private Scenario scenario;
    private Response lastResponse;

}
