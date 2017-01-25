# GamificationAPI Test
# language: en

Feature: Rules modification
  
  Scenario: Modify existing rule
   Given I have a rule payload with a token
   And I POST it to the /rules endpoint 
   When I modify the payload of that rule
   And I PUT it to the /rules/id endpoint 
   Then I receive a 200 status code
   And I see that the rule modifications took effect