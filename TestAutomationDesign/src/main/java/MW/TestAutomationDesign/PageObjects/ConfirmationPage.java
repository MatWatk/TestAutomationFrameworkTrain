package MW.TestAutomationDesign.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MW.TestAutomationDesign.AbstractComponent.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
WebDriver driver;

@FindBy(xpath="//span")
WebElement confirmationMessagePath;

	public ConfirmationPage (WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public String GetConfirmationMessage()
	{
		// Checking if message "Checkout Complete" has appeared and if all process has finished well
		String checkoutMessage = confirmationMessagePath.getText();
		return checkoutMessage;
	}
}
