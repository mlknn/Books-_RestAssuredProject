package org.Controller;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.Models.AuthClient;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.Controller.RequestSpecifications.getRequestSpecification;
import static org.Controller.RequestSpecifications.getResponseSpecification;

public class OrderController {
    Response response;
    public static String randomBookOrder() {
        Faker faker = new Faker();
        Random random = new Random();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        int randomNumber = random.nextInt(6);
        int bookId = randomNumber + 1;

        return String.format("{\n" +
                "    \"bookId\": \"%s\",\n" +
                "    \"customerName\": \"%s\"\n" +
                "}", bookId, firstName);
    }

    public Response createOrder(){
        return response= given().
                spec(getRequestSpecification()).when()
                .body(randomBookOrder())
                .header("Content-Type","application/json")
                .post("orders").then()
                .spec(getResponseSpecification())
                .extract().response();
    }
    public Response getOrderById(String orderId) {
        return response = given().
                spec(getRequestSpecification()).when()
                .pathParam("orderId", orderId)
                .get("/orders/{orderId}").then()
                .spec(getResponseSpecification())
                .extract().response();
    }
    public Response deleteOrderById(String orderId) {
        return response = given().
                spec(getRequestSpecification()).when()
                .queryParam("orderId", orderId)
                .delete("/orders/").then()
                .spec(getResponseSpecification())
                .extract().response();
    }
}
