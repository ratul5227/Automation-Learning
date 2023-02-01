package Ratul.stepDefinition;

import java.io.IOException;

import org.testng.Assert;

import Ratul.SeleniumTestNGFrameworkDesign.PageObject.CartItemPage;
import Ratul.SeleniumTestNGFrameworkDesign.PageObject.OrderConfirmPage;
import Ratul.SeleniumTestNGFrameworkDesign.PageObject.PlaceOrder;
import Ratul.SeleniumTestNGFrameworkDesign.PageObject.ProductCatalog;
import Ratul.SeleniumTestNGFrameworkDesign.PageObject.loginPage;
import Ratul.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionImp extends BaseTest {
	
	public loginPage loginPageObj;
	public ProductCatalog productCatalog;
	public OrderConfirmPage OrderConfirm;
	@Given ("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		loginPageObj = launchApplication();
	}
	
	@Given ("^Logged in with (.+) and (.+)$") //we are adding regular expression (.+) as this is input value. and adding ^ at start and $ at end 
	public void logged_in_with_username_password(String username, String Password) {
		productCatalog = loginPageObj.login(username,Password);
	}
	
	@When ("^Add product (.+) to cart$")
	public void add_product_to_cart(String ProductName) throws InterruptedException {
		productCatalog.addProductToCart(ProductName);
	}
	
	@When ("CheckOut (.+) and Submit order$")    //here we can use both when or AND keyword
	public void checkOut_and_submit_order(String ProductName) {
		CartItemPage cartItem = productCatalog.navigateToAddToCardPage();  //As this child class has access of its parent class
		
		boolean cartItemCheck = cartItem.checkCartItemWithProduct(ProductName);
		Assert.assertTrue(cartItemCheck);
		
		PlaceOrder PlaceOrder = cartItem.checkOut();
		PlaceOrder.addOrderDetails("India");
		OrderConfirm = PlaceOrder.placeTheOrder();
	}
	
	 @Then ("{string} message is displayed at confirmation page")
	 public void Messange_displayed_at_confirmation_page(String string) {
		 String OrderPlaceMessage = OrderConfirm.verifyOrderPlaceStatus();
			Assert.assertTrue(OrderPlaceMessage.equalsIgnoreCase(string));
			driver.close();
	 }
	 @Then ("{string} this Login Error message is displayed.") 
	 public void Login_Error_message_is_displayed(String string) {
		 Assert.assertEquals(string, loginPageObj.InvalidLoginCheck().trim());
		 driver.close();
	 }
	

}
