package qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import qa.testcomponents.BaseTest;
import qa.testcomponents.Retry;

public class TC_02_VerifyErrorDisplayed extends BaseTest
{

	@Test(groups= {"errorHandle"},retryAnalyzer = Retry.class)
	public void verifyLoginErrorMessage()
	{
		landingPage.loginApplication("sagar95kumbhar@gmail.com", "Sk@1234567sd89");
		String errorMsg=landingPage.getErrorMessage();
		System.out.println(errorMsg);
		Assert.assertEquals(errorMsg,"Incorrect email or password.");
		
	}
	
	
}
