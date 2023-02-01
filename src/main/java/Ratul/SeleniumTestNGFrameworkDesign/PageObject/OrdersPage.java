package Ratul.SeleniumTestNGFrameworkDesign.PageObject;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Ratul.AbstructComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {
	
	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(xpath="//tr/td[2]")
	List<WebElement> OrderedProductName;

	public boolean CheckOrderedProduct(String ProductName) {
		boolean results = OrderedProductName.stream().map(i->i.getText()).anyMatch(i->i.equalsIgnoreCase(ProductName));
		return results;
	}
	
	

	
}
