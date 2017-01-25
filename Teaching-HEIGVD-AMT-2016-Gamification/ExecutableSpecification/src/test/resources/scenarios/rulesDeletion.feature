# GamificationAPI Test
# language: en

Feature: Rule Deletion

  Background:
   Given I have a rule payload with a token
   And I POST it to the /rules endpoint 

  Scenario: Delete existent rule
   When I DELETE it using /rules/id endpoint
   Then I receive a 200 status code

  Scenario: Check that the rule has been deleted
   When I DELETE it using /rules/id endpoint
   And I ask for the pointScale with a GET on the /rules/id endpoint
   Then I receive a 204 status code

