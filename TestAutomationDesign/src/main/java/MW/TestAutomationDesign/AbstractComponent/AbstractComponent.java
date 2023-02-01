package MW.TestAutomationDesign.AbstractComponent;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v108.emulation.Emulation;
import org.openqa.selenium.support.FindBy;

import MW.TestAutomationDesign.PageObjects.LandingPage;

public class AbstractComponent {
WebDriver driver;

@FindBy (xpath="//div[@class='cart_item']")
static
List <WebElement> allProductsFromCart;

	public AbstractComponent(WebDriver driver)
	{
		this.driver = driver;
	}

	public static Double PricePreparation(String price)
	{
		// Preparing data for convertion into double
		String replacedPrice = price.replace("$", " ").trim();
		double convertedPrice = Double.parseDouble(replacedPrice);
		return convertedPrice;
	}	
	
	public static List<Double> GetAllProductPrices()
	{
		List <Double> allCartProductPrices = allProductsFromCart.stream().map(p->PricesCheckout(p)).collect(Collectors.toList());
		return allCartProductPrices;
	}
	
	public static Double PricesCheckout(WebElement p)
	{
		String price = p.findElement(By.xpath("div/div/div")).getText();
		Double preparedPrice = PricePreparation(price);
		return preparedPrice;
	}
	public static List<String> GetLoginInfoFromDatabase(String username) throws SQLException
	{
		String host = "localhost";
		String port = "3306";
		Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port +"/FrameworkDB", "root", "test123");
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery("select * from LoginAndPassword where login = '" + username + "'");
		List<String> loginThings = new ArrayList<String>();
		while (rs.next())
		{
			String loginFromDatabase = rs.getString("login");
			String passwordFromDatabase = rs.getString("password");
			loginThings.add(loginFromDatabase);
			loginThings.add(passwordFromDatabase);
		}
		return loginThings;
	}

	public ChromeDriver initDriverDev()
	{
		ChromeDriver driverDev = new ChromeDriver();
		return driverDev;
	}
	public void takescreenshotOnMobileVersion(ChromeDriver driverDev) throws IOException
	{
		DevTools devTools = driverDev.getDevTools();
		devTools.createSession();
		driverDev.get("https://www.saucedemo.com/");
		devTools.send(Emulation.setDeviceMetricsOverride(400, 634, 100, true, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
		File src = ((TakesScreenshot)driverDev).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"//screenshot_mobile//mobile_version_screenshot.png"));
		devTools.close();
		driverDev.close();
	}
}
