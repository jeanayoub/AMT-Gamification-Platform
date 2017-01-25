# GamificationAPI Test
# language: en

Feature: Application Event Reading

 Scenario:
  Given I have an event payload with a token
  And I POST it to /events endpoint
  When I ask for the event with a GET on /applications/id/events
  Then I receive a 200 status code