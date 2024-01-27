package qa.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.abstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent
{

	WebDriver driver;

	@FindBy(id = "userEmail")
	WebElement userMail;

	@FindBy(id = "userPassword")
	WebElement userPass;

	@FindBy(id = "login")
	WebElement login;
	
	@FindBy(xpath = "//*[contains(text(),'Incorrect')]")
	WebElement errorMsg;
		
	By errorMsgBy=By.xpath("//*[contains(text(),'Incorrect')]");

	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public void enterUserMailId(String userId) {
		userMail.sendKeys(userId);
	}

	public void enterUserPassword(String userPassword) {
		userPass.sendKeys(userPassword);
	} 

	public void clickOnloginbutton() {
		login.click();
	}

	public ProductCatalouge loginApplication(String userId, String userPassword) {
		enterUserMailId(userId);
		enterUserPassword(userPassword);
		clickOnloginbutton();
		ProductCatalouge productCatlouge= new ProductCatalouge(driver);
		return  productCatlouge;		
	}
	public String getErrorMessage()
	{
		waitForPresenceOfElement(errorMsgBy);
		return errorMsg.getText();
	}
}
