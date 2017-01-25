# GamificationAPI Test
# language: en

Feature: Rules Registration

 Background:
   Given I have a rule payload with a token 

  Scenario: Post a new rule
   When I POST it to the /rules endpoint
   Then I receive a 201 status code