package com.waracle.cakemgr.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;

public class ResponseSteps {

    @Then("^the following cakes are returned:$")
    public void thenTheFollowingCakesAreReturned(DataTable expectedCakes) {

    }

    @Then("^the following errors are returned:$")
    public void thenTheFollowingErrorsAreReturned(DataTable expectedErrors) {

    }
}
