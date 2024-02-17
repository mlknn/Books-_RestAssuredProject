package org.Controller;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.Models.AuthClient;


public class RequestSpecifications {

    static RequestSpecification requestSpecification;
    static ResponseSpecification responseSpecification;

    public static String baseURI="https://simple-books-api.glitch.me";

    public static RequestSpecification getRequestSpecification(){
        return requestSpecification=new RequestSpecBuilder().setBaseUri(baseURI)
                .addHeader("Authorization", "Bearer " + AuthClient.getAuth())
                .addHeader("Content-Type","application/json")
                .build();
    }
    public static ResponseSpecification getResponseSpecification(){
        return  responseSpecification=new ResponseSpecBuilder().setDefaultParser(Parser.JSON).build();
    }
}
