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
import MW.TestAutomationDesign.TestComponents.BaseTest;

public class LoginrValidation extends BaseTest{

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

@Test
public static void LogOnSelectedUserFromDatabase() throws SQLException, IOException
{
	Properties prop = new Properties();
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//MW//Resources//GlobalData.properties");
	prop.load(fis);
	//String loginFromDatabase = System.getProperty("login" != null ? System.getProperty("login") : prop.getProperty("login");
	String loginFromDatabase = prop.getProperty("login");
	AbstractComponent abstractComponent = new AbstractComponent(driver);
	List<String> loginAndPassword = abstractComponent.GetLoginInfoFromDatabase(loginFromDatabase);
	landingPage.manualLoggingIn(loginAndPassword.get(0), loginAndPassword.get(1));
}
}

