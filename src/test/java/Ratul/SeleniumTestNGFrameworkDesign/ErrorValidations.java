package Ratul.SeleniumTestNGFrameworkDesign;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import Ratul.AbstructComponents.AbstractComponent;
import Ratul.SeleniumTestNGFrameworkDesign.PageObject.CartItemPage;
import Ratul.SeleniumTestNGFrameworkDesign.PageObject.OrderConfirmPage;
import Ratul.SeleniumTestNGFrameworkDesign.PageObject.PlaceOrder;
import Ratul.SeleniumTestNGFrameworkDesign.PageObject.ProductCatalog;
import Ratul.SeleniumTestNGFrameworkDesign.PageObject.loginPage;
import Ratul.TestComponents.BaseTest;
import Ratul.TestComponents.RetryFailedtestcase;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidations extends BaseTest {
	
	
	@Test(groups= {"ErrorHandeling"}, retryAnalyzer=RetryFailedtestcase.class) //using this retryAnalyzer if this test failed then
	//it will call the RetryFailedtestcase class retry method and run it again until retry method return false.
	public void LoginErrorValidation() throws InterruptedException, IOException {
		
		loginPageObj.login("gtest@gmail.com", "RatulTest@1"); 
		Assert.assertEquals("Incorrect email password.", loginPageObj.InvalidLoginCheck().trim());
		
	}
	
	@Test
public void ProductErrorValidation() throws InterruptedException, IOException {
		
		String ProductName = "ADIDAS ORIGINAL";
		ProductCatalog productCatalog = loginPageObj.login("testR@mailinator.com", "Test@123"); 
		productCatalog.addProductToCart(ProductName);
		CartItemPage cartItem = productCatalog.navigateToAddToCardPage();  	
		boolean cartItemCheck = cartItem.checkCartItemWithProduct("ADIDAS");
		Assert.assertFalse(cartItemCheck);
	}
}
