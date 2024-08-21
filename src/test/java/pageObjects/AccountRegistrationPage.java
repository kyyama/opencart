package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	WebDriver driver;
	
	public AccountRegistrationPage(WebDriver driver) 
	{
		
		super(driver);
		
	}
	//here we can make every element as private
	//These are elements
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath="//input[@id='input-email']")	
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement 	txtTelephone;
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtconfirmpassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkdpolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btncontinue;

	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	//These are actions
	public void setFirstName( String fname) {
		txtFirstname.sendKeys(fname);
		
	}
	public void setLastName( String lname) {
		txtLastname.sendKeys(lname);
		
	}
	
	public void setEmail( String email) {
		txtEmail.sendKeys(email);
		
	}
	public void settelephone( String tel) {
		txtTelephone.sendKeys(tel);
		
	}
	public void setPassword( String pwd) {
		txtPassword.sendKeys(pwd);
		
	}
	public void setConfirmPassword( String pwd) {
		txtconfirmpassword.sendKeys(pwd);
		
	}
	
	public void setPrivacyPolicy() {
		chkdpolicy.click();
		
	}
	
	
	public void clickContinue() {
		//sol 1
		btncontinue.click();
		//sol 2
		//btncontinue.submit();
		//sol 3
		//Actions act=new Actions(driver);
		//act.moveToElement(btncontinue).click().perform();
		//sol 4
		//JavascriptExecutor js =(JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click();",btncontinue);
		
		//sol5
		//btncontinue.sendKeys(Keys.returns);
		//sol6
		//WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btncontinue)).click();
		
		
		
		
	}
	public String getConfirmationMsg() {
		try{
			return(msgConfirmation.getText());
		}catch(Exception e) {
			return(e.getMessage());
			
		}
	}
	
}
