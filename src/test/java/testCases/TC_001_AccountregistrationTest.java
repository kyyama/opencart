package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;
import org.apache.logging.log4j.Logger;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
//in setup method we need to set before process like driver setup and everything
import testBase.BaseClass;


public class TC_001_AccountregistrationTest extends BaseClass{
	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() throws InterruptedException
	{
		logger.info("***** starting  TC_001_AccountregistrationTest *******");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("clicked on my accountlink");
		hp.clickRegister();
		logger.info("clickedon register link");
		AccountRegistrationPage regpage	= new  AccountRegistrationPage(driver);
		
		logger.info("providing customer details.....");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");//randomly generated the email
		regpage.settelephone(randomNumber());
		
		String password=randomAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		logger.info("validating expected mesage.....");
		
	String confmsg=	regpage.getConfirmationMsg();
	if(confmsg.equals("Your Account Has Been Created!")) {
		AssertJUnit.assertTrue(true);
	}
	else {
		
		logger.error("test failed....");
		logger.debug("debug logs...");
		AssertJUnit.assertTrue(false);
		
	}
	//Assert.assertEquals(confmsg,"Your Account Has Been Created!!");
		}
	catch(Exception e) {
		
			AssertJUnit.fail();
		}
		
		Thread.sleep(3000);
		logger.info("***** finished TC_001_AccountregistrationTest *******");
			
		
	}
	
	
	
}
