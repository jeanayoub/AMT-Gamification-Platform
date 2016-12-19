# some comment
# language: en

Feature: Modify existing application
  
  Scenario: Modify existing application
   Given I have an existing application ID
   And I have an application payload
   When I PUT it to the /applications/{ID} endpoint
   And I I ask for the appliction with a GET on the /applications{id} endpoint
   Then I receive a 201 status code
   And I see that the modifications took effect


    
   Scenario: Check that modifications took effect