package com.sunnet.GenericLib;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Synchronization 
{   
	
	static WebDriverWait wait;
	static WebElement elements;	
    private enum  Property 
    {
    	clickable, visible,display;
    }
    
	public static boolean  explicitWait(WebDriver driver,WebElement objectID,String objectProperty)
	{
		
		wait = new WebDriverWait(driver, 90);
		Property objProp = Property.valueOf(objectProperty.toLowerCase()); 
		switch(objProp)
		{
			case clickable:
				 elements = wait.until(ExpectedConditions.elementToBeClickable(objectID));
				 break;
				 
			case visible:
				 elements = wait.until(ExpectedConditions.visibilityOf(objectID));
				 break;
			case display:
				 elements = wait.until(ExpectedConditions.visibilityOf(objectID));
				 break;
		}
		return elements != null;
		
	}
//================================================================================================================================	
	public static void implicitWait(WebDriver driver,int Secs)
	{

			driver.manage().timeouts().implicitlyWait(Secs, TimeUnit.SECONDS);
	}
	
//================================================================================================================================
	public static boolean waitForPageLoad(WebDriver driver) 
	{

		//JavascriptExecutor je = (JavascriptExecutor) driver;
		int waitTime = 90 *1000;
		int counter =0;
		counter=0;
		//Number ajaxCount=-1;
		boolean flag=false;
		do{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			counter +=1000;

			try 
			{
				flag=((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				//ajaxCount=(Number) ((je)).executeScript("return dwr.engine._batchesLength");
				Thread.sleep(1000);
			} catch (Exception e) {

			}



		}while(flag==false && counter <waitTime);

		return false;

		
	}

}
