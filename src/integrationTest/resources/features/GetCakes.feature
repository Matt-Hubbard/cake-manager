Feature: Get Cakes
  The user can retrieve all the cakes in the system.

  Scenario: Get all cakes
    Given the following cakes:
      | title | desc | image |
      |       |      |       |
    When the user gets all the cakes
    Then the following cakes are returned:
      | title |
      |       |