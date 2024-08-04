@tag
  Feature: Purchase the order from Ecommerce Website
    I want to use this template for feature file

    Background:
      Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test for submitting the order.
    Given Logged in with username <name> and password <password>
    When I added product <productName> to cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message will be displayed on ConfirmationPage

    Examples:
    | name                    | password | productName  |
    |jack.davis1602@gmail.com | Jack@123 | ZARA COAT 3  |