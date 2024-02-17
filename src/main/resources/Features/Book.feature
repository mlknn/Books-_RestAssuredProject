Feature: Books

  Scenario: Get Auth Token
    Given User get token

  Scenario: Get aLL BOOKS
    Given Get all books
    Then Verify status code "200"

  Scenario: Verify All book available
    Given Get all books
    Then Verify status code "200"
    And Verify all books have status of "true"

  Scenario Outline: Verify Book returns with Id
    Given Get all books
    Then Verify status code "200"
    When Search book with Id "<bookId>"
    Then Verify status code "200"
    Examples:
      | bookId |
      | 3      |
      | 5      |
      | 2      |


