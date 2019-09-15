Feature: SignIn Page
Scenario: SignIn Page with  New user Account Creation

Given User is on "http://automationpractice.com/index.php" page and "practicetest@gmail.com"
When I enter all Mandatory details
      | Romia                   |
      | Julite                  |
      | 123456                  |
      | 21 Newsteadway          |
      | Trenton                 |
      | 08601                   |
      | 123456987               |
      | Home address            |
Then Validating the user account with the "http://automationpractice.com/index.php?controller=my-account" 
And Validating both products are same


