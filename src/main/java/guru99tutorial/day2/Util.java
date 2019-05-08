package guru99tutorial.day2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Util {
	static final String CHROME_DRIVER_LOCATION = "C:\\Users\\wbutterworth\\eclipse-workspace\\Guru99-Selenium-Tutorial\\selenium-drivers\\chromedriver.exe";
	static final String PASSWORD = "AsYvyhU";
	static final String UID = "mngr193327";
	static final String URL = "http://www.demo.guru99.com/V4/";
	static final long Timeout = 30;

	static WebDriver getDriver() {
		/* task says to use Firefox, but I dont have firefox installed on this machine... */
		System.setProperty("webdriver.chrome.driver",CHROME_DRIVER_LOCATION);
		WebDriver driver = (WebDriver) new ChromeDriver();
		
		return driver;
	}
}
