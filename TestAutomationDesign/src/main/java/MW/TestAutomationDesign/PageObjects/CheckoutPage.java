package MW.TestAutomationDesign.PageObjects;

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
}
