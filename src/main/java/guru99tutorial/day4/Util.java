package guru99tutorial.day4;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;

public class Util {
	static final String CHROME_DRIVER_LOCATION = "C:\\Users\\wbutterworth\\eclipse-workspace\\Guru99-Selenium-Tutorial\\selenium-drivers\\chromedriver.exe";
	static final String PASSWORD = "AsYvyhU";
	static final String UID = "mngr193327";
	static final String URL = "http://www.demo.guru99.com/V4/";
	static final long TIMEOUT = 30;
	static final String FILE = "C:\\Users\\wbutterworth\\eclipse-workspace\\Guru99-Selenium-Tutorial\\TestData.xlsx";
	private static final String DATA_SHEET_NAME = "Sheet1";

	static WebDriver getDriver() {
		/* task says to use Firefox, but I dont have firefox installed on this machine... */
		System.setProperty("webdriver.chrome.driver",CHROME_DRIVER_LOCATION);
		WebDriver driver = (WebDriver) new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		
		return driver;
	}
	
	/**
	 * read the test data from the input file.
	 * Returns a 2 dimensional string array, each row is a test case.
	 * Each column is test data, in the order username, password, expect pass.
	 * expect pass is true ( "T", "TRUE", etc ) if we expect the test to succeed, or false ( "F", "FALSE", etc ) if we expect the test to fail.
	 * @return
	 * @throws IOException 
	 * @throws EncryptedDocumentException 
	 */
	@DataProvider(name="TestData")
	static String[][] getTestData() throws EncryptedDocumentException, IOException {
		String[][] data = null;
		
		Workbook book = WorkbookFactory.create( new File( FILE ));
		
		Sheet sheet = book.getSheet(DATA_SHEET_NAME);
		
		int numRows = sheet.getLastRowNum(); // 0 based
		int numCols = sheet.getRow(0).getLastCellNum(); // 1 based
		
		data = new String[numRows+1][numCols];

		
		for( int i = 0; i <= numRows; i++ ) {
			Row row = sheet.getRow(i);
			
			for( int j = 0; j < numCols; j++ ) {
				Cell cell = row.getCell(j);
				
				data[i][j] = cell.getStringCellValue();
			}
		}
		
		book.close();
		
		return data;
	}
	
	/**
	 * Parse an expected result string to determine if we expect success or failure.
	 * @param result
	 * @return
	 */
	static Boolean ExpectPass(String result) {
		if ( result == null ) return false;
		
		if ( result.equalsIgnoreCase("T") || result.equalsIgnoreCase("TRUE") || result.equalsIgnoreCase("1") )
			return true;
		
		return false;
	}
	
	
}
