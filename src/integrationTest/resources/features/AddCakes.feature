Feature: Add Cakes
  The user can add new cakes to the system.

  Background:
    Given the user is logged in

  Scenario: Add new cake
    When the user adds the following cakes:
      | title          | description | image  |
      | Chocolate Cake | Delicious!  | image1 |
    Then the following cake is returned:
      | title          | description | image  |
      | Chocolate Cake | Delicious!  | image1 |

  Scenario: Cannot add a cake without a title
    When the user adds the following cakes:
      | description | image     |
      | Bland       | Beige.png |
    Then the following errors are returned:
      | defaultMessage         |
      | Title must not be null |

  Scenario: Cannot add a cake without a description
    When the user adds the following cakes:
      | title          | image     |
      | My First Cake! | Beige.png |
    Then the following errors are returned:
      | defaultMessage               |
      | Description must not be null |

  Scenario: Cannot add a cake without an image
    Given the user is logged in
    When the user adds the following cakes:
      | title          | description |
      | My First Cake! | Bland       |
    Then the following errors are returned:
      | defaultMessage         |
      | Image must not be null |