package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;   //log4j

public class BaseClass {
	
public static WebDriver driver;
public Logger logger;
public Properties prop;
	
	@BeforeClass(groups={"Sanity","Regression"})
	@Parameters({"os","browser"})
	public void setUp(String os, String br) throws IOException
	{
		logger=LogManager.getLogger(this.getClass());//Log4j
		
		FileReader file = new FileReader("./src//test//resources//config.properties");
		prop = new Properties();
		prop.load(file);
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			
			//OS
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN11);
			} else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				cap.setPlatform(Platform.LINUX);
			}
			else 
			{
				System.out.println("No Matching OS Found");
				return;
			}
			
			//Browser
			switch(br.toLowerCase())
			{
			case "chrome": 
				cap.setBrowserName("chrome");
				break;
			case "edge":
				cap.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				cap.setBrowserName("firefox");
				break;
			default:
				System.out.println("No matching browser");
				return;
			}
		driver = new RemoteWebDriver(new URL("http://10.0.0.153:4444/wd/hub"), cap);	
		}
	
	if(prop.getProperty("execution_env").equalsIgnoreCase("local"))
	{
		switch(br.toUpperCase())
		{
		case "CHROME":
			driver = new ChromeDriver();
			break;
		case "EDGE":
			driver = new EdgeDriver();
			break;
		case "FIREFOX":
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Driver not found");
			return;
		}
	}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups={"Sanity","Regression"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomString()
	{
		
		String randomText = RandomStringUtils.randomAlphabetic(10);
		System.out.println(randomText);
		return randomText;
	}
	
	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+ tname + "_" + timeStamp+".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
	
	

}
