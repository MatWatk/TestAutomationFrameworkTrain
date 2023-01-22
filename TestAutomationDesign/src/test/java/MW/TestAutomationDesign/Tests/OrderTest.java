package MW.TestAutomationDesign.Tests;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import MW.TestAutomationDesign.AbstractComponent.AbstractComponent;
import MW.TestAutomationDesign.PageObjects.CartPage;
import MW.TestAutomationDesign.PageObjects.CheckoutOverviewPage;
import MW.TestAutomationDesign.PageObjects.CheckoutPage;
import MW.TestAutomationDesign.PageObjects.ConfirmationPage;
import MW.TestAutomationDesign.PageObjects.LandingPage;
import MW.TestAutomationDesign.PageObjects.ProductPage;
import MW.TestAutomationDesign.TestComponents.BaseTest;

public class OrderTest extends BaseTest{
@Test
	public void orderTest() throws IOException 
	{
		int maxPrice = 10;
		
		// LANDING PAGE
		// Select on which username you want to login - 1 standard_user, 2 locked_out_user, 3 problem_user, 4 performance_glitch_user;
		ProductPage productPage = landingPage.loggingIn(1);
		
		// PRODUCT PAGE
		productPage.addToCardSelected(maxPrice);
		CartPage cartPage = productPage.clickOnCart();

		// CART PAGE
		Boolean cartPageCheck = cartPage.cartCheckWithPriceDeclaration(maxPrice);
		Assert.assertFalse(cartPageCheck);
		CheckoutPage checkoutPage = cartPage.clickCheckout();
		
		// CHECKOUT PAGE
		checkoutPage.FillCheckoutAllFieldsWithDefinedValues();
		CheckoutOverviewPage checkoutOverviewPage = checkoutPage.clickContinueButton();
		
		// CHECHKOUT OVERVIEW PAGE	
		// Checking if the price has been counted well
		List<Double> allCartProductPrices = AbstractComponent.GetAllProductPrices();
		Assert.assertFalse(allCartProductPrices.stream().anyMatch(p->p>maxPrice));
		// Summing all products from cart and adding tax to it
		Double countedPrice = checkoutOverviewPage.PricesCalculation();
		// Converting the total price from website into double for comparison
		Double convertedTotalPrice = checkoutOverviewPage.TotalPriceConvertionToComparison();
		Assert.assertTrue(countedPrice.equals(convertedTotalPrice));
		ConfirmationPage confirmationPage = checkoutOverviewPage.ClickingFinishButton();
		
		// CONFIRMATION PAGE
		// Checking if message "Checkout Complete" has appeared and if all process has finished well
		String checkoutMessage = confirmationPage.GetConfirmationMessage();
		Assert.assertTrue(checkoutMessage.contains("COMPLETE"));
	}

@Test
public void cartCheck()
{
	ProductPage productPage = landingPage.loggingIn(1);
	int numberOfProducts = productPage.addAllToCard();
	int allProductsNumber = productPage.getNumberOfAllProducts();
	CartPage cartPage = productPage.clickOnCart();
	
	// Checking if all products has been added to cart
	Assert.assertTrue(numberOfProducts == cartPage.getNumberOfCartProducts());
	Assert.assertTrue(allProductsNumber == cartPage.getNumberOfCartProducts());
	cartPage.removeAllProductsFromCart();
	
	int allProductsNumberAfterRemove = cartPage.getNumberOfCartProducts();
	// Checking if all products "remove" buttons from card works
	Assert.assertTrue(allProductsNumberAfterRemove == 0);
	
	cartPage.clickContinueShoppingButton();
		
}

@Test
public void emptyCartCheckoutCheck()
{
	ProductPage productPage = landingPage.loggingIn(1);
	CartPage cartPage = productPage.clickOnCart();
	CheckoutPage checkoutPage = cartPage.clickCheckout();
	
	// If it is possible to go to checkout page with empty cart it doesn't make any sense and there should appear an error.
	// If no error will appear and the test will go on the test will fail.
	String currentPageName = checkoutPage.checkIfYoureOnCheckoutPage();
	Assert.assertFalse(currentPageName.contains("CHECKOUT"));
}

@Test
public void checkAllBackbuttons()
{
	ProductPage productPage = landingPage.loggingIn(1);
	CartPage cartPage = productPage.clickOnCart();
	CheckoutPage checkoutPage = cartPage.clickCheckout();
	checkoutPage.FillCheckoutAllFieldsWithDefinedValues();
	CheckoutOverviewPage checkoutOverviewPage = checkoutPage.clickContinueButton();
	
	checkoutOverviewPage.clickCancelButton();
	// Checking if the button brought the user back to products page
	String currentBrowserTitleProductsPage = productPage.checkIfYoureOnProductPage();
	Assert.assertTrue(currentBrowserTitleProductsPage.equalsIgnoreCase("products"));
	
	productPage.clickOnCart();
	cartPage.clickCheckout();
	
	checkoutPage.clickCancelButton();
	// Checking if the back button brought the user to cart page
	String currentBrowserTitleCartPage = cartPage.checkIfYoureOnProductPage();
	Assert.assertTrue(currentBrowserTitleCartPage.equalsIgnoreCase("your cart"));
	
	cartPage.clickContinueShoppingButton();
	// Checking if the button brought the user back to products page
	String currentBrowserTitleProductsPageAgain = productPage.checkIfYoureOnProductPage();
	Assert.assertTrue(currentBrowserTitleProductsPageAgain.equalsIgnoreCase("products"));
}

@Test
public void checkoutTextboxesCheck()
{
	ProductPage productPage = landingPage.loggingIn(1);
	productPage.addAllToCard();
	CartPage cartPage = productPage.clickOnCart();
	CheckoutPage checkoutPage = cartPage.clickCheckout();
	checkoutPage.changeDatatypesInTextboxes();
	CheckoutOverviewPage checkoutOverviewPage = checkoutPage.clickContinueButton();
	Assert.assertFalse(checkoutOverviewPage.getWebsiteTitle().contains("OVERVIEW"));
	
}

@Test
public void checkoutTexboxesFullCheck() throws InterruptedException
{
	ProductPage productPage = landingPage.loggingIn(1);
	productPage.addAllToCard();
	CartPage cartPage = productPage.clickOnCart();
	CheckoutPage checkoutPage = cartPage.clickCheckout();
	List<Integer> arrayContainingTestedDataTypes = checkoutPage.textboxesFullCheck();
	for (int arrayNumber = 0; arrayNumber<arrayContainingTestedDataTypes.size(); arrayNumber++)
	{
		double sortingVar = arrayNumber % 3;
		System.out.println(sortingVar);
		if (sortingVar == 0)
		{
			System.out.println("****************");
		}
		int dataTypeNumber = arrayContainingTestedDataTypes.get(arrayNumber);	
		if (dataTypeNumber == 1)
		{
			System.out.println("filled by number");
		}
		else 
		{
			System.out.println("filled by text");
		}
	}
}

}
