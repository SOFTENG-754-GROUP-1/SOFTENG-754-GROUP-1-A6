@profilePersonalisation
Feature: Profile Personalisation

  Scenario: user wants to change to a valid username
    Given I visit the profile page
    When I enter "user1" in the new username field
    And I press the change username button
    Then I should see an alert message pop-up saying username successfully changed

  Scenario: user wants to change their profile picture
    Given I visit the profile page
    When I upload an image file for the profile picture
    And I press the change profile picture button
    Then I should see an alert message pop-up saying profile picture successfully changed

  Scenario: user wants to change their banner picture
    Given I visit the profile page
    When I upload an image file for the banner picture
    And I press the change banner picture button
    Then I should see an alert message pop-up saying banner picture successfully changed

  Scenario: user wants to update their flags
    Given I visit the profile page
    When I select flags
    And I press the update flags button
    Then I should see an alert message pop-up saying flag successfully changed

  Scenario: user wants to update status message
    Given I visit the profile page
    When I enter "Hello everyone" in the status field
    And I press the update status button
    Then I should see an alert message pop-up saying status successfully changed

  Scenario: user wants to change app theme
    Given I visit the profile page
    When I select "Dark" theme
    And I press the change theme button
    Then I should see an alert message pop-up saying theme successfully changed

  Scenario: user wants to see their profile information
    Given I visit the profile page
    Then I should see my current profile information

  Scenario: user wants to remove profile picture
    Given I visit the profile page
    When I press the remove profile picture button
    Then I should see no profile picture

  Scenario: user wants to remove banner picture
    Given I visit the profile page
    When I press the remove banner picture button
    Then I should see no banner picture