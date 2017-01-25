# some comment
# language: en

Feature: Badge deletion

  Background:
   Given I have an application with an id and a related badge with an idBadge

  Scenario: Delete existent badge
   When I DELETE it using /badges/id endpoint
   Then I receive a 200 status code

  Scenario: Check that the badge has been deleted
   When I DELETE it using /badges/id endpoint
   And I ask for the badge using /badges/id
   Then I receive a 204 status code
