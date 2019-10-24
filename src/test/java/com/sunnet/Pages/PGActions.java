package com.sunnet.Pages;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.sunnet.GenericLib.DatabaseFunction;
import com.sunnet.GenericLib.Extent_Reports;
import com.sunnet.GenericLib.GenericFunctions;
import com.sunnet.GenericLib.Synchronization;
import com.sunnet.GenericLib.Utilities;
import com.sunnet.utils.ProjectUtily;

public class PGActions {
	// =========================================Variables=================================================================================
			DatabaseFunction db = new DatabaseFunction();
			GenericFunctions gfObj = new GenericFunctions();
			private WebDriver driver;
			List<WebElement> elements;
			public List<String> lstObject, lstTestData;
			WebElement pentry, startDate, endDate, title, startLabel, desc, projTyp, projItem, costCentre, costCentreItem,projmngrop,commissionmngr,systemType,workType,requestType,reqstSubmit,action,requestSubmit,filterStartDate,endDateLabel,
					projNum, wrkCentre, saveAndReturn, logo, projList, filter, titleFilter,addoutage,newProj,save,Return,srchResult1,projManager,selectPM,selectCM,createRequest,schdleProfile,schdleItem,comment,confirmSubmit,filterEndDate,actions;
			String RequestEntryNumber,reqNum,viewpageoutage,value,name;
			String[] params1,params;
			boolean srchResult;
			

		// =============================Constructor to initialize all the Page Objects==========================================================
			public PGActions(WebDriver driver)

			{

				try {
					this.driver = driver;
					lstObject = ProjectUtily.getObjProjectData(db);
					lstTestData = db.getTestDataObject("Select * from Actions", "Input");
				} catch (Exception exc) {

					System.out.println("Exception in Constructor." + exc.getMessage());

				}
			
			}
			// ================================BUSINESS VALIDATION LOGIC=================================================

