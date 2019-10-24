package com.sunnet.Pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sunnet.GenericLib.DatabaseFunction;
import com.sunnet.GenericLib.Extent_Reports;
import com.sunnet.GenericLib.GenericFunctions;
import com.sunnet.GenericLib.Synchronization;
import com.sunnet.GenericLib.Utilities;
import com.sunnet.utils.ProjectUtily;


public class PGLogin {	
	// =========================================Variables=================================================================================
	DatabaseFunction db = new DatabaseFunction();
	GenericFunctions GFObj=new GenericFunctions();
	private WebDriver driver;
	List<WebElement> elements;
	String status;
	public List<String> lstObject, lstTestData;

	WebElement objUserName,objPassWord,objLoginButton,objProfileElement,objLogo,objloginform,userName,logOut;
	
    String expurl="https://sunnetqa.anblicks.com/itoa/login.htmlx";
    
	// =============================Constructor to initialize all the Page Objects==========================================================
	public PGLogin(WebDriver driver) 

	{
		try 
		{
			this.driver = driver;
			lstObject = db.getTestDataObject("Select * from Loginpage", "ObjectRepository");
			lstTestData = db.getTestDataObject("Select * from Loginpage", "Input");
		} 
		catch (Exception exc) 
		{

			System.out.println("Exception in Constructor."+exc.getMessage());

		}
	}	
	

	// ================================BUSINESS VALIDATION LOGIC================================================= 


	public  void superUserLogin() throws Exception
	{
		Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
		try
		{
			Synchronization.waitForPageLoad(driver);
			Synchronization.implicitWait(driver, 6000);
			objUserName=Utilities.returnElement(driver, mapObjProjectData.get("Username").get(0),mapObjProjectData.get("Username").get(1));			objUserName.clear();
			objUserName.sendKeys(lstTestData.get(15));
			
			objPassWord=Utilities.returnElement(driver, mapObjProjectData.get("Password").get(0),mapObjProjectData.get("Password").get(1));
			objPassWord.clear();
			objPassWord.sendKeys(lstTestData.get(16));
			
			objLoginButton=Utilities.returnElement(driver, mapObjProjectData.get("Loginbtn").get(0),mapObjProjectData.get("Loginbtn").get(1));
			objLoginButton.click();
			
			Extent_Reports.executionLog("PASS","Login successful",driver);
		}
		catch(Throwable ex)
		{
			Extent_Reports.executionLog("FAIL","Login not successful",driver);
		}
		
		
	}
	
	public  void verifyLogin()
	{
		Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
		try
		{
          Synchronization.implicitWait(driver, 10);;
    

			for (int i=0;i<=lstTestData.size();) 
			{
				objUserName=Utilities.returnElement(driver, mapObjProjectData.get("Username").get(0),mapObjProjectData.get("Username").get(1));
				objUserName.sendKeys(lstTestData.get(i));
				Thread.sleep(2000);
				objPassWord=Utilities.returnElement(driver, mapObjProjectData.get("Password").get(0),mapObjProjectData.get("Password").get(1));
				objPassWord.sendKeys(lstTestData.get(i+1));
				Thread.sleep(2000);
				objLoginButton=Utilities.returnElement(driver, mapObjProjectData.get("Loginbtn").get(0),mapObjProjectData.get("Loginbtn").get(1));
				objLoginButton.click();
				status=lstTestData.get(i+2);
			

			 if(status.equalsIgnoreCase("valid"))
				{		
				 objLogo=Utilities.returnElement(driver, mapObjProjectData.get("Logo").get(0),mapObjProjectData.get("Logo").get(1));
					Boolean logo=objLogo.isDisplayed();
				Extent_Reports.executionLog("PASS", Extent_Reports.logExpected +"logged in successfully for valid credentials", driver);
					assertTrue(logo);
					Thread.sleep(2000);
					userName=Utilities.returnElement(driver, mapObjProjectData.get("userlogin").get(0),mapObjProjectData.get("userlogin").get(1));
					userName.click();
					logOut=Utilities.returnElement(driver, mapObjProjectData.get("userlogout").get(0),mapObjProjectData.get("userlogout").get(1));
					logOut.click();
					
			//	System.out.println("valid"+logo);

				}
				else
				{
				objloginform=Utilities.returnElement(driver, mapObjProjectData.get("Login").get(0),mapObjProjectData.get("Login").get(1));
				Boolean form=objloginform.isDisplayed();
				assertTrue(form);
				String validation= Utilities.returnElement(driver, mapObjProjectData.get("vm").get(0),mapObjProjectData.get("vm").get(1)).getText();
				 Extent_Reports.executionLog("PASS",Extent_Reports.logExpected + "Validation message displayed as   "+validation ,driver);
				}
		//	String validation= Utilities.returnElement(driver, mapObjProjectData.get("vm").get(0),mapObjProjectData.get("vm").get(1)).getText();
		//	 Extent_Reports.executionLog("PASS",Extent_Reports.logExpected + "Validation message displayed as   "+validation ,driver);
			 i=i+3;
		}}

		catch (Exception exc) 
		{

			System.out.println(exc.getMessage());
		}
	}

	
}



