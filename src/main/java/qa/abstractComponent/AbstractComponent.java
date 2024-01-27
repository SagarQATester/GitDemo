package qa.abstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qa.pageobject.CartPage;
import qa.pageobject.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	WebDriverWait wait;

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public CartPage goToCartPage() 
	{
		CartPage cartPage= new CartPage(driver);
		cartHeader.click();
		return cartPage;
	}

	public OrderPage goToOrderPage() 
	{
		OrderPage orderPage= new OrderPage(driver);
		orderHeader.click();
		return orderPage;
	}

	public void waitForElementToAppear(By findBy) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

	}

	public void waitForPresenceOfElement(By findBy) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(findBy));

	}

	public void waitForElementToClickable(By findBy) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}

}
