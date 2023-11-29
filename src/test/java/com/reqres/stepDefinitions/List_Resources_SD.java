package com.reqres.stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class List_Resources_SD extends BaseStepDef {

    @Then("{string} with {string} should be listed")
    public void with_should_be_listed(String path, String expectedKey, List<Object> expectedValues) {

        List<Map<Object, Object>> users = response.path(path);

        for (Map<Object, Object> user : users) {
            if (expectedValues.contains(user))
                System.out.println(user);
        }
    }

    @Then("{string} should start with {string} and have {int} characters")
    public void should_start_with_and_have_characters(String path, String startWith, Integer size) {

        List<String> users = response.path("data."+path);

        for (String user : users) {
            Assert.assertTrue(user.startsWith(startWith) && user.length() == size);
        }
    }

    @Then("all the value of {string} should be following format as NN-NNNN")
    public void all_the_value_of_should_be_following_format_as(String path) {

        List<String> usersValues=response.path("data."+path);

        for (String each : usersValues) {

            Assert.assertTrue(each.matches("\\d{2}-[0-9]{4}"));
        }
    }

    @Then("the {string} of {int}. element of data should be {int}")
    public void the_of_element_of_data_should_be(String path, Integer id, Integer year) {

        List<Integer> usersValues=response.path("data."+path);

        Assert.assertEquals(year, usersValues.get(id-1));
    }


}
