# some comment
# language: en

Feature: Application Badge Reading

  Scenario: Read a specific badge with a specific application
   Given I have an application with an id and a related badge with an idBadge 
   When I ask for the badge using /applications/id/badges/idBadge endpoint
   Then I receive a 200 status code