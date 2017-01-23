# some comment
# language: en

Feature: Application modification
  
  Scenario: Modify existing application
   Given I have an application with a specific payload
   And I POST it to the /applications endpoint
   When I modify the payload of that application
   And I PUT it to the /applications/id endpoint 
   Then I receive a 200 status code
   And I see that the modifications took effect
    
   
   Scenario: Check that it is not possible to modifiy nonexistent application
    Given I have a nonexistent application id like 999999
    And I have an application with a specific payload
    When I PUT it to the /applications/id endpoint
    Then I receive a 404 status code 