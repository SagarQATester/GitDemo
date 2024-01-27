package qa.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.abstractComponent.AbstractComponent;

public class ProductCatalouge extends AbstractComponent
{

	WebDriver driver;
	
	@FindBy (css=".mb-3")
	List<WebElement> products;
	
	By productBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By ProductText=By.xpath("//*[contains(text(),'Product')]");
	By cart=By.cssSelector("[routerlink*='cart']");
	
	
	public ProductCatalouge(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productBy);
		return products;
	}
	public WebElement getProductName(String productName)
	{
		WebElement prod=getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		return prod;
	}
	public void productAddToCart(String productName)
	{
		WebElement prod=getProductName(productName);
		prod.findElement(addToCart).click();
		waitForPresenceOfElement(ProductText);
		waitForElementToClickable(cart);
		
	}
	
}
