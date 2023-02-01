package Ratul.AbstructComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Ratul.SeleniumTestNGFrameworkDesign.PageObject.CartItemPage;
import Ratul.SeleniumTestNGFrameworkDesign.PageObject.OrdersPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartButton;
	@FindBy(xpath="(//button[contains(@class,'btn-custom')])[2]")
	WebElement OrderButton;

	public void waitForElementToVisible(By findby) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	public void waitForElementToInvisible(WebElement pageLoder) throws InterruptedException {
		
		Thread.sleep(2000);  //using thread.sleep for 1 bug of the application
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 * wait.until(ExpectedConditions.invisibilityOf(pageLoder));
		 */
	}
	public void waitForElementToClickAble(By findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(findby));
		}
	public CartItemPage navigateToAddToCardPage() {
		cartButton.click();
		CartItemPage cartItem = new CartItemPage(driver);
		return cartItem;
	}
	
	public OrdersPage goToOrderPage() {
		OrderButton.click();
		OrdersPage OrdersPage = new OrdersPage(driver);
		return OrdersPage ;
	}
}
