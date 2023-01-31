package MW.TestAutomationDesign.Tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.testng.annotations.Test;

import MW.TestAutomationDesign.AbstractComponent.AbstractComponent;
import MW.TestAutomationDesign.PageObjects.LandingPage;
import MW.TestAutomationDesign.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DevToolsTests extends BaseTest{

	@Test
	public void websiteOnDifferentDeviceSize() throws IOException
	{
		//Need to write it in a better way! For now for the raport it will work.
		AbstractComponent abstractComponent = new AbstractComponent(driver);
		ChromeDriver driverDev = abstractComponent.initDriverDev();
		abstractComponent.takescreenshotOnMobileVersion(driverDev);
	}
}
