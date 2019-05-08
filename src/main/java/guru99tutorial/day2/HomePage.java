package guru99tutorial.day2;

import org.openqa.selenium.WebDriver;

/**
 * Minimal HomePage object for Day 2 of tutorial
 * @author wbutterworth
 *
 */
public class HomePage {
	private WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
}
