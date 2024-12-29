package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	//Constructors
	public HomePage(WebDriver driver) 
	{
		super(driver);
	}
	
	//Locators
	@FindBy(xpath="//a[@title='My Account']") WebElement myAccount;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement register;
	@FindBy(xpath="//a[normalize-space()='Login']") WebElement login;
	
	//Action Methods
	public void clickMyAccount()
	{
		myAccount.click();
	}
	
	public void clickRegister()
	{
		register.click();
	}
	
	public void clickLogin()
	{
		login.click();
	}

}
