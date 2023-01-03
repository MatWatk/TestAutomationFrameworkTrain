package MW.TestAutomationDesign.Tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.saucedemo.com/");
		
		// LANDING PAGE
		
		// Getting login
		String usernamesWholeText = driver.findElement(By.id("login_credentials")).getText();
		String[] splittedText = usernamesWholeText.split("\\r?\\n");
		
		// Select on which username you want to login - 1 standard_user, 2 locked_out_user, 3 problem_user, 4 performance_glitch_user;
		int userType = 1;
		String login = splittedText[userType];
		
		//Getting password
		String wholePassText = driver.findElement(By.xpath("//div[@class='login_password']")).getText();
		String[] splittedPass = wholePassText.split(":");
		String password = splittedPass[1];
		
		// Filling logging in fields
		driver.findElement(By.id("user-name")).sendKeys(login);
		driver.findElement(By.id("password")).sendKeys(password);
		
		// Clicking "LOGIN" button
		driver.findElement(By.id("login-button")).click();
		
		
		// PRODUCT PAGE
		
		// Getting all products into one WebElements list
		List <WebElement> allProducts = driver.findElements(By.xpath("//div[@class='inventory_item_description']"));
		// Adding to cart products which price is lower than 10$
		allProducts.stream().forEach(s->GetProductWithLowPrice(s));
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		
		// CART PAGE
		
		// Collecting all products into list
		List <WebElement> cartInventory = driver.findElements(By.xpath("//div[@class='cart_item']"));
		// Checking if is there any product more expensive than 10$
		List <Double> allCartProductPrices = cartInventory.stream().map(p->PricesCheckout(p)).collect(Collectors.toList());
		Assert.assertFalse(allCartProductPrices.stream().anyMatch(p->p>10));
		driver.findElement(By.id("checkout")).click();
		
		// CHECKOUT PAGE
		
		// Filling empty fields
		driver.findElement(By.id("first-name")).sendKeys("MW");
		driver.findElement(By.id("last-name")).sendKeys("ABC");
		driver.findElement(By.id("postal-code")).sendKeys("123");
		driver.findElement(By.id("continue")).click();
		
		// CHECHKOUT OVERVIEW PAGE
		// Checking if the price has been counted well
		Assert.assertFalse(allCartProductPrices.stream().anyMatch(p->p>10));
		// Summing all products from cart and adding tax to it
		Double productsSumPrice = allCartProductPrices.stream().mapToDouble(p->p).sum();
		String tax = driver.findElement(By.xpath("//div[@class='summary_tax_label']")).getText();
		String[] splittedTax = tax.split(" ");
		Double taxPrice = PricePreparation(splittedTax[1]);
		Double cartCountedPrice = taxPrice + productsSumPrice;
		// Converting the total price from website into double for comparison
		String totalPrice = driver.findElement(By.xpath("//div[@class='summary_total_label']")).getText();
		totalPrice = totalPrice.replace("Total:", " ");
		Double totalPriceConverted = PricePreparation(totalPrice);
		Assert.assertTrue(cartCountedPrice.equals(totalPriceConverted));
		driver.findElement(By.xpath("//button[@name='finish']")).click();
		
		// CONFIRMATION PAGE
		// Checking if message "Checkout Complete" has appeared and if all process has finished well
		String checkoutMessage = driver.findElement(By.xpath("//span")).getText();
		Assert.assertTrue(checkoutMessage.contains("COMPLETE"));
		driver.close();
	}
	public static Double PricePreparation(String price)
	{
		// Preparing data for convertion into double
		String replacedPrice = price.replace("$", " ").trim();
		double convertedPrice = Double.parseDouble(replacedPrice);
		return convertedPrice;
	}
	
	public static void GetProductWithLowPrice(WebElement s)
	{
		// Preparing data for convertion into double
		String price = s.findElement(By.xpath("div/div[@class='inventory_item_price']")).getText();
		Double preparedPrice = PricePreparation(price);
		// Selecting products which price is lower than 10$
		if (preparedPrice<10)
		{
			s.findElement(By.xpath("div/button")).click();
		}
	}
	public static Double PricesCheckout(WebElement p)
	{
		String price = p.findElement(By.xpath("div/div/div")).getText();
		Double preparedPrice = PricePreparation(price);
		return preparedPrice;
	}
}
