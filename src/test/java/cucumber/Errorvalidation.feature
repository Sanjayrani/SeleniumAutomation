@tag
Feature: Error Validation
  I want to use this template for feature file


  @ErrorValidation
  Scenario Outline: Negative test while logging in.
    Given I landed on Ecommerce page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples:
      | name                    | password  |
      |jack.davis1602@gmail.com | Jack@1023 |