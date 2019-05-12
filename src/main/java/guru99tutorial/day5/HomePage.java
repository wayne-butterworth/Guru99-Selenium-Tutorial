package guru99tutorial.day5;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
}
