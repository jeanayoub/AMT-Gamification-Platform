# some comment
# language: en

Feature: Login registration

  Scenario: Post a new login
   Given I have an application in my database with an id
   When I POST it to the /login endpoint
   Then I receive a 200 status code