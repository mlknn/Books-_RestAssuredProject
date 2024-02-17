Feature: Orders

  Scenario: Create an order
    When Create an Order with random book
    Then User verify status code is "201"

    Scenario: Get an Order with Id
      When Create an Order with random book
      And User search for Order
      Then User verify status code is "200"

      Scenario:
        When Create an Order with random book
        And Delete order
        Then User verify status code is "204"