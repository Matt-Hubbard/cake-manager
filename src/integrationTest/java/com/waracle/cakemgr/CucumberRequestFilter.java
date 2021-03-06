package com.waracle.cakemgr;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CucumberRequestFilter implements Filter {

    @Autowired
    private ScenarioData scenarioData;

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        scenarioData.getScenario().log(
                new StringBuilder()
                        .append("Request\n")
                        .append("URI: ")
                        .append(requestSpec.getBaseUri())
                        .toString()
        );

        Response response = ctx.next(requestSpec, responseSpec);

        scenarioData.getScenario().log(
                new StringBuilder()
                        .append("Response\n")
                        .append("Body:\n")
                        .append(response.asPrettyString())
                        .toString()
        );

        return response;
    }
}
