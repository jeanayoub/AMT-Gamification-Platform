# GamificationAPI Test
# language: en

Feature: Badge registration

  Background:
   Given I have a badge payload and a token

  Scenario: Post a new badge
   When I POST it to the /badges endpoint
   Then I receive a 201 status code