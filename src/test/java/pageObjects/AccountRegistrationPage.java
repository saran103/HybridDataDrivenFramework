package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	//Constructors
	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
	}
	
	//Locators
	@FindBy(xpath="//input[@id='input-firstname']") WebElement firstName;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement lastName;
	@FindBy(xpath="//input[@id='input-email']") WebElement eMail;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement telephoneNum;
	@FindBy(xpath="//input[@id='input-password']") WebElement password;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement passwordConfirm;
	@FindBy(xpath="//label[normalize-space()='No']") WebElement subscribeNo;
	@FindBy(xpath="//input[@name='agree']") WebElement agreeTerms;
	@FindBy(xpath="//input[@value='Continue']") WebElement continueBtn;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;
	
	//Action Methods
	public void setFirstName(String fname)
	{
		firstName.sendKeys(fname);
	}
	public void setLastName(String lname)
	{
		lastName.sendKeys(lname);
	}
	public void setEmail(String email)
	{
		eMail.sendKeys(email);
	}
	public void setTelephone(String telephone)
	{
		telephoneNum.sendKeys(telephone);
	}
	public void setPassword(String pwd)
	{
		password.sendKeys(pwd);
	}
	public void setConfirmPassword(String pwd)
	{
		passwordConfirm.sendKeys(pwd);
	}
	public void setSubscribe()
	{
		subscribeNo.click();
	}
	public void setTermsAndCondtions()
	{
		agreeTerms.click();
	}
	public void clickContinueBtn()
	{
		continueBtn.click();
	}
	
	/*If click method doesn't work:
	 * 1st option: continueBtn.submit()
	 * 
	 * 2nd Option: 
	 * Actions act = new Actions(driver);
	 * act.moveToElement(act).click().perform();
	 * 
	 * 3rd Option:
	 * JavaScriptExecutor js = (JavaSCriptExecutor)driver;
	 * js.executeScript("arguments[0].click;", continueBtn);
	 * 
	 * 4th Option:
	 * continueBtn.sendKeys(Keys.RETURN);
	 * 
	 * 5th Option:
	 * WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 * myWait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
	 * 
	 */
	
	public String getConfirmationString()
	{
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
		
	}
}
