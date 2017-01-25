# GamificationAPI Test
# language: en

Feature: Badge Reading

  Background:
   Given I have an application with an id and a related badge with an idBadge
   
  Scenario: Read all badges
   When I ask for the badges using /badges endpoint
   Then I receive a 200 status code
  
  Scenario: Read one badge
    When I ask for the badge using /badges/id
    Then I receive a 200 status code
   