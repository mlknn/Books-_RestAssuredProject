package org.Steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.Controller.BookController;
import org.Models.Book;

import java.util.*;
import static junit.framework.Assert.*;


public class BookSteps extends BookController {
    private Response response;
    private List<Book> books;
    private Book book;

    @Given("User get token")
    public void userGetToken() {
    }

    @Given("Get all books")
    public void getAllBooksStep() {
        response = getAllBooks();
        assertEquals(response.getStatusCode(), 200);

        // Extract books from response and initialize the books list
        books = response.jsonPath().getList(".", Book.class);
        assertNotNull(books); // Ensure books list is not null

    }

    @Then("Verify status code {string}")
    public void verify_status_code(String statusCode) {
        int ExpectedStatusCode =Integer.parseInt(statusCode);
        assertEquals(ExpectedStatusCode,response.getStatusCode());

    }

    @And("Verify all books have status of {string}")
    public void verifyAllBooksHaveStatusOf(String status) {
        books = response.jsonPath().getList(".", Book.class);
        // Check each book's availability
        for (Book book : books) {
            assertTrue(book.isAvailable());
        }

    }

    @When("Search book with Id {string}")
    public void searchBookWithId(String id) {
        response=getBookById(id);
        book = response.as(Book.class);
    }

}
