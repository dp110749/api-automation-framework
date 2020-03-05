package com.MLI_DOLPHIN.Execution;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(glue = {
"classpath:com.MLI_DOLPHIN.stepDefinitions"},features = { "classpath:FeatureFiles/OauthApiTest.feature" })

public class Execution extends AbstractTestNGCucumberTests{

}
