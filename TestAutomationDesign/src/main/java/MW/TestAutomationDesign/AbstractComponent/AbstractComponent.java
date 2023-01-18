package MW.TestAutomationDesign.AbstractComponent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
	
	
}
