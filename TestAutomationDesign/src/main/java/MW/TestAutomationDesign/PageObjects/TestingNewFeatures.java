package MW.TestAutomationDesign.PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class TestingNewFeatures {

	public static void main(String[] args) {
		textBoxesFullCheck();

	}
	public static void textBoxesFullCheck()
	{

		List<String> dataTypesForTextboxes = new ArrayList<String>();
		dataTypesForTextboxes.add("123");
		dataTypesForTextboxes.add("abc");
		System.out.print(dataTypesForTextboxes.get(0));
		System.out.print(dataTypesForTextboxes.get(1));
		for (int i=0; i<=1; i++)
		{
			driver.findElement(By.id("first-name")).sendKeys(dataTypesForTextboxes.get(i));
			for (int j=0; j<=1; j++)
			{
				for (int k=0; k<=1; k++)
				{
					
				}
			}
		}
}
}
