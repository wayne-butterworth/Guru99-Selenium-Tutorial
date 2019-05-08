package guru99tutorial.day2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * guru99 selenium tutorial, day 2
 * Requirements:
 * 1. Go to http://www.demo.guru99.com/V4/
 * 2. Enter valid UserId
 * 3. Enter valid Password
 * 4. Click Login
 * 5. Verify the output (verify the title of the Home Page)
 * 
 * Also:
 * a) specify the location of the browser ( already done in day1 )
 * b) specify the amount of time to wait when searching for a gui element ( already done in day1 )
 * c) code to set up and launch browser should be in a separate method
 * d) script initialisation parameters in Util class: "helps in easy maintenance" 
 * ( imho, a properties file would be better, but let's keep this simple )
 * 
 * Access details to demo site.
 * User ID :	mngr193327
 * Password :	AsYvyhU
 */
public class Day2 {
	
	public static void main(String[] args) {
		WebDriver driver = Util.getDriver();
		
		driver.manage().timeouts().implicitlyWait(Util.Timeout, TimeUnit.SECONDS);
		
		/* get the logon page */
		driver.get(Util.URL);
		
		LoginPage loginPage;
		try {
			loginPage = PageFactory.initElements(driver, LoginPage.class);
		} catch (Exception e) {
			System.out.println("Unable to create login page: " + e.getLocalizedMessage());
			e.printStackTrace();
			driver.quit();
			return;
		}
		
		loginPage.setUidField(Util.UID);
		loginPage.setPasswordField(Util.PASSWORD);
		HomePage homePage = loginPage.clickLoginButton();
		
		/* Make sure that we went to the correct page */
		if ( homePage.getTitle().equalsIgnoreCase("Guru99 Bank Manager HomePage")) {
			System.out.println("Test Succeeded");
		} else {
			System.out.println("Test FAILED: expected \"Guru99 Bank Manager HomePage\", received \"" + homePage.getTitle() + "\"" );
		}
		// leave open so we can see the results
		//driver.quit();
	}
	
}
