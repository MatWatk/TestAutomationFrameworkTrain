package MW.TestAutomationDesign.PageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MW.TestAutomationDesign.AbstractComponent.AbstractComponent;

public class ProductPage extends AbstractComponent{
WebDriver driver;

@FindBy(xpath="//div[@class='inventory_item_description']")
static
List <WebElement> allProducts;

@FindBy(xpath="//a[@class='shopping_cart_link']")
WebElement cartButton;

@FindBy(xpath="div/div[@class='inventory_item_price']")
WebElement productPrice;

@FindBy(xpath="//div[@class='inventory_item_description']/div/button")
List <WebElement> allAddToCartButtons;

@FindBy (xpath="//span[@class='title']")
WebElement productPageTitle;

	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//public List<WebElement> GetProducts()
	//{
		// Getting all products into one WebElements list
	//	return allProducts;
	//}
	
	public void addToCardSelected(int selectedMaxPrice)
	{
		// Adding to cart products which price is lower than typed in method price
		allProducts.stream().forEach(s->GetProductWithLowPrice(s,selectedMaxPrice));

	}
	
	public static void GetProductWithLowPrice(WebElement s, int selectedMaxPrice)
	{
		// Preparing data for convertion into double
		String price = s.findElement(By.xpath("div/div[@class='inventory_item_price']")).getText();
		Double preparedPrice = PricePreparation(price);
		// Selecting products which price is lower than 10$
		if (preparedPrice<selectedMaxPrice)
		{
			s.findElement(By.xpath("div/button")).click();
		}
	}
	public int addAllToCard()
	{
		allAddToCartButtons.stream().forEach(s->s.click());
		List <String> buttonsText = allAddToCartButtons.stream().map(s->s.getText()).collect(Collectors.toList());
		List <String> buttonsWithChangedStatus = buttonsText.stream().filter(buttonText->buttonText.equalsIgnoreCase("remove")).collect(Collectors.toList());
		return buttonsWithChangedStatus.size(); 
		
	}
	
	
	public int getNumberOfAllProducts()
	{
		return allProducts.size();
	}
	
	public CartPage clickOnCart()
	{
		cartButton.click();
		return new CartPage(driver);
	}
	
	public String checkIfYoureOnProductPage()
	{
		return productPageTitle.getText();
	}
}
