package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		
		super(driver);
		
	}
	//these are elements
@FindBy(xpath="//span[normalize-space()='My Account']")	
	WebElement lnkMyaccount;
@FindBy(xpath="//a[normalize-space()='Register']")	
WebElement lnkRegister;	
@FindBy(linkText="Login")// login link added in  steps
WebElement lnkLogin;

//these are actions
public void  clickMyAccount() {
	lnkMyaccount.click();
	
}
public void  clickRegister() {
	lnkRegister.click();
	
}
public void clickLogin() {
	lnkLogin.click();
}
}
