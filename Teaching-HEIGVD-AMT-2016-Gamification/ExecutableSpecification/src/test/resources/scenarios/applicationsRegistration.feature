# some comment
# language: en

Feature: Application registration

  Background:
   Given I have an application payload 

  Scenario: Post a new application
   When I POST it to the /applications endpoint
   Then I receive a 201 status code

  Scenario: Check that the application has been posted
   When I POST it to the /applications endpoint
   And I ask for a list of registered apps with a GET on the /applications endpoint
   Then I see my app in the list

  Scenario: Check that it is not possible to create two apps with the same name
   When I POST it to the /applications endpoint
   And I POST it to the /applications endpoint
   Then I receive a 409 status code