package com.waracle.cakemgr.steps;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.ScenarioData;
import com.waracle.cakemgr.dto.CakeDto;
import io.cucumber.java.en.Then;
import io.restassured.common.mapper.TypeRef;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static java.util.Objects.isNull;
import static org.junit.Assert.fail;

public class ResponseSteps {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ScenarioData scenarioData;

    @Then("^the following cake is returned:$")
    public void thenTheFollowingCakeIsReturned(List<CakeDto> expectedCakes) {
        assertCakes(expectedCakes, singletonList(scenarioData.getLastResponse().getBody().as(CakeDto.class)));
    }

    @Then("^the following cakes are returned:$")
    public void thenTheFollowingCakesAreReturned(List<CakeDto> expectedCakes) {
        assertCakes(expectedCakes, getCakesList());
    }

    @Then("^the following errors are returned:$")
    public void thenTheFollowingErrorsAreReturned(List<Map<String, String>> expectedErrors) {
        List<Map<String, String>> actualErrors = new ArrayList<>((List<Map<String, String>>) getResponseBody().get("errors"));

        assertList(expectedErrors, actualErrors,
                matchIfPresent(m -> m.get("defaultMessage")),
                matchIfPresent(m -> m.get("field"))
        );
    }

    private Map<String, Object> getResponseBody() {
        return scenarioData.getLastResponse().getBody().as(new TypeRef<Map<String, Object>>() {
        });
    }

    private List<CakeDto> getCakesList() {
        return objectMapper.convertValue(((Map<String, Object>) getResponseBody().get("_embedded")).get("cakeDtoList"), new TypeReference<List<CakeDto>>() {
        });
    }

    private static <T, U> BiPredicate<T, T> matchIfPresent(Function<T, U> method) {
        return (expected, actual) -> isNull(method.apply(expected)) || method.apply(expected).equals(method.apply(actual));
    }

    private static void assertCakes(List<CakeDto> expectedCakes, List<CakeDto> actualCakes) {
        assertList(expectedCakes, actualCakes,
                matchIfPresent(CakeDto::getId),
                matchIfPresent(CakeDto::getTitle),
                matchIfPresent(CakeDto::getDescription),
                matchIfPresent(CakeDto::getImage)
        );
    }

    @SafeVarargs
    private static <T> void assertList(List<T> expecteds, List<T> actuals, BiPredicate<T, T>... predicates) {
        actuals = new ArrayList<>(actuals);
        for (T expected : expecteds) {
            List<T> refined = actuals.stream()
                    .filter(actual ->
                            Arrays.stream(predicates)
                                    .reduce(BiPredicate::and)
                                    .orElse((o, o2) -> true)
                                    .test(expected, actual)
                    )
                    .collect(Collectors.toList());

            if (refined.size() == 1) {
                actuals.remove(refined.get(0));
            } else if (refined.size() == 0) {
                fail("Can't match expected value");
            } else {
                fail("Too many actual values match expected value");
            }
        }
    }
}
