package com.UITask.steps;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
 features = "Scenario"
 ,glue={"StepDefinition"},
		 monochrome = true
 )
public class RunnerClass {
	

}
