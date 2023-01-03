package MW.TestAutomationDesign.Tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import MW.TestAutomationDesign.AbstractComponent.AbstractComponent;
import MW.TestAutomationDesign.PageObjects.CartPage;
import MW.TestAutomationDesign.PageObjects.CheckoutOverviewPage;
import MW.TestAutomationDesign.PageObjects.CheckoutPage;
import MW.TestAutomationDesign.PageObjects.ConfirmationPage;
import MW.TestAutomationDesign.PageObjects.LandingPage;
import MW.TestAutomationDesign.PageObjects.ProductPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest2 {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		AbstractComponent abstractComponent = new AbstractComponent(driver);
		int maxPrice = 10;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		
		// LANDING PAGE
		// Select on which username you want to login - 1 standard_user, 2 locked_out_user, 3 problem_user, 4 performance_glitch_user;
		landingPage.loggingIn(1);
		
		// PRODUCT PAGE
		ProductPage productPage = new ProductPage(driver);
		productPage.addToCardSelected(maxPrice);

		// CART PAGE
		CartPage cartPage = new CartPage(driver);
		Boolean cartPageCheck = cartPage.CartCheck(maxPrice);
		Assert.assertFalse(cartPageCheck);
		cartPage.ClickCheckout();
		
		// CHECKOUT PAGE
		
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.FillCheckoutAllFields();
		
		// CHECHKOUT OVERVIEW PAGE
		
		
		// Checking if the price has been counted well
		List<Double> allCartProductPrices = AbstractComponent.GetAllProductPrices();
		Assert.assertFalse(allCartProductPrices.stream().anyMatch(p->p>maxPrice));
		// Summing all products from cart and adding tax to it
		CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
		Double countedPrice = checkoutOverviewPage.PricesCalculation();
		// Converting the total price from website into double for comparison
		Double convertedTotalPrice = checkoutOverviewPage.TotalPriceConvertionToComparison();
		Assert.assertTrue(countedPrice.equals(convertedTotalPrice));
		
		checkoutOverviewPage.ClickingFinishButton();
		
		// CONFIRMATION PAGE
		// Checking if message "Checkout Complete" has appeared and if all process has finished well
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		String checkoutMessage = confirmationPage.GetConfirmationMessage();
		Assert.assertTrue(checkoutMessage.contains("COMPLETE"));
		abstractComponent.closeBrowser();
	}

	


}
