package guru99tutorial.day6;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.common.io.Files;

/**
 * Minimal HomePage object for Day 2 of tutorial
 * @author wbutterworth
 *
 */
public class HomePage {
	private WebDriver driver;
	
	/*
	 * The text we want is in a <td> with style="color: green".
	 * Fortunately there is only 1, so we can use xPath to find it.
	 */
	@FindBy(xpath="//td[@style='color: green']")
	WebElement welcomeText;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTitle() {
		return driver.getTitle();
	}

	public String getWelcomeText() {
		return welcomeText.getText();
	}

	public void takeScreenshot(String filename) {
		File s = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		File d = new File(filename);
		try {
			Files.move(s, d);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
