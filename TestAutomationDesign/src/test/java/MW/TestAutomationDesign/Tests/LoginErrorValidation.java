package MW.TestAutomationDesign.Tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import MW.TestAutomationDesign.PageObjects.LandingPage;
import MW.TestAutomationDesign.TestComponents.BaseTest;

public class LoginErrorValidation extends BaseTest{

@Test
public void loginErrorValidation() throws IOException
{
	LandingPage landingPage = launchApplication();
	landingPage.goTo();
	String errorMessage = landingPage.manualLoggingIn("WrongLogin", "WrongPassword");
	Assert.assertTrue(errorMessage.contains("Epic sadface: Username and password do not match any user in this service"));
}
	
@Test
public void manuaLoggingInTest () throws IOException
{
	Properties prop = new Properties();
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//MW//Resources//GlobalData.properties");
	prop.load(fis);
	//String manualPassword = System.getProperty("password") != null ? System.getProperty("password") : prop.getProperty("password");
	String manualPassword = prop.getProperty("password");
	//String manualLogin = System.getProperty("login" != null ? System.getProperty("login") : prop.getProperty("login");
	String manualLogin = prop.getProperty("login");
	landingPage.manualLoggingIn(manualLogin, manualPassword);

	
}
}

