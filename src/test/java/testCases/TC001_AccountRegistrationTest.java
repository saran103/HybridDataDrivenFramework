package testCases;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups="Sanity")
	@Parameters({"browser"})
	public void verify_account_registration(String br) throws InterruptedException
	{
		logger.info("***** Starting TC001_AccountRegistrationTest on "+br+ " browser***");
		logger.debug("This is a debug log message on "+br+ " browser");
		try
		{
		HomePage home = new HomePage(driver);
		
		home.clickMyAccount();
		logger.info("Clicked on MyAccount Link.. "+br+ " browser");
		
		home.clickRegister();
		logger.info("Clicked on Register Link.. "+br+ " browser");
		
		AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details..."+br+ " browser");
		regPage.setFirstName("Kaniyan");
		regPage.setLastName("Poogundran");
		//regPage.setEmail(randomString()+"@gmail.com");
		regPage.setEmail(prop.getProperty("email"));
		regPage.setTelephone("8976542356");
		regPage.setPassword(prop.getProperty("pwd"));
		regPage.setConfirmPassword(prop.getProperty("pwd"));
		regPage.setSubscribe();
		regPage.setTermsAndCondtions();
		regPage.clickContinueBtn();
		
		logger.info("Validating expected message.."+br+ " browser");
		
		String confMsg = regPage.getConfirmationString();
		Assert.assertEquals(confMsg, "Your Account Has Been Created!");
		logger.info("Test passed "+br+ " browser");
		} 
		catch (Exception e)
		{
			logger.error("Test failed: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		} 
		finally 
		{
		logger.info("***** Finished TC001_AccountRegistrationTest on "+br+ " browser****");
		}
		
	}
	
	


}
