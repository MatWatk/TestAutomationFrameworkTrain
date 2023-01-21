package MW.TestAutomationDesign.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import MW.TestAutomationDesign.PageObjects.CartPage;
import MW.TestAutomationDesign.PageObjects.CheckoutPage;
import MW.TestAutomationDesign.PageObjects.ProductPage;
import MW.TestAutomationDesign.TestComponents.BaseTest;

public class ErrorValidation extends BaseTest
{

	@Test
	public void loginErrorValidation() 
	{
		String errorMessage = landingPage.manualLoggingIn("WrongLogin", "WrongPassword");
		Assert.assertTrue(errorMessage.contains("Epic sadface: Username and password do not match any user in this service"));
	}
	
	@Test
	public void emptyCheckoutCheck()
	{
		ProductPage productPage = landingPage.loggingIn(1);
		productPage.addAllToCard();
		CartPage cartPage = productPage.clickOnCart();
		CheckoutPage checkoutPage = cartPage.clickCheckout();
		String errorMessage = checkoutPage.checkEmptyTextboxes();
		Assert.assertTrue(errorMessage.contains("Error"));
	}
		
	
}
