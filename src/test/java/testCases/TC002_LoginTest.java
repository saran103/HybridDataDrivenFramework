package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups="Regression")
	public void verify_login()
	{
		logger.info("***** Starting TC002_LoginTest*****");
		
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("**** Clicked My Account link in home page...****");
			hp.clickLogin();
			logger.info("**** Clicked login link in home page...****");
			
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(prop.getProperty("email"));
			lp.setPassword(prop.getProperty("pwd"));
			lp.clickLogin();
			
			MyAccountPage acctPage = new MyAccountPage(driver);
			boolean isAcctPageExist = acctPage.isMyAccountPageExists();
			Assert.assertEquals(isAcctPageExist, true, "Login failed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.fail();
		}
		
		logger.info("***** Finished TC002_LoginTest*****");
	}

}
