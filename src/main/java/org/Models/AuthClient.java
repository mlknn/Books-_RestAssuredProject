package org.Models;

import com.github.javafaker.Faker;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.Controller.RequestSpecifications.getRequestSpecification;
import static org.Controller.RequestSpecifications.getResponseSpecification;
import static org.Utils.UtilFunctions.jsonExtract;

public class AuthClient {
    public String clientName;
    public  String clientEmail;


    public AuthClient(String clientName, String clientEmail) {
        this.clientName = clientName;
        this.clientEmail = clientEmail;
    }

    public AuthClient() {
    }

    public static String generateJsonBody() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String clientName = firstName +" Bug";
        String clientEmail = firstName+lastName+ "2233@gmail.com";

        return String.format("{\n" +
                "    \"clientName\": \"%s\",\n" +
                "    \"clientEmail\": \"%s\"\n" +
                "}", clientName, clientEmail);
    }
     public static String getAuth(){
        Response response=given().baseUri("https://simple-books-api.glitch.me")
               .when()
                .body(AuthClient.generateJsonBody())
                .header("Content-Type","application/json")
                .post("api-clients/").then().log().all()
                .spec(getResponseSpecification())
                .extract().response();
        return  jsonExtract(response,"accessToken");
    }
}
