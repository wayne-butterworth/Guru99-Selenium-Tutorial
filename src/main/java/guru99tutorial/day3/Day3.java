package guru99tutorial.day3;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * guru99 selenium tutorial, day 3
 * Parameterise the script to allow running with different values.
 * Test 1
 * Requirements:
 * 1. Go to http://www.demo.guru99.com/V4/
 * 2. Enter valid UserId
 * 3. Enter valid Password
 * 4. Click Login
 * 5. Verify the output
 * Expected result: the title of the new page is "Guru99 Bank Manager HomePage"
 * 
 * Test 2
 * As above, with Username = invalid, password = valid
 * Expected result: A pop-up "User or Password is not valid" is shown.
 * 
 * Test 3
 * As above, with Username = valid, password = invalid
 * Expected result: A pop-up "User or Password is not valid" is shown.
 * 
 * Test 4
 * As above, with Username = invalid, password = invalid
 * Expected result: A pop-up "User or Password is not valid" is shown.
 * 
 * Load the test parameters from Excel.
 */
public class Day3 {
	
	public static void main(String[] args) {
		/* get the test data */
		String [][] data = null;
		try {
			data = Util.getTestData();
		} catch (EncryptedDocumentException | IOException e) {
			print("Error opening test data spreadsheet '" + Util.FILE + "': " + e.getLocalizedMessage());
			e.printStackTrace();
			return;
		}
		
		/* make sure we have enough data */
		if ( data == null ) {
			print("No test data in spreadsheet?");
			return;
		}
		
		if ( data.length == 0 ) {
			print("No test data in spreadsheet?");
			return;
		}
		
		if ( data[0].length < 3) {
			print("Not enough columns in test data spreadsheet");
		}
		
		for( int i = 1; i <= data.length; i++ ) {
			print("Running test " + i + ":" );
			if ( doTest(data[i-1][0], data[i-1][1], data[i-1][2] ))
				print("Test " + i + " succeeded");
			else
				print("Test " + i + " FAILED");
		}
	}
	
	static Boolean doTest( String username, String password, String result ) {
		Boolean testresult = true;
		
		WebDriver driver = Util.getDriver();
		
		
		/* get the logon page */
		driver.get(Util.URL);
		
		LoginPage loginPage;
		try {
			loginPage = PageFactory.initElements(driver, LoginPage.class);
		} catch (Exception e) {
			System.out.println("Unable to create login page: " + e.getLocalizedMessage());
			e.printStackTrace();
			driver.quit();
			return false;
		}
		
		loginPage.setUidField(username);
		loginPage.setPasswordField(password);
		HomePage homePage = loginPage.clickLoginButton();
		
		/* we do we expect to happen? */
		if ( Util.ExpectPass(result)) {
			/* Make sure that we went to the correct page */
			if ( !homePage.getTitle().equalsIgnoreCase("Guru99 Bank Manager HomePage")) {
				print("Test FAILED: expected \"Guru99 Bank Manager HomePage\", received \"" + homePage.getTitle() + "\"" );
				testresult = false;
			}
		} else {
			/* we expect a popup */
			Alert alert;
			try {
				alert = driver.switchTo().alert();
				
				if ( !alert.getText().equalsIgnoreCase("User or Password is not valid") ) {
					print("Test FAILED: expected alert \"User or Password is not valid\", received \"" + alert.getText() + "\"" );
					testresult = false;
				}
				
				/* dismiss the alert */
				alert.accept();
			} catch (NoAlertPresentException e) {
				print("Test FAILED: expected popup - none found");
				testresult = false;
			}
		}

		driver.switchTo().parentFrame();
		driver.close();
		
		return testresult;
	}
	
	/**
	 * because I get tired of typing "System.out.println" all the time.
	 * @param message
	 */
	static void print( String message) {
		System.out.println(message);
	}
	
}
