# GamificationAPI Test
# language: en

Feature: Point Scales Reading

 Background:
   Given I have a point scale payload with a token
   And I POST it to the /pointScales endpoint

  Scenario: Read all pointScales of an application
   When I ask for the pointScales with a GET on the /pointScales endpoint
   Then I receive a 200 status code


   Scenario: Read one existent pointScale of an application
    When I ask for the pointScale with a GET on the /pointScales/id endpoint
    Then I receive a 200 status code