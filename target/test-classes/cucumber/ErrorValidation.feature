
@tag
Feature: Error Validation
  I want to use this template for my feature file
	
  @ErrorValidation
  Scenario Outline: Negative test for login
  	Given I landed on Ecommerce page
    When Logged in with <username> and <Password>
    Then "Incorrect email or password." this Login Error message is displayed. 

    Examples: 
      | username  				| Password 	| 
      | gratul7@gmail.com | RatulTest@ |
      | g@gmail.com | RatulTest@ |  
