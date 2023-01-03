package MW.TestAutomationDesign.PageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MW.TestAutomationDesign.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent{
WebDriver driver;

@FindBy (xpath="//div[@class='cart_item']")
List <WebElement> allProductsFromCart;

@FindBy (id="checkout")
WebElement checkoutButtonPath;

@FindBy (xpath="//div[@class='item_pricebar']/button")
List <WebElement> removeFromCartButton;

@FindBy (xpath="//div[@class='cart_footer']/button[@id='continue-shopping']")
WebElement continueShoppingButton;

@FindBy (xpath="//span[@class='title']")
WebElement productPageTitle;

	public CartPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean cartCheckWithPriceDeclaration(int selectedMaxPrice)
	{
		// Checking if is there any product more expensive than 10$
		
		//List <Double> allCartProductPrices = allProductsFromCart.stream().map(p->PricesCheckout(p)).collect(Collectors.toList());
		List<Double> allCartProductPrices = GetAllProductPrices();
		if (allCartProductPrices.stream().anyMatch(p->p>selectedMaxPrice))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public CheckoutPage clickCheckout()
	{
		checkoutButtonPath.click();
		return new CheckoutPage(driver);
	}
	
	public int getNumberOfCartProducts()
	{
		return allProductsFromCart.size();
	}
	
	public void removeAllProductsFromCart()
	{
		removeFromCartButton.stream().forEach(b->b.click());
	}
	
	public void clickContinueShoppingButton()
	{
		continueShoppingButton.click();
	}
	public String checkIfYoureOnProductPage()
	{
		return productPageTitle.getText();
	}
	
}
