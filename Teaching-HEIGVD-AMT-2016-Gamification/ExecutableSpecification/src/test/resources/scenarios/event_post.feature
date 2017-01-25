# GamificationAPI Test
# language: en

Feature: Event Registration

 Scenario:
  Given I have an event payload with a token
  When I POST it to /events endpoint
  Then I receive a 201 status code