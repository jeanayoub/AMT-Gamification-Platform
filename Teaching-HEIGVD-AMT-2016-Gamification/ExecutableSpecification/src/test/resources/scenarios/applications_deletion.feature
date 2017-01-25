# GamificationAPI Test
# language: en

Feature: Application deletion

  Background:
   Given I have an application in my database with an id 

  Scenario: Delete existent application
   Given I have an application with an id
   When I DELETE it using /applications/id endpoint
   Then I receive a 200 status code

  Scenario: Check that the application has been deleted
   Given I have an application with an id
   When I DELETE it using /applications/id endpoint
   And I ask for the application with a GET on the /applications/id endpoint
   Then I receive a 404 status code

  Scenario: Check that it is not possible to delete nonexistent application
   Given I have a nonexistent application id like 999999
   When I DELETE it using /applications/id endpoint
   Then I receive a 404 status code