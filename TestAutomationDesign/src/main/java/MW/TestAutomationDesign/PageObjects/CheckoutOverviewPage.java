package MW.TestAutomationDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MW.TestAutomationDesign.AbstractComponent.AbstractComponent;

public class CheckoutOverviewPage extends AbstractComponent{
WebDriver driver;

@FindBy (xpath="//div[@class='summary_tax_label']")
WebElement taxPath;

@FindBy (xpath="//div[@class='summary_total_label']")
WebElement totalPricePath;

@FindBy (xpath="//button[@name='finish']")
WebElement finishButtonPath;

@FindBy (id="cancel")
WebElement cancelButton;

@FindBy (xpath="//div/span")
WebElement websiteTitle;

	public CheckoutOverviewPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Double PricesCalculation()
	{
		// Summing all products from cart and adding tax to it
		List <Double> allCartProductPrices = GetAllProductPrices();
		Double productsSumPrice = allCartProductPrices.stream().mapToDouble(p->p).sum();
		String tax = taxPath.getText();
		String[] splittedTax = tax.split(" ");
		Double taxPrice = PricePreparation(splittedTax[1]);
		Double cartCountedPrice = taxPrice + productsSumPrice;
		return cartCountedPrice;
	}
	
	public Double TotalPriceConvertionToComparison()
	{
		String totalPrice = totalPricePath.getText();
		totalPrice = totalPrice.replace("Total:", " ");
		Double totalPriceConverted = PricePreparation(totalPrice);
		return totalPriceConverted;
	}
	
	public ConfirmationPage ClickingFinishButton()
	{
		finishButtonPath.click();
		return new ConfirmationPage(driver);
	}
	public void clickCancelButton()
	{
		cancelButton.click();
	}
	
	public String getWebsiteTitle()
	{
		return(websiteTitle.getText());
	}
}
