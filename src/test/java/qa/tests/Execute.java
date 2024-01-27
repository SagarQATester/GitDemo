package qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import qa.abstractComponent.AbstractComponent;
import qa.pageobject.CartPage;
import qa.pageobject.ConfirmationPage;
import qa.pageobject.LandingPage;
import qa.pageobject.PaymentPage;
import qa.pageobject.ProductCatalouge;
import qa.testcomponents.BaseTest;

public class Execute extends BaseTest {

	@Test
	public void orderPlace() {

		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		landingPage.loginApplication("sagar95kumbhar@gmail.com", "Sk@123456789");

		String productName = "ADIDAS ORIGINAL";
		ProductCatalouge productCatlouge = new ProductCatalouge(driver);
		productCatlouge.productAddToCart(productName);

		AbstractComponent abstractComponent = new AbstractComponent(driver);
		abstractComponent.goToCartPage();

		CartPage cartPage = new CartPage(driver);
		cartPage.clickToCheckOutbutton();

		PaymentPage paymentPage = new PaymentPage(driver);
		paymentPage.selectContry("India");
		paymentPage.placeOrder();

		ConfirmationPage confirmationpage = new ConfirmationPage(driver);
		String successMsg = confirmationpage.verifySuccessMessage();
		Assert.assertTrue(successMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println("Order placed!!!!!!!!!!!!");

	}

}
