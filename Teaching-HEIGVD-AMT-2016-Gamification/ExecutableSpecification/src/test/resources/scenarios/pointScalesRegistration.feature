# GamificationAPI Test
# language: en

Feature: PointScale Registration

 Background:
   Given I have a point scale payload with a token 

  Scenario: Post a new point scale
   When I POST it to the /pointScales endpoint
   Then I receive a 201 status code