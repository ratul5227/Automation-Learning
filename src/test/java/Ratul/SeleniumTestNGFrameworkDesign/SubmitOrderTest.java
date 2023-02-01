package Ratul.SeleniumTestNGFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Ratul.AbstructComponents.AbstractComponent;
import Ratul.SeleniumTestNGFrameworkDesign.PageObject.CartItemPage;
import Ratul.SeleniumTestNGFrameworkDesign.PageObject.OrderConfirmPage;
import Ratul.SeleniumTestNGFrameworkDesign.PageObject.OrdersPage;
import Ratul.SeleniumTestNGFrameworkDesign.PageObject.PlaceOrder;
import Ratul.SeleniumTestNGFrameworkDesign.PageObject.ProductCatalog;
import Ratul.SeleniumTestNGFrameworkDesign.PageObject.loginPage;
import Ratul.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	String ProductName = "ADIDAS ORIGINAL";
	String Prod = "test";
	@Test(dataProvider= "getData" , groups = {"Purchase"})
	public void SubmitOrder(HashMap<String,String> input ) throws InterruptedException, IOException {
		
		ProductCatalog productCatalog = loginPageObj.login(input.get("email"),input.get("password")); //as we are inheritating  BaseTest 
		//so we access loginPageObj variable from parent class
		
		productCatalog.addProductToCart(input.get("ProductName"));
		
		CartItemPage cartItem = productCatalog.navigateToAddToCardPage();  //As this child class has access of its parent class
		
		boolean cartItemCheck = cartItem.checkCartItemWithProduct(input.get("ProductName"));
		Assert.assertTrue(cartItemCheck);
		
		PlaceOrder PlaceOrder = cartItem.checkOut();
		PlaceOrder.addOrderDetails("India");
		OrderConfirmPage OrderConfirm = PlaceOrder.placeTheOrder();
		
		String OrderPlaceMessage = OrderConfirm.verifyOrderPlaceStatus();
		Assert.assertTrue(OrderPlaceMessage.equalsIgnoreCase("Order Placed Successfully"));
		
		String orderItemName = OrderConfirm.verifyOrderPlaceProduct(input.get("ProductName"));
		
		Assert.assertTrue(input.get("ProductName").equalsIgnoreCase(orderItemName));
		
		/*
		 * String [] orderItemName = {"ADIDAS ORIGINAL","IPHONE 13 PRO"}; List
		 * orderProductName = Arrays.asList(orderItemName);
		 * 
		 * for(int i=0;i<productLocation.size(); i++) { String productName =
		 * productLocation.get(i).getText(); if(orderProductName.contains(productName))
		 * {
		 * driver.findElements(By.xpath("//div[@class = 'card-body']/button[2]")).get(i)
		 * .click(); } Thread.sleep(3000); }
		 */
		 
	}
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void VerifyOrder() {
		ProductCatalog productCatalog = loginPageObj.login("gratul7@gmail.com", "RatulTest@1");
		OrdersPage OrdersPage = productCatalog.goToOrderPage();
		Assert.assertTrue(OrdersPage.CheckOrderedProduct(ProductName));
	}
	
	//This is the example of data provider with JSON data return 
	/*
	 * @DataProvider //using JSON data in data provider 
	 * public Object[][] getData()
	 * { 
	 * return new Object [][] {{"gratul7@gmail.com","RatulTest@1","ADIDAS ORIGINAL"},{"testR@mailinator.com","Test@123","ZARA COAT 3"}};
	 *  }
	 */
	
	//This is the example of data provider with HashMap
/*	 @DataProvider
	 public Object[][] getData(){
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "gratul7@gmail.com");
		map.put("password", "RatulTest@1");
		map.put("ProductName", "ADIDAS ORIGINAL");
		
		HashMap<String,String> map2 = new HashMap<String,String>();
		map2.put("email", "testR@mailinator.com");
		map2.put("password", "Test@123");
		map2.put("ProductName", "ZARA COAT 3");
		
		 return new Object [][] {{map},{map2}}; 
	 }*/
	 
	 //This is the example of data provider with
	 @DataProvider
	 public Object[][] getData() throws IOException{
		List <HashMap<String, String>> data =  getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Ratul\\TestData\\PurchaseOrder.json");
		
		 return new Object [][] {{data.get(0)},{data.get(1)},{data.get(2)}}; 
	 }
	

}
