package com.reqres.stepDefinitions;

import io.cucumber.java.*;
import io.restassured.RestAssured;


public class Hooks {

    //import from io.cucumber.java not from junit
    @Before(order = 1)
    public void setupScenario() {

    }

    @Before (value = "@login", order = 2)
    public void setupScenarioForLogins() {

    }

    @Before (value = "@db", order = 0)
    public void setupForDatabaseScenarios() {

    }

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @After
    public void teardownScenario(Scenario scenario) {


    }

    @BeforeStep
    public void setupStep() {

    }

    @AfterStep
    public void afterStep() {

    }

}
