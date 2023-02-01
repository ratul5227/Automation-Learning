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

public class OrderConfirmPage extends AbstractComponent {
	
	WebDriver driver;
	public OrderConfirmPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	//WebElement userNames = driver.findElement(By.id("userEmail"));
	//using PageFactory
	@FindBy(id="toast-container")
	WebElement OrderPlaceToast;
	By orderPlaceMsg = By.id("toast-container");
	@FindBy(css = ".m-3 .title")
	WebElement OrderedProduct;
	
	public String verifyOrderPlaceStatus() {
		waitForElementToVisible(orderPlaceMsg);
		
		String OrderPlaceMessage = OrderPlaceToast.getText();
		return OrderPlaceMessage;
	}
	public String verifyOrderPlaceProduct(String ProductName) {
		String orderItemName = OrderedProduct.getText();
		return orderItemName;
	}
	
	
}
