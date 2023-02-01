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

public class CartItemPage extends AbstractComponent {
	
	WebDriver driver;
	public CartItemPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(css=".cartWrap h3")
	List <WebElement> cartItem;
	@FindBy(css=".totalRow .btn-primary")
	WebElement checkOut;
	By checkOutButtonID= By.cssSelector(".totalRow .btn-primary");
	
	
	
	public boolean checkCartItemWithProduct(String ProductName) {
		
		boolean cartItemCheck = cartItem.stream().allMatch(i->i.getText().equalsIgnoreCase(ProductName));
		return cartItemCheck;
		
	}
	public PlaceOrder checkOut() {
		waitForElementToClickAble(checkOutButtonID);
		checkOut.click();
		PlaceOrder PlaceOrder = new PlaceOrder(driver);
		return PlaceOrder;
	}
	
}
