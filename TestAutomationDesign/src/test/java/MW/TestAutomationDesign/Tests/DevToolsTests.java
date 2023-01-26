package MW.TestAutomationDesign.Tests;

import java.io.IOException;

import org.testng.annotations.Test;

import MW.TestAutomationDesign.AbstractComponent.AbstractComponent;
import MW.TestAutomationDesign.PageObjects.LandingPage;
import MW.TestAutomationDesign.TestComponents.BaseTest;

public class DevToolsTests extends BaseTest{

	@Test
	public void websiteOnDifferentDeviceSize() throws IOException
	{
		// AFTER SORTING ALL TESTS EXCLUDE THIS TEST FROM @BEFORE TEST IN XML FILE!!!!!!!!
		LandingPage landingPage = new LandingPage(driver);
		landingPage.phoneVersion();
		AbstractComponent abstractComponent = new AbstractComponent(driver);
		abstractComponent.takeScreenshot();
	}
}
