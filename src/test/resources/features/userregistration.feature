Feature: User Registration on LambdaTest eCommerce Playground

  Background:
    Given I launch the LambdaTest eCommerce Playground
    And I am on the registration page

  @Smoke @Regression
  Scenario Outline: Successful registration with valid details
    Given I have the registration details
    And I update the email address to "@example.com"
    When I fill in valid registration details
    And I select a newsletter subscription option as "<subscription_option>"
    And I agree to the Privacy Policy
    And I submit the registration form
    Then I should see "Your Account Has Been Created!"
    Examples:
      | subscription_option |
      | false               |
      | true                |

  @Regression
  Scenario Outline: Registration fails with invalid firstname
    When I fill in registration details with "firstname" as "<value>"
    And I select a newsletter subscription option as "true"
    And I agree to the Privacy Policy
    And I submit the registration form
    Then I should see an error message "<error>" for the field "firstname"

    Examples:
      | value    | error                                           |
      |          | First Name must be between 1 and 32 characters! |
      | John@#$% | Invalid characters in first name                |

  @Regression
  Scenario Outline: Registration fails with invalid lastname
    When I fill in registration details with "lastname" as "<value>"
    And I select a newsletter subscription option as "true"
    And I agree to the Privacy Policy
    And I submit the registration form
    Then I should see an error message "<error>" for the field "lastname"

    Examples:
      | value  | error                                          |
      |        | Last Name must be between 1 and 32 characters! |
      | Doe$%^ | Invalid characters in last name                |

  @Regression
  Scenario Outline: Registration fails with invalid email
    When I fill in registration details with "email" as "<value>"
    And I select a newsletter subscription option as "true"
    And I agree to the Privacy Policy
    And I submit the registration form
    Then I should see an error message "<error>" for the field "email"

    Examples:
      | value                | error                                       |
      |                      | E-Mail Address does not appear to be valid! |
      | invalid-email-format | E-Mail Address does not appear to be valid! |
      | john doe@example.com | E-Mail Address does not appear to be valid! |
      | johndoeexample.com   | E-Mail Address does not appear to be valid! |
      | johndoe@             | E-Mail Address does not appear to be valid! |

  @Regression
  Scenario Outline: Registration fails with invalid telephone
    When I fill in registration details with "telephone" as "<value>"
    And I select a newsletter subscription option as "true"
    And I agree to the Privacy Policy
    And I submit the registration form
    Then I should see an error message "<error>" for the field "telephone"

    Examples:
      | value                             | error                                          |
      | 12                                | Telephone must be between 3 and 32 characters! |
      | 123456789012345678901234567890123 | Telephone must be between 3 and 32 characters! |
      | +1(800)ABC-1234                   | Invalid characters in telephone                |
      | 123ABC456                         | Invalid characters in telephone                |

  @Regression
  Scenario Outline: Registration fails with invalid password
    When I fill in registration details with "password" as "<value>"
    And I select a newsletter subscription option as "true"
    And I agree to the Privacy Policy
    And I submit the registration form
    Then I should see an error message "<error>" for the field "password"

    Examples:
      | value                 | error                                         |
      |                       | Password must be between 4 and 20 characters! |
      | 123                   | Password must be between 4 and 20 characters! |
      | 123456789012345678901 | Password must be between 4 and 20 characters! |
      | john.doe@example.com  | Password should not match email               |

  @Regression
  Scenario: Registration fails without accepting Privacy Policy
    When I fill in valid registration details
    And I select a newsletter subscription option as "true"
    And I submit the registration form
    Then I should see warning message "Warning: You must agree to the Privacy Policy!"

  @Regression
  Scenario: Registration fails with already registered email
    When I fill in registration details with "email" as "john.doe@example.com"
    And I agree to the Privacy Policy
    And I submit the registration form
    Then I should see warning message "Warning: E-Mail Address is already registered!"