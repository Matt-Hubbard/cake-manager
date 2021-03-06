Feature: Add Cakes
  The user can add new cakes to the system.

  Scenario: Add new cake
    Given the following cakes:
      | title | desc | image |
      |       |      |       |
    When the user adds the following cakes:
      | title | desc | image |
      |       |      |       |
    Then the following cakes are returned:
      | title |
      |       |

  Scenario: Cannot add a cake without a title
    When the user adds the following cakes:
      | desc | image |
      |      |       |
    Then the following cakes are returned:
      | title |
      |       |