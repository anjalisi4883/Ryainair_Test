Feature: Ryanair booking -I want to test Ryanair booking upto Decline payment page.

  Scenario: Ryanair booking upto Decline payment page
    Given User logged-in to Ryanair Application
    And User makes a booking from "Dublin" to "Malta" for "2" Adults and "1" children
    When User pay for booking with card details "1234 56789 000000" "10/20" and "123"
    Then User should get a payment decline message