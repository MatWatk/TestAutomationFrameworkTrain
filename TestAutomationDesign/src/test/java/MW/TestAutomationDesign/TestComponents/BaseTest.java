package MW.TestAutomationDesign.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import MW.TestAutomationDesign.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
public static WebDriver driver;	
public static LandingPage landingPage;
	
	public static WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//MW//Resources//GlobalData.properties");
		prop.load(fis);
		//String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver", "C:\\Users\\User\\Desktop\\Programowanie\\edgedriver_win64\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod
	public static LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	//@AfterMethod
	public void closingBrowser()
	{
		driver.close();
	}

}
