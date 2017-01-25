# GamificationAPI Test
# language: en

Feature: Rule Reading

 Background:
    Given I have a rule payload with a token 
    And I POST it to the /rules endpoint

  Scenario: Read all rules of an application
   When I ask for the rules with a GET on the /rules endpoint
   Then I receive a 200 status code


   Scenario: Read one existent rule of an application
    When I ask for the pointScale with a GET on the /rules/id endpoint
    Then I receive a 200 status code