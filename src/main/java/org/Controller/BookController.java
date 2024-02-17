package org.Controller;

import io.restassured.response.Response;
import org.Models.AuthClient;
import com.fasterxml.jackson.annotation.JsonProperty;


import static io.restassured.RestAssured.given;
import static org.Utils.UtilFunctions.jsonExtract;

public class BookController extends RequestSpecifications {

    Response response;
    public Response getAllBooks(){
        return response= given().
                spec(getRequestSpecification()).when()
                .get("/books").then()
                .spec(getResponseSpecification())
                .extract().response();
    }
    public Response getBookById(final String bookId) {
        return response = given().
                spec(getRequestSpecification()).when()
                .pathParam("id", bookId)
                .log().all()
                .get("/books/{id}").then()
                .spec(getResponseSpecification())
                .extract().response();
    }


}
