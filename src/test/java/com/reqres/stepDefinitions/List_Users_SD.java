package com.reqres.stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class List_Users_SD extends BaseStepDef {

    @Given("I use this path {string}")
    public void i_use_this_path(String path) {

        endPoint += "/" + path;
    }

    @When("I use this query param {string} {string}")
    public void i_use_this_query_param(String keyword, String value) {

        reqSpec = given().spec(reqSpec)
                .queryParam(keyword, value);
    }

    @When("I use get method")
    public void i_use_get_method() {

        response = given().spec(reqSpec)
                .when().get(endPoint);
    }

    @Then("status code should be {int}")
    public void status_code_should_be(Integer statusCode) {

        response.then().statusCode(statusCode);
    }

    @Then("all users should be listed")
    public void all_users_should_be_listed() {

        List<Object> allUsers = response.path("data");
        System.out.println(allUsers);
    }

    @Then("response headers {string} should have this value {string}")
    public void response_headers_should_have_this_value(String header, String value) {

        response.then().header(header, value);
    }

    @Then("request headers {string} should have this value {string}")
    public void request_headers_should_have_this_value(String header, String value) {

        given().spec(reqSpec)
                .then().request().header(header, value);
    }

    @Then("response time is less then {int}")
    public void response_time_is_less_then(Integer time) {

        assertThat(response.getTime(), lessThan((long) time));
    }

    @Then("the value of {string} should be {string}")
    public void the_value_of_should_be(String key, String value) {

        response.then().body(key, is(value));
    }

    @Then("the value of {string} should be {int}")
    public void the_value_of_should_be(String key, Integer value) {

        response.then().body(key, is(value));
    }


    @Then("{string} url should be working")
    public void url_should_be_working(String url) {

        given().get(response.path(url).toString())
                .then().statusCode(200);
    }


    @Then("{string} should be listed")
    public void should_be_listed(String path) {

        System.out.println(response.path(path).toString());
    }


    @Then("odd ids' names should be listed")
    public void odd_ids_names_should_be_listed() {

        List<Map<Object, Object>> users = response.path("data");

        for (Map<Object, Object> user : users)
            if ((Integer) user.get("id") % 2 != 0)
                System.out.println(user.get("first_name") + " " + user.get("last_name"));
    }


    @Then("each email address should contain first name")
    public void each_email_address_should_contain_first_name() {

        List<Map<Object, Object>> users = response.path("data");

        for (Map<Object, Object> user : users) {
            assertThat(user.get("email").toString(), containsString(user.get("first_name").toString().toLowerCase()));
        }
    }


    @Then("user {string} and {string} should match")
    public void user_and_should_match(String given1, String given2) {

        List<Map<Object, Object>> users = response.path("data");

        int count = 0;
        for (Map<Object, Object> user : users) {
            if ((user.get("id").toString().equals(given1))
                    && (user.get("first_name").toString().equals(given2))) count++;
        }
        Assert.assertTrue(count==1);
    }

}
