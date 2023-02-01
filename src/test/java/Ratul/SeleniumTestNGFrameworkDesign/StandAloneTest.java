package Ratul.SeleniumTestNGFrameworkDesign;

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

import Ratul.SeleniumTestNGFrameworkDesign.PageObject.loginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup(); //using this we don't need to download chrome driver on local 
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		loginPage obj = new loginPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("gratul7@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("RatulTest@1");
		driver.findElement(By.id("login")).click();
		
		String ProductName = "ADIDAS ORIGINAL";
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> productBlock = driver.findElements(By.cssSelector(".mb-3")); 
		List<WebElement> productLocation = driver.findElements(By.xpath("//div[@class='card-body']/h5"));
		
		WebElement product = productBlock.stream().filter(i->i.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
		//System.out.println(product.findElement(By.cssSelector("b")).getText());
		
		product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		//Thread.sleep(3000);
		String Message = driver.findElement(By.id("toast-container")).getText();
		System.out.println(Message);
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//Thread.sleep(3000);
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click(); //here we use regular expression to find item
		
		List <WebElement> cartItem = driver.findElements(By.cssSelector(".cartWrap h3"));
		boolean cartItemCheck = cartItem.stream().allMatch(i->i.getText().equalsIgnoreCase(ProductName));
		Assert.assertTrue(cartItemCheck);
		
		
		driver.findElement(By.cssSelector(".totalRow .btn-primary")).click();
		
		driver.findElement(By.cssSelector(".form-group .input")).sendKeys("Ind");
		List <WebElement> options = driver.findElements(By.cssSelector(".list-group-item"));
		WebElement expectedOption = options.stream().filter(i->i.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		expectedOption.click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		System.out.println(driver.findElement(By.cssSelector(".toast-container")).getText());
		
		String orderItemName = driver.findElement(By.cssSelector(".m-3 .title")).getText();
		Assert.assertTrue(ProductName.equalsIgnoreCase(orderItemName));
		
		
		
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
}
