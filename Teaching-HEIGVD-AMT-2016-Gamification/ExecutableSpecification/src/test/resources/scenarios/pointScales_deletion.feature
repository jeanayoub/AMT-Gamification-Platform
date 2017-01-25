# GamificationAPI Test
# language: en

Feature: PointScale Deletion

  Background:
   Given I have a point scale payload with a token
   And I POST it to the /pointScales endpoint

  Scenario: Delete existent pointScale
   When I DELETE it using /pointScales/id endpoint
   Then I receive a 200 status code

  Scenario: Check that the pointScale has been deleted
   When I DELETE it using /pointScales/id endpoint
   And I ask for the pointScale with a GET on the /pointScales/id endpoint
   Then I receive a 404 status code