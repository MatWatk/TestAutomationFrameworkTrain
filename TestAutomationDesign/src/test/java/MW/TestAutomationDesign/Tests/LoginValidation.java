package MW.TestAutomationDesign.Tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import MW.TestAutomationDesign.AbstractComponent.AbstractComponent;
import MW.TestAutomationDesign.PageObjects.LandingPage;
import MW.TestAutomationDesign.PageObjects.ProductPage;
import MW.TestAutomationDesign.TestComponents.BaseTest;

public class LoginValidation extends BaseTest{

@Test(groups= {"LoggingIn"})
public void manuaLoggingInTest () throws IOException
{
	Properties prop = new Properties();
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//MW//Resources//GlobalData.properties");
	prop.load(fis);
	String manualPassword = System.getProperty("password") != null ? System.getProperty("password") : prop.getProperty("password");
	//String manualPassword = prop.getProperty("password");
	String manualLogin = System.getProperty("login") != null ? System.getProperty("login") : prop.getProperty("login");
	//String manualLogin = prop.getProperty("login");
	landingPage.manualLoggingIn(manualLogin, manualPassword);
}

@Test(groups= {"LoggingIn"})
public static void LogOnSelectedUserFromDatabase() throws SQLException, IOException
{
	Properties prop = new Properties();
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//MW//Resources//GlobalData.properties");
	prop.load(fis);
	String loginFromDatabase = System.getProperty("login") != null ? System.getProperty("login") : prop.getProperty("login");
	//String loginFromDatabase = prop.getProperty("login");
	AbstractComponent abstractComponent = new AbstractComponent(driver);
	List<String> loginAndPassword = abstractComponent.GetLoginInfoFromDatabase(loginFromDatabase);
	landingPage.manualLoggingIn(loginAndPassword.get(0), loginAndPassword.get(1));
}

@Test(groups= {"LoggingIn", "GeneralTest"})
public static void logOnAllUsers() throws InterruptedException
{
	ProductPage productPage = landingPage.loggingIn(1);
	String pageTitleStandardUser = landingPage.getPageTitle();
	Assert.assertTrue(pageTitleStandardUser.equalsIgnoreCase("products"));
	landingPage.logout();
	String blockedUserMessage = landingPage.checkLoggingOnBlockedUser();
	Assert.assertTrue(blockedUserMessage.contains("locked"));
	landingPage.loggingIn(3);
	String pageTitleProbUserTest = landingPage.getPageTitle();
	Assert.assertTrue(pageTitleProbUserTest.equalsIgnoreCase("products"));
	landingPage.logout();
	landingPage.loggingIn(4);
	String pageTitlePerfbUserTest = landingPage.getPageTitle();
	Assert.assertTrue(pageTitlePerfbUserTest.equalsIgnoreCase("products"));
	landingPage.logout();
	
}
}

