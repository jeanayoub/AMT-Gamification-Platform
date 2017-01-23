# some comment
# language: en

#Feature: Application reading

# Background:
#   Given I have an application in my database with an id 

#  Scenario: Read all existent applications
#   When I ask for a list of registered apps with a GET on the /applications endpoint
#   Then I receive a 200 status code

#  Scenario: Read one existent application
#   Given I have an application with 40 as id
#   When I ask for the application with a GET on the /applications/id endpoint
#   Then I receive a 200 status code

#  Scenario: Check that it is not possible to read nonexistent application
#   Given I have a nonexistent application id like 999999
#   When I ask for the application with a GET on the /applications/id endpoint
#   Then I receive a 404 status code