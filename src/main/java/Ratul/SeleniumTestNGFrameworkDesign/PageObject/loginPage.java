package Ratul.SeleniumTestNGFrameworkDesign.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Ratul.AbstructComponents.AbstractComponent;

public class loginPage extends AbstractComponent{
	
	WebDriver driver;
	public loginPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	//WebElement userNames = driver.findElement(By.id("userEmail"));
	//using PageFactory
	@FindBy(id="userEmail")
	private WebElement username; //using this we implements Encapsulation within our code
	
	@FindBy(id="userPassword")
	private WebElement password;
	
	@FindBy(id="login")
	private WebElement loginButton;
	
	@FindBy(css=".toast-message")
	private WebElement loginErrorMsg;
	
	private By loginErrorMsgLocator = By.cssSelector(".toast-message");
	
	public ProductCatalog login(String userId, String passwordForLogin) {
		username.sendKeys(userId);
		password.sendKeys(passwordForLogin);
		loginButton.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	
	public String InvalidLoginCheck() {
		waitForElementToVisible(loginErrorMsgLocator);
		
		return loginErrorMsg.getText();
	}
	
	
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	

}
