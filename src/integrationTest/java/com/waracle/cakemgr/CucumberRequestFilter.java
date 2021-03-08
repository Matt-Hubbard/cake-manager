package com.waracle.cakemgr;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class CucumberRequestFilter implements Filter {

    @Autowired
    private ScenarioData scenarioData;

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        scenarioData.getScenario().log(
                new StringBuilder()
                        .append("Request")
                        .append("\nMethod: ").append(requestSpec.getMethod())
                        .append("\nHeaders: ").append(requestSpec.getHeaders().asList())
                        .append("\nCookies: ").append(requestSpec.getCookies().asList())
                        .append("\nURI: ").append(requestSpec.getURI())
                        .append("\nBody: ").append(nonNull(requestSpec.getBody()) ? requestSpec.getBody().toString() : null)
                        .append("\nForm: ").append(requestSpec.getFormParams().toString())
                        .append("\nPath Params: ").append(requestSpec.getPathParams().toString())
                        .toString()
        );

        Response response = ctx.next(requestSpec, responseSpec);
        StringBuilder builder = new StringBuilder()
                .append("Response")
                .append("\nHeaders: ").append(response.getHeaders())
                .append("\nCookies: ").append(response.getCookies());

        if (MediaType.APPLICATION_JSON_VALUE.equals(response.getContentType())) {
            builder.append("\nBody:").append(response.getBody().asPrettyString());
        }
        scenarioData.getScenario().log(builder.toString());
        return response;
    }
}
