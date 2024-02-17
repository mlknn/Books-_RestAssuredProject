package org.Steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.Controller.OrderController;

import static junit.framework.Assert.assertEquals;
import static org.Utils.UtilFunctions.jsonExtract;

public class OrderSteps extends OrderController {
    private String orderId;
    private String created;
    Response response;
    @When("Create an Order with random book")
    public void createAnOrderWithRandomBook() {
        response =createOrder();
        orderId=jsonExtract(response,"orderId");
        created=jsonExtract(response,"created");
        assertEquals(Boolean.parseBoolean(created),true);


    }

    @Then("User verify status code is {string}")
    public void userVerifyStatusCodeIs(String statusCode) {
        int ExpectedStatusCode =Integer.parseInt(statusCode);
        assertEquals(ExpectedStatusCode,response.getStatusCode());
    }

    @And("User search for Order")
    public void userSearchForOrder() {
        response =getOrderById(orderId);
        System.out.println("response.getBody() = " + response.getBody().asString());

    }

    @And("Delete order")
    public void deleteOrder() {
        response =deleteOrderById(orderId);

    }
}
