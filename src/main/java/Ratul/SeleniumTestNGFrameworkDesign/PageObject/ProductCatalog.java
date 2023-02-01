package Ratul.SeleniumTestNGFrameworkDesign.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Ratul.AbstructComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {
	
	WebDriver driver;
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	//WebElement userNames = driver.findElement(By.id("userEmail"));
	//using PageFactory
	@FindBy(css=".mb-3")
	List <WebElement> productBlock;
	@FindBy(css =".ng-animating")
	WebElement pageLoder;
	
	
	By findby = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.id("toast-container");
	By findby2 = By.cssSelector(".ng-animating");
	
	
	
	public List <WebElement> productList() {
		waitForElementToVisible(findby);
		return productBlock;
	}
	
	public WebElement getProductByName(String ProductName) {
		
		WebElement product = productList().stream().filter(i->i.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
		return product; 
	}
	
	public void addProductToCart(String ProductName) throws InterruptedException {
		WebElement product = getProductByName(ProductName);
		product.findElement(addToCart).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		waitForElementToVisible(toastMessage);
		waitForElementToInvisible(pageLoder);
		
	}
	
	
	
}
