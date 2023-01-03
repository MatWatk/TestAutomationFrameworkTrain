package MW.TestAutomationDesign.AbstractComponent;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AbstractComponent {
WebDriver driver;

@FindBy (xpath="//div[@class='cart_item']")
static
List <WebElement> allProductsFromCart;

	public AbstractComponent(WebDriver driver)
	{
		this.driver = driver;
	}

	public static Double PricePreparation(String price)
	{
		// Preparing data for convertion into double
		String replacedPrice = price.replace("$", " ").trim();
		double convertedPrice = Double.parseDouble(replacedPrice);
		return convertedPrice;
	}	
	
	public static List<Double> GetAllProductPrices()
	{
		List <Double> allCartProductPrices = allProductsFromCart.stream().map(p->PricesCheckout(p)).collect(Collectors.toList());
		return allCartProductPrices;
	}
	
	public static Double PricesCheckout(WebElement p)
	{
		String price = p.findElement(By.xpath("div/div/div")).getText();
		Double preparedPrice = PricePreparation(price);
		return preparedPrice;
	}
	
	
	
}
