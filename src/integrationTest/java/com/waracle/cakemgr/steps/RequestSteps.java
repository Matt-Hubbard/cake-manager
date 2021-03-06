package com.waracle.cakemgr.steps;

import com.waracle.cakemgr.CucumberRequestFilter;
import com.waracle.cakemgr.ScenarioData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class RequestSteps {

    public static final String CAKE_URL = "http://localhost:%d/cakes";

    @Autowired
    private ScenarioData scenarioData;

    @Autowired
    private CucumberRequestFilter cucumberRequestFilter;

    @LocalServerPort
    private int port;

    @Before
    public void setUp(Scenario scenario) {
        scenarioData.setScenario(scenario);

    }

    @When("^the user gets all the cakes$")
    public void whenTheUserGetsAllTheCakes() {
        given()
                .filter(cucumberRequestFilter)
                .when()
                .get(format(CAKE_URL, port))
        ;
    }

    @When("^the user adds the following cakes:$")
    public void whenTheUserAddsTheFollowingCakes(DataTable cakeDtos) {

    }
}
