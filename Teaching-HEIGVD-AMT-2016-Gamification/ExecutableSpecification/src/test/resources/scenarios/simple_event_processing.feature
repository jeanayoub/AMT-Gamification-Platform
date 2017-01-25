Feature: Simple event processing

 
  Scenario: Post a new rule
   Given an application
   And a token for this application after a login
   And a Badge associated to that application
   And a PointScale associated to that application
   And a Rule associated to the event Like and the badge with 5 points
   And another Rule associated to the event Dislike and the pointScale with 2 points
   When I POST 2 Events Like for user u1
   And I POST 3 Events Dislike for user u2
   And I POST 10 Events Like for user u3
   When I ask for the event with a GET on /applications/id/events
   Then I see that there is 15 events
