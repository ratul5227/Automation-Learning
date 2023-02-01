
@tag
Feature: Purchase order from Ecommerce Website
  I want to use this template for my feature file

	Background: 
	Given I landed on Ecommerce page
	
  @Regression
  Scenario Outline: Possitive test for submitting order 
    Given Logged in with <username> and <Password>
    When Add product <Product name> to cart 
    And CheckOut <Product name> and Submit order
    Then "Order Placed Successfully" message is displayed at confirmation page 

    Examples: 
      | username  				| Password 		| Product name |
      | gratul7@gmail.com | RatulTest@1 | ADIDAS ORIGINAL |
