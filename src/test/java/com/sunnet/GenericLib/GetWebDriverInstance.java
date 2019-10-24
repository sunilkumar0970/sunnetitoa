package com.sunnet.GenericLib;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;



//=========================================================CLASS & METHODS =============================================================================================
public class GetWebDriverInstance extends Extent_Reports {
	public static String env;
	private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	
	private static String  genericPath=System.getProperty("user.dir").concat("/src/test/resources/DriverExecutable/");
	protected WebDriver driver;
	public static  String USERNAME="" ;
	public static  String AUTOMATE_KEY="" ;
	public static  String URL="" ;
	DesiredCapabilities caps ;
	Properties prop = new Properties();
	//@SuppressWarnings("deprecation")
	@BeforeMethod
	
	@Parameters(value={"environment","browser","version","os","osversion"})
	public   void getBrowser(String environment,String browser,String version,String os,String osversion) throws MalformedURLException, InterruptedException 
	{
		
		env = environment;
		driver = null;
		
		try 
		{
// load a properties file
			InputStream input = new FileInputStream("data.properties");
			prop.load(input);

		} 
		catch (IOException ex) 
		{
			ex.printStackTrace();
		}
		

		switch (environment.toUpperCase()) 
		{
		
		case "FIREFOX":
			
						  if(os.toLowerCase().contains("mac"))
						  {
							  System.setProperty("webdriver.gecko.driver", genericPath+"geckodriver");
						  }
						  else
						  {
							  System.setProperty("webdriver.gecko.driver", genericPath+"geckodriver1.exe");
								
							  
						  }
			
						driver = drivers.get("Firefox");
						if (driver == null) 
						{
							driver = new FirefoxDriver();
							
						}
						driver.manage().window().maximize();
						break;
						
		case "IE":
		
				driver = drivers.get("IE");
				if (driver == null) 
				{
					System.setProperty("webdriver.ie.driver", genericPath+"IEDriverServer.exe");
	
					driver = new InternetExplorerDriver();
					drivers.put("IE", driver);
				}
				break;
				
		case "EDGE":
			
			driver = drivers.get("EDGE");
			if (driver == null) 
			{
				System.setProperty("webdriver.edge.driver", genericPath+"MicrosoftWebDriver.exe");

				driver = new EdgeDriver();
				drivers.put("EDGE", driver);
			}
			break;
				
		case "CHROME":
			
					  if(os.toLowerCase().contains("mac"))
					  {
						  System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
					  }
					  else
					  {
						 
							System.setProperty("webdriver.chrome.driver",genericPath+"chromedriver.exe");		
					  }
					  ChromeOptions options = new ChromeOptions(); 
					  options.addArguments("disable-infobars"); 
					  options.setExperimentalOption("useAutomationExtension", false);
					  driver = new ChromeDriver(options);
					  driver.manage().deleteAllCookies();
					  driver.manage().window().maximize();
		
			break;
			
			
		case "HEADLESS":
			driver = new HtmlUnitDriver();
			break;
		case "OPERA":	
			driver = new OperaDriver();
			break;

		case "SAFARI":
			if (os.toLowerCase().contains("mac")) {
				System.setProperty("webdriver.safari.driver", "/usr/local/bin/SafariDriver.safariextz");
			} else {
				System.setProperty("webdriver.safari.driver", "/usr/local/bin/SafariDriver.safariextz");

			}

			driver = drivers.get("Safari");
			if (driver == null) {
				driver = new SafariDriver();

			}
			driver.manage().window().maximize();


			//driver = new SafariDriver();
			break;
			
		}
		driver.get(prop.getProperty("URL").toString());
	}
	
    @AfterMethod(alwaysRun = true)
	public  void closeAllDriver() 
	{

		try
		{
			driver.quit();
		}
		catch(WebDriverException exc)
		{
			System.out.println("WebDriver Exception in CloseAllDriver " +exc.getMessage());
		}

	}



}