			public void verifyActions() throws Exception {
			//	Random random = new Random();
				Map<String, String> map = new HashMap<String, String>();

				try {

					Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
					
					projList = Utilities.returnElement(driver, mapObjProjectData.get("ProjectList").get(0),mapObjProjectData.get("ProjectList").get(1));
					projList.click(); 
				/*	Thread.sleep(3000);
					newProj =Utilities.returnElement(driver, mapObjProjectData.get("newproject").get(0),mapObjProjectData.get("newproject").get(1));
					newProj.click();
					Thread.sleep(3000);*/
					
					int i = 0;
					while (i < (lstTestData.size())) {
					//	projList = Utilities.returnElement(driver, mapObjProjectData.get("ProjectList").get(0),mapObjProjectData.get("ProjectList").get(1));
					//	projList.click(); 
						Thread.sleep(3000);
						newProj =Utilities.returnElement(driver, mapObjProjectData.get("newproject").get(0),mapObjProjectData.get("newproject").get(1));
						newProj.click();
						Thread.sleep(3000);
						startDate = Utilities.returnElement(driver, mapObjProjectData.get("StartDate").get(0),
								mapObjProjectData.get("StartDate").get(1));
						startDate.sendKeys(lstTestData.get(i));
						Thread.sleep(3000);
						startLabel = Utilities.returnElement(driver, mapObjProjectData.get("StartdateLabel").get(0),
								mapObjProjectData.get("StartdateLabel").get(1));
						startLabel.click();
						endDate = Utilities.returnElement(driver, mapObjProjectData.get("Enddate").get(0),
								mapObjProjectData.get("Enddate").get(1));
						endDate.clear();
						endDate.sendKeys(lstTestData.get(i + 1));
						title = Utilities.returnElement(driver, mapObjProjectData.get("Title").get(0),
								mapObjProjectData.get("Title").get(1));
						Thread.sleep(3000);

						title.sendKeys(lstTestData.get(i + 2));
						desc = Utilities.returnElement(driver, mapObjProjectData.get("Description").get(0),
								mapObjProjectData.get("Description").get(1));
						desc.sendKeys(lstTestData.get(i + 3));
						Thread.sleep(4000);
						projTyp = Utilities.returnElement(driver, mapObjProjectData.get("projtype").get(0),
								mapObjProjectData.get("projtype").get(1));
						Select pt=new Select(projTyp);
						pt.selectByIndex(1);
						projNum = Utilities.returnElement(driver, mapObjProjectData.get("projnum").get(0),
								mapObjProjectData.get("projnum").get(1));
						projNum.sendKeys(lstTestData.get(i + 4));
						Thread.sleep(3000);
						costCentre = Utilities.returnElement(driver, mapObjProjectData.get("costcentre").get(0),
								mapObjProjectData.get("costcentre").get(1));
						Select st=new Select(costCentre);
						st.selectByIndex(1);
						wrkCentre = Utilities.returnElement(driver, mapObjProjectData.get("WorkCentre").get(0),
								mapObjProjectData.get("WorkCentre").get(1));
						wrkCentre.sendKeys(lstTestData.get(i + 5));
						Synchronization.implicitWait(driver,10);
						projManager=Utilities.returnElement(driver, mapObjProjectData.get("ProjectManager").get(0), mapObjProjectData.get("ProjectManager").get(1));
						projManager.click();
						selectPM=Utilities.returnElement(driver, mapObjProjectData.get("ProjManagerItems").get(0), mapObjProjectData.get("ProjManagerItems").get(1));
						selectPM.sendKeys(Keys.ARROW_DOWN);
						selectPM.sendKeys(Keys.ARROW_DOWN);
						selectPM.sendKeys(Keys.ENTER);	
						commissionmngr=Utilities.returnElement(driver, mapObjProjectData.get("CommManager").get(0), mapObjProjectData.get("CommManager").get(1));
						commissionmngr.click();
						//Synchronization.implicitWait(driver,10);
						selectCM=Utilities.returnElement(driver, mapObjProjectData.get("selectCommManager").get(0), mapObjProjectData.get("selectCommManager").get(1));
						selectCM.sendKeys(Keys.ARROW_DOWN);
						selectCM.sendKeys(Keys.ENTER);
					Thread.sleep(3000);
						save = Utilities.returnElement(driver, mapObjProjectData.get("saveandreturn").get(0),
								mapObjProjectData.get("saveandreturn").get(1));
						save.click();
						Thread.sleep(1000);
						String projectNum=driver.findElement(By.xpath("//*[@class='inlineBlock']")).getText();
						String[] viewHeader= projectNum.split("#");
						String projectnumber1=viewHeader[1];
						System.out.println(projectnumber1);
					 
						actions=Utilities.returnElement(driver, mapObjProjectData.get("Actions").get(0),
								mapObjProjectData.get("Actions").get(1));
						actions.click();
						Thread.sleep(1000);
						driver.findElement(By.linkText("APPROVE")).click();
						 for(int k=0;k<viewHeader.length;k++) { 
							 String status=driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[1]/h3[2]")).getText();
							 Return=Utilities.returnElement(driver, mapObjProjectData.get("return").get(0),mapObjProjectData.get("return").get(1));
							 Return.click();
						 filterStartDate = Utilities.returnElement(driver, mapObjProjectData.get("FilterStartDate").get(0),mapObjProjectData.get("FilterStartDate").get(1));
							filterStartDate.clear();
							Thread.sleep(2000);
							filterStartDate.sendKeys("07/01/2019");
							Thread.sleep(3000);
							filterEndDate=Utilities.returnElement(driver, mapObjProjectData.get("FilterEndDate").get(0),mapObjProjectData.get("FilterEndDate").get(1));
							filterEndDate.clear();
							Thread.sleep(2000);
							filterEndDate.sendKeys("08/25/2019");
							Thread.sleep(2000);
							endDateLabel = Utilities.returnElement(driver, mapObjProjectData.get("Enddatelabel").get(0),mapObjProjectData.get("Enddatelabel").get(1));
							endDateLabel.click();
							filterEndDate.sendKeys(Keys.ENTER);
						filter = Utilities.returnElement(driver, mapObjProjectData.get("Filtericon").get(0),
								mapObjProjectData.get("Filtericon").get(1));
						Thread.sleep(3000);
						filter.click();		
						titleFilter = Utilities.returnElement(driver, mapObjProjectData.get("projecttitle").get(0),
								mapObjProjectData.get("projecttitle").get(1));
						titleFilter.sendKeys(projectnumber1);
						Thread.sleep(3000);
						srchResult1 = Utilities.returnElement(driver, mapObjProjectData.get("verification").get(0),
								mapObjProjectData.get("verification").get(1));
						srchResult=srchResult1.isDisplayed();
						if (srchResult == true) {
						String projectStauts=driver.findElement(By.xpath("//*[@id='projectList']/tbody/tr[1]/td[7]/div")).getText();
						assertEquals(status, projectStauts);
						srchResult1.click();
						Thread.sleep(5000);
						Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + status+" Status Created Working", driver);
						String status1=driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div[1]/h3[2]")).getText();
						if(status1.equalsIgnoreCase("completed"))
						{
						Return=Utilities.returnElement(driver, mapObjProjectData.get("return").get(0),mapObjProjectData.get("return").get(1));
						 Return.click();	
						}
						else {
					//problem from this step
						for (int j = 1; j > i; j--) {
						actions=Utilities.returnElement(driver, mapObjProjectData.get("Actions").get(0),
								mapObjProjectData.get("Actions").get(1));
						actions.click();
						 Thread.sleep(1000);
						driver.findElement(By.linkText("COMPLETE")).click();
						}}
						for (int l = 5; l < i; l++) {
							actions=Utilities.returnElement(driver, mapObjProjectData.get("Actions").get(0),
									mapObjProjectData.get("Actions").get(1));
							actions.click();
							 Thread.sleep(1000);
							driver.findElement(By.linkText("CANCEL")).click();
							Thread.sleep(1000);
							driver.findElement(By.id("comments")).sendKeys("cancel status");
							Thread.sleep(1000);
							driver.findElement(By.linkText("Confirm Cancel")).click();
							Thread.sleep(2000);
							Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + status+" Status Created Working", driver);
	
						}
					
						}}i=i+6;
						}}
					catch (Exception exc) {

						System.out.println(exc.getMessage());
						exc.printStackTrace();
					}}
			
			
			
			
			
			
			
			
			
			
}