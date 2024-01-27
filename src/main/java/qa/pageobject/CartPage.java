package qa.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.abstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent
{

	WebDriver driver;

	@FindBy (xpath = "//button[text()='Checkout']")
	WebElement checkout;
	
	@FindBy (css=".cartSection h3")
	private List<WebElement> cartProduct;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
		
	public boolean verifyTheCartProduct(String productName)
	{
		boolean match=cartProduct.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	public PaymentPage clickToCheckOutbutton()
	{
		checkout.click();
		PaymentPage paymentPage= new PaymentPage(driver);
		return paymentPage ;
	}
	
	
	
}
