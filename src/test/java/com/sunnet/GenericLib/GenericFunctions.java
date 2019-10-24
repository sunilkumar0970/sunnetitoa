package com.sunnet.GenericLib;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;


public class GenericFunctions {
	public WebDriver drivers;

		// ============================Genric Method to Scroll to an Element==================================
	public  void scrolltoElement(WebElement ScrolltoThisElement) {
		Coordinates coordinate = ((Locatable) ScrolltoThisElement)
		.getCoordinates();
		coordinate.onPage();
		coordinate.inViewPort();
		}
		
	   // ============================Genric Method to Validate content on all pages==================================
	public  void validatecontent(WebElement ActualText,String ExpectedText,WebDriver driver) throws Exception
	{
		drivers=driver;
		SoftAssert soft_Assert=new SoftAssert();
		try
		{
			Assert.assertEquals(ActualText.getText(), ExpectedText);
			Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "Expected Result is:"+ExpectedText.trim()
			+ Extent_Reports.logActual +"Actual Result:"+ ActualText.getText().trim(),driver);
			
			
		}
		catch(Throwable t)
		{
			soft_Assert.fail("Actual content '"+ActualText.getText()+"' And Expected Content '"+ActualText+"' do not match");
			Extent_Reports.executionLog("FAIL",Extent_Reports.logExpected+"Expected Result is:"+ExpectedText.trim()
			+Extent_Reports.logActual+"Actual Result is:"+ActualText.getText().trim(),driver);
			
		}
		
		}
	
	
	   // ============================Genric Method to click on all links on page==================================     
	public  void clickAllLinksInPage()
			throws Exception {

			List<WebElement> Links = drivers.findElements(By.tagName("a"));
			System.out.println("Total number of links :"+ Links.size());

			for (int p = 0; p < Links.size(); p++) {
			System.out.println("Elements present the body :"+Links.get(p).getText());
			Links.get(p).click();
			Thread.sleep(3000);
			System.out.println("Url of the page " + p +"" + drivers.getCurrentUrl());
			drivers.navigate().back();
			Thread.sleep(2000);
			}

			}
	// ============================Generic Method to move to an element================================== 
	
	public void Movetoelements(WebDriver driver,WebElement object) 
	{
		Actions action = new Actions(driver);
		action.moveToElement(object).build().perform();
		
	}
}
