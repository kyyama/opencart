package testCases;





import org.testng.Assert;
import org.testng.annotations.*;

import Utilities.DataProviders;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;


/*Data is valid  - login success - test pass  - logout
Data is valid -- login failed - test fail

Data is invalid - login success - test fail  - logout
Data is invalid -- login failed - test pass
*/



public class TC_003_LoginDDT  extends BaseClass{
	
	
	@Test(dataProvider="LoginData",dataProviderClass= DataProviders.class,groups="Datadriven")//getting data providers from different class
	public void  verify_login(String username,String password, String exp)throws InterruptedException  {
		
		logger.info("********* starting  TC_003_LoginDDT*********");
		
		try {
		//Home page
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin(); //Login link under MyAccount
			
		//Login page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(username);
		lp.setPassword(password);
		lp.clickLogin(); //Login button
			
		//My Account Page
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		
		
		/*Data is valid  - login success - test pass  - logout
		Data is valid -- login failed - test fail

		Data is invalid - login success - test fail  - logout
		Data is invalid -- login failed - test pass*/
		
		
		if(exp.equalsIgnoreCase("valid ")) {
			if(targetPage==true) 
			{
				macc.clickLogout();
				Assert.assertTrue(true);
				
				
			}
			else 
			{
				Assert.assertTrue(false);
			}
			
		}
		if(exp.equalsIgnoreCase("Invalid")) 
		{
			
			if(targetPage==true) 
			{
				macc.clickLogout();
				Assert.assertTrue(false);
				
				
			}
			else {
				Assert.assertTrue(true);
			}
			
		}
		
		}catch(Exception  e) {
			Assert.fail();
			
		}
		Thread.sleep(3000);
		
		logger.info("********* finished TC_003_LoginDDT*********");
		
		
		
		
	}
	

}
