package qa.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import qa.abstractComponent.AbstractComponent;
import qa.pageobject.CartPage;
import qa.pageobject.ConfirmationPage;
import qa.pageobject.LandingPage;
import qa.pageobject.OrderPage;
import qa.pageobject.PaymentPage;
import qa.pageobject.ProductCatalouge;
import qa.testcomponents.BaseTest;

public class TC_01_VerifyUserAbleToOrderTheProduct extends BaseTest {

	String productName = "ADIDAS ORIGINAL";


	@Test(dataProvider = "getData",groups="Purchase")
	public void orderPlace(HashMap <String,String> input) 
	{

		ProductCatalouge productCatlouge=landingPage.loginApplication(input.get("email"), input.get("password"));	
		productCatlouge.productAddToCart(input.get("productName"));
		CartPage cartPage=productCatlouge.goToCartPage();
		boolean match =cartPage.verifyTheCartProduct(input.get("productName"));
		Assert.assertTrue(match);
		PaymentPage paymentPage=cartPage.clickToCheckOutbutton();
		paymentPage.selectContry("India");
		ConfirmationPage confirmationpage= paymentPage.placeOrder();
		String successMsg = confirmationpage.verifySuccessMessage();
		Assert.assertTrue(successMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println("Order placed!!!!!!!!!!!!");

	}

	@Test(dependsOnMethods= {"orderPlace"})
	public void verifyOrderMenuList() 
	{
		ProductCatalouge productCatlouge=landingPage.loginApplication("sagar95kumbhar@gmail.com", "Sk@123456789");
		OrderPage orderPage=productCatlouge.goToOrderPage();
		orderPage=new OrderPage(driver);
		boolean match = orderPage.verifyOderProductDiplayed(productName);
		System.out.println(match);
		Assert.assertTrue(match);

	}
	@DataProvider
	public Object[][] getData() throws IOException
	{
     
	List<HashMap<String, String >>	data=getJSONDataToMap(System.getProperty("user.dir")+"//src//test//java//qa//data//PurchaseOrder.json");
	   return new Object[][] {{data.get(0)},{data.get(1)},}	;
	}
	
	
//	 @DataProvider
//	  public Object[][] getData()
//	  {
//	    return new Object[][]  {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" } };
//	    
//	  }
	/*
	HashMap<String, String > map= new HashMap<String, String>();
	map.put("email", "sagar95kumbhar@gmail.com");
	map.put("password", "Sk@123456789");
	map.put("productName", "ADIDAS ORIGINAL");
	
	HashMap<String, String > map1= new HashMap<String, String>();
	map1.put("email", "suraj0707@gmail.com");
	map1.put("password", "Sv@123456789");
	map1.put("productName", "IPHONE 13 PRO");
	*/
}
