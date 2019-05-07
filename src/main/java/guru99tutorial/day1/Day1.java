package guru99tutorial.day1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * guru99 selenium tutorial, day 1
 * Requirements:
 * 1. Go to http://www.demo.guru99.com/V4/
 * 2. Enter valid UserId
 * 3. Enter valid Password
 * 4. Click Login
 * 
 * Access details to demo site.
 * User ID :	mngr193327
 * Password :	AsYvyhU
 */
public class Day1 {
	public static void main(String[] args) {
		/* task says to use Firefox, but I dont have firefox installed on this machine... */
		System.setProperty("webdriver.chrome.driver","C:\\Users\\wbutterworth\\eclipse-workspace\\Guru99-Selenium-Tutorial\\selenium-drivers\\chromedriver.exe");
		WebDriver driver = (WebDriver) new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		/* get the logon page */
		driver.get("http://www.demo.guru99.com/V4/");
		
		LoginPage loginPage;
		try {
			loginPage = PageFactory.initElements(driver, LoginPage.class);
		} catch (Exception e) {
			System.out.println("Unable to create login page: " + e.getLocalizedMessage());
			e.printStackTrace();
			driver.quit();
			return;
		}
		
		loginPage.setUidField("mngr193327");
		loginPage.setPasswordField("AsYvyhU");
		loginPage.clickLoginButton();
		
		
		// leave open so we can see the results
		//driver.quit();
	}
}
