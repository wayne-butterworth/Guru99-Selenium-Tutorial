package guru99tutorial.day4;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * guru99 selenium tutorial, day 4
 * Add TestNG to improve reporting.
 * 
 */
public class Day4 {
	
	private WebDriver driver;
	
	@BeforeMethod
	void setup() {
		driver = Util.getDriver();
		
		/* get the logon page */
		driver.get(Util.URL);
	}
	
	@Test(dataProvider="TestData",dataProviderClass=Util.class)
	void doTest( String username, String password, String result ) {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		
		loginPage.setUidField(username);
		loginPage.setPasswordField(password);
		HomePage homePage = loginPage.clickLoginButton();
		
		/* we do we expect to happen? */
		if ( Util.ExpectPass(result)) {
			/* Make sure that we went to the correct page */
			Assert.assertEquals(homePage.getTitle(), "Guru99 Bank Manager HomePage");
		} else {
			/* we expect a popup */
			Alert alert = driver.switchTo().alert();
				
			Assert.assertEquals(alert.getText(), "User or Password is not valid");
			
			/* dismiss the alert */
			alert.accept();
		}
	}
	
	@AfterMethod
	void close() {
		driver.switchTo().parentFrame();
		driver.close();
	}
	/**
	 * because I get tired of typing "System.out.println" all the time.
	 * @param message
	 */
	static void print( String message) {
		System.out.println(message);
	}
	
}
