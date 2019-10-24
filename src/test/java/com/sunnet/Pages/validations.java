package com.sunnet.Pages;

import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.sunnet.GenericLib.DatabaseFunction;
import com.sunnet.GenericLib.Extent_Reports;
import com.sunnet.GenericLib.GenericFunctions;
import com.sunnet.GenericLib.Utilities;
import com.sunnet.utils.ProjectUtily;



public class validations {
	
	 
	// =========================================Variables=================================================================================
			DatabaseFunction db = new DatabaseFunction();
			GenericFunctions GFObj=new GenericFunctions();
			private WebDriver driver;
			List<WebElement> elements;
			public List<String> lstObject, lstTestData;

			WebElement pentry,startDate,endDate,title,objSaveAndReturn,startLabel,ValidationMessage,projTyp,projNum;	
			
			// =============================Constructor to initialize all the Page Objects==========================================================
			public validations(WebDriver driver) 

			{
				try 
				{
					this.driver = driver;
					lstObject = db.getTestDataObject("Select * from objProjectEntry","ObjectRepository");
					lstTestData = db.getTestDataObject("Select * from validations","Input");
				} 
				catch (Exception exc) 
				{

					System.out.println("Exception in Constructor."+exc.getMessage());

				}
			}	

			// ================================BUSINESS VALIDATION LOGIC================================================= 
			

			
			public  void verifyMandatoryFields() throws Exception
			{
			
				Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
				
				try
				{				
						pentry=Utilities.returnElement(driver,mapObjProjectData.get("Project").get(0),mapObjProjectData.get("Project").get(1));
						pentry.click();
					
						int i=0;
						//List<ProjectDto> projectList = new ArrayList<ProjectDto>();
						while(i<(lstTestData.size()))
						{
							startDate=Utilities.returnElement(driver,mapObjProjectData.get("StartDate").get(0),mapObjProjectData.get("StartDate").get(1));
							endDate=Utilities.returnElement(driver,mapObjProjectData.get("Enddate").get(0),mapObjProjectData.get("Enddate").get(1));
							title=Utilities.returnElement(driver,mapObjProjectData.get("Title").get(0),mapObjProjectData.get("Title").get(1));
							objSaveAndReturn=Utilities.returnElement(driver,mapObjProjectData.get("saveandreturn").get(0),mapObjProjectData.get("saveandreturn").get(1));
							startDate.clear();
							startDate.sendKeys(lstTestData.get(i));
				
				         //  startLabel=Utilities.returnElement(driver, lstObject.get(7),
				          // lstObject.get(8)); startLabel.click();
				 
			                endDate.clear();
							endDate.sendKeys(lstTestData.get(i+1));
							projTyp = Utilities.returnElement(driver, mapObjProjectData.get("projtype").get(0),mapObjProjectData.get("projtype").get(1));
							Select pt=new Select(projTyp);
							pt.selectByVisibleText(lstTestData.get(i+2));
							projNum = Utilities.returnElement(driver, mapObjProjectData.get("projnum").get(0),mapObjProjectData.get("projnum").get(1));
							projNum.clear();
							projNum.sendKeys(lstTestData.get(i+3));
							title.clear();
							Thread.sleep(3000);
							title.sendKeys(lstTestData.get(i+4));
							objSaveAndReturn.click();
					Thread.sleep(2000);
							ValidationMessage=Utilities.returnElement(driver,mapObjProjectData.get("pentryvalidationmsg").get(0),mapObjProjectData.get("pentryvalidationmsg").get(1));
							if(ValidationMessage.isDisplayed())
							{
								Extent_Reports.executionLog("PASS",Extent_Reports.logExpected + "Validation message displayed"+ValidationMessage.getText() ,driver);
						   }
							else
						    {
								Extent_Reports.executionLog("FAIL",Extent_Reports.logExpected + "Validation message not displayed",driver);
							}
							
						Thread.sleep(2000);
							i=i+5;

						}
					
						
				}

				catch (Exception exc) 
				{

					System.out.println(exc.getMessage());
				}
			}
			
			
			
			
			
	
	
}
			
			
			
			
			
