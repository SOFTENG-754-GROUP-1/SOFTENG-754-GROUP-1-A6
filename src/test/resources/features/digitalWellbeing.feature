Feature: Digital Wellbeing
  Scenario: Accumulate screen time
    Given the user is using the language learning application
    When the user uses the application for 60 or more minutes
    Then the user's screen time should be stored and accumulated
