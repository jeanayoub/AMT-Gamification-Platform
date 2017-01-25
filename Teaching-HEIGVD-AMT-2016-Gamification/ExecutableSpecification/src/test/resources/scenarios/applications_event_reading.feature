# GamificationAPI Test
# language: en


Feature: Application Event Reading

  Background:
   Given I have an event payload with a token
   And I POST it to /events endpoint

  Scenario: Read all the events of an application
   When I ask for the events using /applications/id/events endpoint
   Then I receive a 200 status code
   