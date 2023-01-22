package MW.TestAutomationDesign.PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MW.TestAutomationDesign.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
WebDriver driver;

@FindBy(id = "first-name")
WebElement firstName;

@FindBy(id = "last-name")
WebElement lastName;

@FindBy(id="postal-code")
WebElement postCode;

@FindBy (id="continue")
WebElement continueButton;

@FindBy (xpath="//span[@class='title']")
WebElement checkoutPageTitle;

@FindBy (id="cancel")
WebElement cancelButton;

@FindBy (tagName="h3")
WebElement errorMessage;

	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void FillCheckoutAllFieldsWithDefinedValues()
	{
		// Filling empty fields
	firstName.sendKeys("MW");
	lastName.sendKeys("ABC");
	postCode.sendKeys("123");
	}
	
	public CheckoutOverviewPage clickContinueButton()
	{
		continueButton.click();
		return new CheckoutOverviewPage(driver);
	}
	
	public String checkIfYoureOnCheckoutPage()
	{
		return checkoutPageTitle.getText();
	}
	public void clickCancelButton()
	{
		cancelButton.click();
	}
	public String checkEmptyTextboxes()
	{
		continueButton.click();
		String message = errorMessage.getText();
		return message;
	}
	public void changeDatatypesInTextboxes()
	{
		firstName.sendKeys("123");
		lastName.sendKeys("123");
		postCode.sendKeys("Abcd");
	}
	public List<Integer> textboxesFullCheck() throws InterruptedException
	{
		List<String> dataTypesForTextboxes = new ArrayList<String>();
		List<Integer> dataTypesWhichWebsiteAccepted = new ArrayList<Integer>();
		dataTypesForTextboxes.add("123");
		dataTypesForTextboxes.add("abc");
		CheckoutOverviewPage checkoutOverviewPage = clickContinueButton();
		ProductPage productPage = new ProductPage(driver);
		CartPage cartPage = new CartPage(driver);
		if (errorMessage.getText().isEmpty())
		{
			System.out.println("do sth when error doesnt appear - it should appear");
		}
		else
		{
			for (int i=0; i<=1; i++)
			{

				//Thread.sleep(1000);
				for (int j=0; j<=1; j++)
				{

					//Thread.sleep(1000);
					for (int k=0; k<=1; k++)
					{
						firstName.sendKeys(dataTypesForTextboxes.get(i));
						lastName.sendKeys(dataTypesForTextboxes.get(j));
						postCode.sendKeys(dataTypesForTextboxes.get(k));
						Thread.sleep(1000);
						continueButton.click();
						String websiteTitle = checkoutOverviewPage.getWebsiteTitle();
						if (websiteTitle.contains("OVERVIEW"))
						{
						dataTypesWhichWebsiteAccepted.add(i);
						dataTypesWhichWebsiteAccepted.add(j);
						dataTypesWhichWebsiteAccepted.add(k);
						checkoutOverviewPage.clickCancelButton();
						productPage.clickOnCart();
						cartPage.clickCheckout();
						//Thread.sleep(1000);
						}											
					}
				}
			}
		}
		return dataTypesWhichWebsiteAccepted;
	}
}
