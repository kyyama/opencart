package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest  extends BaseClass{
	@Test (groups={"Sanity","Master"})
	public void verify_login() throws InterruptedException
	{
		logger.info("****** starting TC_002_LoginTest********");
		
		try {
		//HomePage
		HomePage hp=new HomePage( driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//my account
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetpage =macc.isMyAccountPageExists();
		//Assert.assertEquals(targetpage, true,"Login failed");
		Assert.assertTrue(targetpage);
		
		}
		
		catch(Exception e) {
			Assert.fail();
			
		}
		Thread.sleep(3000);
		logger.info("****** finished TC_002_LoginTest********");
		
		
	}

}
