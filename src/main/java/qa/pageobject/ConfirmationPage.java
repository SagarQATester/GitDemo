package qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.abstractComponent.AbstractComponent;

public class ConfirmationPage extends AbstractComponent
{

    WebDriver driver;
	
    @FindBy (css = ".hero-primary")
	WebElement successMsg;
	
	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String verifySuccessMessage()
	{
		return 	successMsg.getText();
	}

}
