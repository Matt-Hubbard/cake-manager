package com.waracle.cakemgr.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.CucumberRequestFilter;
import com.waracle.cakemgr.ScenarioData;
import com.waracle.cakemgr.dto.CakeDto;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import java.lang.reflect.Type;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class RequestSteps {

    public static final String LOGIN_URL = "http://localhost:%d/login";
    public static final String CAKE_URL = "http://localhost:%d/cakes";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ScenarioData scenarioData;

    @Autowired
    private CucumberRequestFilter cucumberRequestFilter;

    @LocalServerPort
    private int port;

    @Before
    public void setUp(Scenario scenario) {
        scenarioData.setScenario(scenario);
        RestAssured.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(new Jackson2ObjectMapperFactory() {
            @Override
            public ObjectMapper create(Type cls, String charset) {
                return objectMapper;
            }
        }));
    }

    @DataTableType
    public CakeDto transformCake(Map<String, String> entry) {
        return objectMapper.convertValue(entry, CakeDto.class);
    }

    @When("^the user is logged in$")
    public void whenTheUserIsLoggedIn() {
        scenarioData.setLastResponse(
                given()
                        .filter(cucumberRequestFilter)
                        .auth()
                        .preemptive()
                        .basic("user", "password")
                        .when()
                        .get(format(LOGIN_URL, port))
        );

        scenarioData.setCrsfToken(scenarioData.getLastResponse().getCookie("XSRF-TOKEN"));
        scenarioData.setCookies(scenarioData.getLastResponse().getCookies());
    }

    @When("^the user gets all the cakes$")
    public void whenTheUserGetsAllTheCakes() {
        scenarioData.setLastResponse(
                given()
                        .filter(cucumberRequestFilter)
                        .auth()
                        .preemptive()
                        .basic("user", "password")
                        .when()
                        .get(format(CAKE_URL, port))
        );
    }

    @When("^the user adds the following cakes:$")
    public void whenTheUserAddsTheFollowingCakes(CakeDto cakeDto) {
        scenarioData.setLastResponse(
                given()
                        .filter(cucumberRequestFilter)
                        .header("X-XSRF-TOKEN", scenarioData.getCrsfToken())
                        .cookies(scenarioData.getCookies())
                        .contentType(ContentType.JSON)
                        .auth()
                        .preemptive()
                        .basic("user", "password")
                        .when()
                        .body(cakeDto)
                        .post(format(CAKE_URL, port))
        );
    }
}
