Feature: Get Cakes
  The user can retrieve all the cakes in the system.

  Background:
    Given the user is logged in

  Scenario: Get all cakes
    When the user gets all the cakes
    Then the following cakes are returned:
      | title |