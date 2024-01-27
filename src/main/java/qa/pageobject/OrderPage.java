package qa.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {

	WebDriver driver;
	
	@FindBy (css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	public OrderPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyOderProductDiplayed(String productName)
	{
		boolean match=productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
		
	
	}

	
	
}
