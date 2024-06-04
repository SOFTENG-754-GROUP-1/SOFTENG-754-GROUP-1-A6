@courseCategory
Feature: Course Category Navigation

  Scenario: user wants to view all course categories
    Given I visit the course category page
    Then I should see all available course categories

  Scenario: user wants to view all courses under the categories
    Given I visit the course category page
    Then I should see the courses under each category