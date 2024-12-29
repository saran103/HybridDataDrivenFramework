package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups= {"Sanity","Regression"})
	public void verify_loginDTT(String email, String pwd, String exp)
	{
		logger.info("***** Starting TC003_LoginDDT*****");
		
	try
	{
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("**** Clicked My Account link in home page...****");
			hp.clickLogin();
			logger.info("**** Clicked login link in home page...****");
			
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();
			
			MyAccountPage acctPage = new MyAccountPage(driver);
			boolean isAcctPageExist = acctPage.isMyAccountPageExists();
			
			if(exp.equalsIgnoreCase("valid"))
			{
				if(isAcctPageExist == true)
				{
					acctPage.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertFalse(true);
				}
			}
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(isAcctPageExist == true)
				{
					acctPage.clickLogout();
					Assert.assertTrue(false);
				}
				else {
					Assert.assertTrue(false);
				}
			}
	}
	catch(Exception e)
	{
		Assert.fail();
	}
		
		logger.info("***** Finished TC002_LoginTest*****");
	}

}
