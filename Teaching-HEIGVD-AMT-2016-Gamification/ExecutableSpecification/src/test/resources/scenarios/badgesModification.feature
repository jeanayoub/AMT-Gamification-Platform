# GamificationAPI Test
# language: en

Feature: Badge modification
  
  Scenario: Modify existing badge
   Given I have an application with an id and a related badge with an idBadge
   When I modify the payload of that badge
   And I PUT it to the /badges/id endpoint 
   Then I receive a 200 status code
   And I see that the badge modifications took effect
    
   