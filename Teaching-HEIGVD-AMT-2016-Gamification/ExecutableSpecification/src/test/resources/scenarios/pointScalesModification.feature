# GamificationAPI Test
# language: en

Feature: PointScale modification
  
 Background:
   Given I have a point scale payload with a token
   And I POST it to the /pointScales endpoint


  Scenario: Modify existing PointScale
   When I modify the payload of that PointScale
   And I PUT it to the /pointScales/id endpoint 
   Then I receive a 200 status code
   And I see that the pointScale modifications took effect
    
   