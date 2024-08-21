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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import org.openqa.selenium.remote.RemoteWebDriver;

import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;   //log4j


public class BaseClass {
	
public  static WebDriver driver;
public Logger logger;
public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setUp(@Optional("Windows")String os,@Optional("chrome") String br) throws IOException {
		
		
		
		//loading config.properties file
		FileReader file=new FileReader("./src/test/resources/config.properties");
		p=new Properties();
		p.load(file);
		
		
		logger= LogManager.getLogger(this.getClass());//log4j
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap =new DesiredCapabilities();
			cap.setBrowserName(br.toLowerCase());
			
			//after desiered capabilities we need to set two things// os,//browsername
			
			//os 
			if(os.equalsIgnoreCase("MacOs"))
			{
				
			cap.setPlatform(Platform.MAC);
		}
		  else if  (os.equalsIgnoreCase("Windows"))
		
		{
			cap.setPlatform(Platform.WIN10);
			
		}
		  else 
		  {
			  System.out.println("no matching os");
			  return;
		  }
		
		
		
		//browser
			switch(br.toLowerCase()) 
			{
			case "chrome":cap.setBrowserName("chrome"); break;
			case "edge":cap.setBrowserName("MicrosoftEdge"); break;
			case "firefox":cap.setBrowserName("firefox");break;
			default:System.out.println("no matching browser");return;
			
			
			}
			//grid 
			driver=new RemoteWebDriver(new URL("http://192.168.1.147:4444/wd/hub"),cap);
			
			
			}	
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) 
		{
			switch(br.toLowerCase()) 
			{
			case "chrome":driver=new ChromeDriver(); break;
			case "edge":driver=new EdgeDriver();break;
			case "firefox":driver=new FirefoxDriver();break;
			default:System.out.println("invalid browser name..."); return;
			}
			
		}
			
			
		
		
		//the below code is for local environment
		/*switch(br.toLowerCase()) {
		case "chrome":driver=new ChromeDriver(); break;
		case "edge":driver=new EdgeDriver();break;
		case "firefox":driver=new FirefoxDriver();break;
		default:System.out.println("invalid browser name..."); return;
		}*/
		
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get("http://localhost/opencart/upload/index.php");
		driver.get(p.getProperty("appURL1"));//reading url  from  properties
		driver.manage().window().maximize();
		
	}
	@AfterClass(groups= {"Sanity","Regression","Master"})
    public void tearDown() {
		driver.close();
	

	}
	public String randomeString() {
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
		
	}
	
	
	public String randomNumber() {
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
	public String randomAlphaNumeric() {
		String str=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		return(str+"@"+num);
			
	}
	//for capturing screenshot with time stamp
	//when we have to execute this method where we have to execute this  method
	//whenevere a test method got failed  then we will execute this method 
	//from where we will execute this method ,how we will know that test method got failed  at run time
	//in base class if test method fails on testfailure method will be triggered we should call captureScreen method 
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"/screenshots/" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
	
	

}
