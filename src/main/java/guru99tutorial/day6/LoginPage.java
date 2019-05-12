package guru99tutorial.day6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	@FindBy(name = "btnLogin")
    @CacheLookup
    private WebElement login;
	
	@FindBy(name = "uid")
    @CacheLookup
    private WebElement uidField;

    @FindBy(name = "password")
    @CacheLookup
    private WebElement passwordField;

    @FindBy(name = "btnReset")
    @CacheLookup
    private WebElement resetBtn;

	private WebDriver driver;
    
    public LoginPage( WebDriver driver) {
    	this.driver = driver;
    }
	/**
     * Click on Login Button.
     *
     * @return the guru99login class instance.
     */
    public HomePage clickLoginButton() {
        login.click();
        return PageFactory.initElements(driver, HomePage.class);
    }

    /**
     * click on the reset button
      * @return the guru99login class instance.
     */
    public LoginPage clickResetButton() {
    	resetBtn.click();
        return this;
    }
    
    /**
     * Set value to Welcome To The Online Banking Page Of Guru99 Bank Password field.
     *
     * @return the LoginPage class instance.
     */
    public LoginPage setUidField(String userId) {
    	uidField.clear();
        uidField.sendKeys(userId);
        return this;
    }
    
    /**
     * Set value to Welcome To The Online Banking Page Of Guru99 Bank Password field.
     *
     * @return the LoginPage class instance.
     */
    public LoginPage setPasswordField(String password) {
    	passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }
}
