package com.sunnet.GenericLib;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utilities 
{
//	private static WebDriver driver;
	// Refreshing DOM Elements
	public static WebElement returnElement(WebDriver driver,String locatorType, String locatorName) {
	    switch (locatorType.toLowerCase()) {
	    case "id":
	        return driver.findElement(By.id(locatorName));
	    case "idCollection":
	    	 return (WebElement) driver.findElements(By.id(locatorName));
	    case "xpath":
	        return driver.findElement(By.xpath(locatorName));
	    
	    case "name":
	        return driver.findElement(By.name(locatorName));

	    case "classname":
	        return driver.findElement(By.className(locatorName));
	        
	    case "classnamecollection":
	        List<WebElement> labels = driver.findElements(By.className(locatorName));
	        		return (WebElement) labels;
	    case "cssselector":
	        return driver.findElement(By.cssSelector(locatorName));

	    case "linktext":
	        return driver.findElement(By.linkText(locatorName));

	    case "tagname":
	        return driver.findElement(By.tagName(locatorName));
	    case "partialLinkText":
	    	return driver.findElement(By.partialLinkText(locatorName));

	    default:
	        throw new RuntimeException("Unknown locator " + locatorType + " : " + locatorName);
	    }
	}
	
	public static List<WebElement> returnElements(WebDriver driver,String locatorType, String locatorName) {
	    switch (locatorType.toLowerCase()) {
	    case "id":
	        return driver.findElements(By.id(locatorName));
	    
	    case "xpath":
	        return driver.findElements(By.xpath(locatorName));
	    
	    case "name":
	        return driver.findElements(By.name(locatorName));

	    case "classname":
	        return driver.findElements(By.className(locatorName));
	        
	  
	    case "cssselector":
	        return driver.findElements(By.cssSelector(locatorName));

	    case "linktext":
	        return driver.findElements(By.linkText(locatorName));

	    case "tagname":
	        return driver.findElements(By.tagName(locatorName));

	    default:
	        throw new RuntimeException("Unknown locator " + locatorType + " : " + locatorName);
	    }
	}
		
	
	public static String getPropertiesValue(String proeprtyName) throws IOException
	{
					Properties prop = new Properties();
		// load a properties file
					InputStream input = new FileInputStream("data.properties");
					prop.load(input);
					String propVal=prop.getProperty("URL").toString();
					return propVal;
	}
}
