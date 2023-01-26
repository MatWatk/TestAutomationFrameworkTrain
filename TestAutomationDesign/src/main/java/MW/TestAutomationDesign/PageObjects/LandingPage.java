package MW.TestAutomationDesign.PageObjects;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v108.emulation.Emulation;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MW.TestAutomationDesign.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;

	// Driver constructor
	public LandingPage (WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="login_credentials")
	WebElement usernamesWholeTextPath;
	
	@FindBy(xpath="//div[@class='login_password']")
	WebElement wholePassTextPath;
	
	@FindBy(id="user-name")
	WebElement loginWindow;
	
	@FindBy(id="password")
	WebElement passwordWindow;
	
	@FindBy(id="login-button")
	WebElement loginButton;
	
	@FindBy(xpath="//h3")
	WebElement errorMessage;
	
	@FindBy(id="react-burger-menu-btn")
	WebElement menuBar;
	
	@FindBy(id="logout_sidebar_link")
	WebElement logoutButton;
	
	@FindBy(xpath="//span[@class='title']")
	WebElement websiteTitle;
	
	public void goTo()
	{
		driver.get("https://www.saucedemo.com/");
	}
	
	public ProductPage loggingIn(int userType)
	{
		// Getting login
		String usernamesWholeText = usernamesWholeTextPath.getText();
		String[] splittedText = usernamesWholeText.split("\\r?\\n");
		
		String login = splittedText[userType];
		
		//Getting password
		String wholePassText = wholePassTextPath.getText();
		String[] splittedPass = wholePassText.split(":");
		String password = splittedPass[1];
		
		// Filling logging in fields
		loginWindow.sendKeys(login);
		passwordWindow.sendKeys(password);
		
		// Clicking "LOGIN" button
		loginButton.click();
		return new ProductPage(driver);
	}
	
	public String manualLoggingIn(String provideLogin, String providePassword)
	{
		loginWindow.sendKeys(provideLogin);
		passwordWindow.sendKeys(providePassword);
		loginButton.click();
		try 
		{
			String loginMessage = errorMessage.getText();
			return loginMessage;
		}
		catch (Exception e)
		{
			String success = "success";
			return success;
		}
	}
	public void phoneVersion()
	{
		DevTools devTools = prepareDevTools();
		devTools.send(Emulation.setDeviceMetricsOverride(400, 634, 100, true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
		goTo();
	}
	public void logout() throws InterruptedException
	{
		menuBar.click();
		//Add explicit wait - when visible
		Thread.sleep(500);
		logoutButton.click();
	}
	public String checkLoggingOnBlockedUser()
	{
		loggingIn(2);
		String loginMessage = errorMessage.getText();
		loginWindow.sendKeys(Keys.CONTROL + "a");
		loginWindow.sendKeys(Keys.DELETE);
		passwordWindow.sendKeys(Keys.CONTROL + "a");
		passwordWindow.sendKeys(Keys.DELETE);
		return loginMessage;
	}
	public String getPageTitle()
	{
		return (websiteTitle.getText());
	}
}
