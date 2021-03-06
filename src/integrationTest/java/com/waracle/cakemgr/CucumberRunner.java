package com.waracle.cakemgr;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        plugin = "json:build\\reports\\cucumber\\cucumber-report.json"
)
public class CucumberRunner {

}
