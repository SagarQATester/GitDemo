package qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import qa.abstractComponent.AbstractComponent;

public class PaymentPage extends AbstractComponent {

	WebDriver driver;

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement selectCountry;

	@FindBy(xpath = "//*[text()=' India']")
	WebElement india;

	@FindBy(xpath = "//*[text()='Place Order ']")
	WebElement placeOrder;

	By presenceText = By.xpath("//*[text()=' India']");

	public PaymentPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectContry(String country) {
		Actions a = new Actions(driver);
		a.sendKeys(selectCountry, country).build().perform();
		waitForPresenceOfElement(presenceText);
		a.moveToElement(india).click().build().perform();
	}

	public ConfirmationPage placeOrder() {
		placeOrder.click();
		ConfirmationPage confirmationPage= new ConfirmationPage(driver);
		return confirmationPage;
		
	}

}
