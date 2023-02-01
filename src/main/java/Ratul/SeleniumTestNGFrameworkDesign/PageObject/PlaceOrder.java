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

public class PlaceOrder extends AbstractComponent {
	
	WebDriver driver;
	public PlaceOrder(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	//WebElement userNames = driver.findElement(By.id("userEmail"));
	//using PageFactory
	@FindBy(css=".form-group .input")
	WebElement country;
	@FindBy(css =".list-group-item")
	List <WebElement> options;
	@FindBy(css=".action__submit")
	WebElement placeOrder;
	By countryId = By.cssSelector(".form-group .input");
	
	public void addOrderDetails(String countryName) {
		//waitForElementToVisible(countryId);
		country.sendKeys(countryName);
		WebElement expectedOption = options.stream().filter(i->i.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		expectedOption.click();
	}
	public OrderConfirmPage placeTheOrder() {
		placeOrder.click();
		OrderConfirmPage OrderConfirm = new OrderConfirmPage(driver);
		return OrderConfirm;
	}
	
	
}
