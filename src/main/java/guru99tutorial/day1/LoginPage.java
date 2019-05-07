package guru99tutorial.day1;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

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
	/**
     * Click on Login Button.
     *
     * @return the guru99login class instance.
     */
    public LoginPage clickLoginButton() {
        login.click();
        return this;
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
        uidField.sendKeys(userId);
        return this;
    }
    
    /**
     * Set value to Welcome To The Online Banking Page Of Guru99 Bank Password field.
     *
     * @return the LoginPage class instance.
     */
    public LoginPage setPasswordField(String password) {
        passwordField.sendKeys(password);
        return this;
    }
}
