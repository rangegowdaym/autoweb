Feature: Login

  @Smoke
  @Regression
  Scenario Outline: Login with credentials
    Given I launch the LambdaTest eCommerce Playground
    And I open the login page
    When I enter username as "<username>" and password as "<password>"
    And I click the login button
    Then I should see the logout button

    Examples:
      | username                     | password  |
      | test.lambdatest001@gmail.com | Test@1234 |

  @Regression
  Scenario Outline: LOgin with invalid credentials
    # This scenario tests the login functionality with various invalid credentials.
    Given I launch the LambdaTest eCommerce Playground
    And I open the login page
    When I enter username as "<username>" and password as "<password>"
    And I click the login button
    Then I should see the error message "<message>"

    Examples:
      | username                           | password      | message                                                                                          |
      | test.lambdatest001@gmail.com       |               | Warning: No match for E-Mail Address and/or Password.                                            |
      | test.lambdatest001@gmail.com       | Test@12344566 | Warning: No match for E-Mail Address and/or Password.                                            |
      |                                    | Test@1234     | Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour. |
      |                                    |               | Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour. |
      | test.lambdatest001@exampletest.com | Test@1234     | Warning: No match for E-Mail Address and/or Password.                                            |
