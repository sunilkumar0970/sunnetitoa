package com.sunnet.Pages;

//import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.sunnet.GenericLib.DatabaseFunction;
import com.sunnet.GenericLib.Extent_Reports;
import com.sunnet.GenericLib.GenericFunctions;
import com.sunnet.GenericLib.Utilities;
import com.sunnet.utils.ProjectUtily;

public class PGProjectEntryWithCreateRequest {
	// =========================================Variables=================================================================================
		DatabaseFunction db = new DatabaseFunction();
		GenericFunctions gfObj = new GenericFunctions();
		private WebDriver driver;
		List<WebElement> elements;
		public List<String> lstObject, lstTestData;
		WebElement pentry, startDate, endDate, title, startLabel, desc, projTyp, projItem, costCentre, costCentreItem,projmngrop,commissionmngr,systemType,workType,requestType,reqstSubmit,action,requestSubmit,filterStartDate,endDateLabel,
				projNum, wrkCentre, saveAndReturn, logo, projList, filter, titleFilter,addoutage,newProj,save,Return,srchResult1,projManager,selectPM,selectCM,createRequest,schdleProfile,schdleItem,comment,confirmSubmit,filterEndDate;
		String RequestEntryNumber,reqNum,viewpageoutage,value,name;
		String[] params1,params;
		boolean srchResult;
		

		// =============================Constructor to initialize all the Page Objects==========================================================
		public PGProjectEntryWithCreateRequest(WebDriver driver)

		{

			try {
				this.driver = driver;
				lstObject = ProjectUtily.getObjProjectData(db);
				lstTestData = db.getTestDataObject("Select * from EditProjectEntryWithCreateRequest", "Input");
			} catch (Exception exc) {

				System.out.println("Exception in Constructor." + exc.getMessage());

			}
		}

		// ================================BUSINESS VALIDATION LOGIC=================================================

		public void verifyProjectEntryBySave() throws Exception {
		//	Random random = new Random();
			Map<String, String> map = new HashMap<String, String>();

			try {

				Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
			//	Map<String, String> map = new HashMap<String, String>();
				
				projList = Utilities.returnElement(driver, mapObjProjectData.get("ProjectList").get(0),mapObjProjectData.get("ProjectList").get(1));
				projList.click(); 
				Thread.sleep(3000);
				newProj =Utilities.returnElement(driver, mapObjProjectData.get("newproject").get(0),mapObjProjectData.get("newproject").get(1));
				newProj.click();
				Thread.sleep(3000);
				
				int i = 0;
				while (i < (lstTestData.size())) {
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
					//projItem = Utilities.returnElement(driver, mapObjProjectData.get("Projtypitem").get(0),
					//		mapObjProjectData.get("Projtypitem").get(1));
				//	projItem.click();
					projNum = Utilities.returnElement(driver, mapObjProjectData.get("projnum").get(0),
							mapObjProjectData.get("projnum").get(1));
					projNum.sendKeys(lstTestData.get(i + 4));
					Thread.sleep(3000);
					costCentre = Utilities.returnElement(driver, mapObjProjectData.get("costcentre").get(0),
							mapObjProjectData.get("costcentre").get(1));
					Select st=new Select(costCentre);
					st.selectByIndex(1);
				//	costCentre.click();
				//	costCentreItem = Utilities.returnElement(driver, mapObjProjectData.get("costcentreitem").get(0),
				//			mapObjProjectData.get("costcentreitem").get(1));
				//	costCentreItem.click();
					wrkCentre = Utilities.returnElement(driver, mapObjProjectData.get("WorkCentre").get(0),
							mapObjProjectData.get("WorkCentre").get(1));
					wrkCentre.sendKeys(lstTestData.get(i + 5));
					projManager=Utilities.returnElement(driver, mapObjProjectData.get("ProjectManager").get(0), mapObjProjectData.get("ProjectManager").get(1));
					projManager.click();
					selectPM=Utilities.returnElement(driver, mapObjProjectData.get("ProjManagerItems").get(0), mapObjProjectData.get("ProjManagerItems").get(1));
					selectPM.sendKeys(Keys.ARROW_DOWN);
					selectPM.sendKeys(Keys.ARROW_DOWN);
					selectPM.sendKeys(Keys.ENTER);	
					commissionmngr=Utilities.returnElement(driver, mapObjProjectData.get("CommManager").get(0), mapObjProjectData.get("CommManager").get(1));
					commissionmngr.click();
					selectCM=Utilities.returnElement(driver, mapObjProjectData.get("selectCommManager").get(0), mapObjProjectData.get("selectCommManager").get(1));
					selectCM.sendKeys(Keys.ARROW_DOWN);
					selectCM.sendKeys(Keys.ENTER);
				Thread.sleep(3000);
					save = Utilities.returnElement(driver, mapObjProjectData.get("Save").get(0),
							mapObjProjectData.get("Save").get(1));
					save.click();
					Thread.sleep(3000);
				/*	String pid=driver.getCurrentUrl();
					System.out.println(pid);
					params1 = pid.split("\\?");
					
					  params = params1[1].split("&");
					    for (String param : params)
					    {
					         name = param.split("=")[0];
					         value = param.split("=")[1];
					        map.put(name, value);
					    }
					 System.out.println(map.get("projectId"));
					 */
					String projectNum=driver.findElement(By.xpath("//*[@class='inlineBlock']")).getText();
					String[] viewHeader= projectNum.split("#");
					String projectnumber1=viewHeader[1];
					createRequest= Utilities.returnElement(driver, mapObjProjectData.get("CreateRequest").get(0), mapObjProjectData.get("CreateRequest").get(1));
					createRequest.click();
					
					 systemType=Utilities.returnElement(driver, mapObjProjectData.get("systyeDistribution").get(0), mapObjProjectData.get("systyeDistribution").get(1));
					 systemType.click();
					workType=Utilities.returnElement(driver, mapObjProjectData.get("worktype").get(0), mapObjProjectData.get("worktype").get(1));
					workType.click(); 
					requestType=Utilities.returnElement(driver, mapObjProjectData.get("reqtypeForced").get(0), mapObjProjectData.get("reqtypeForced").get(1));
					requestType.click();
					
					startDate = Utilities.returnElement(driver, mapObjProjectData.get("StartDate").get(0),
							mapObjProjectData.get("StartDate").get(1));
					startDate.clear();
					startDate.sendKeys(lstTestData.get(i));
					Thread.sleep(3000);
					
					schdleProfile=Utilities.returnElement(driver, mapObjProjectData.get("ScheduleProfile").get(0), mapObjProjectData.get("ScheduleProfile").get(1));
					schdleProfile.click();
					schdleItem=Utilities.returnElement(driver, mapObjProjectData.get("Scheduleitem").get(0), mapObjProjectData.get("Scheduleitem").get(1));
					schdleItem.click();
					reqstSubmit=Utilities.returnElement(driver, mapObjProjectData.get("Reqstsubmit").get(0), mapObjProjectData.get("Reqstsubmit").get(1));
					reqstSubmit.click();
					action=Utilities.returnElement(driver, mapObjProjectData.get("Action").get(0), mapObjProjectData.get("Action").get(1));
					action.click();
					Thread.sleep(2000);
					requestSubmit=Utilities.returnElement(driver, mapObjProjectData.get("RequestSubmit").get(0), mapObjProjectData.get("RequestSubmit").get(1));
					
					requestSubmit.click();
					
					comment=Utilities.returnElement(driver, mapObjProjectData.get("Comment").get(0), mapObjProjectData.get("Comment").get(1));
					Thread.sleep(2000);
					comment.sendKeys(lstTestData.get(i + 6));
					confirmSubmit=Utilities.returnElement(driver, mapObjProjectData.get("cnfrmsubmit").get(0), mapObjProjectData.get("cnfrmsubmit").get(1));
					confirmSubmit.click();
				 RequestEntryNumber=driver.findElement(By.xpath("//span[@id='outageAppNumId']//child::h3[1]")).getText();//capture request entry number
				 reqNum=RequestEntryNumber.substring(1);
				
				System.out.println(RequestEntryNumber);
					 
					
					logo = Utilities.returnElement(driver, mapObjProjectData.get("Logo").get(0),
							mapObjProjectData.get("Logo").get(1));
					logo.click();
					projList = Utilities.returnElement(driver, mapObjProjectData.get("ProjectList").get(0),
							mapObjProjectData.get("ProjectList").get(1));
					projList.click();
					filterStartDate = Utilities.returnElement(driver, mapObjProjectData.get("FilterStartDate").get(0),mapObjProjectData.get("FilterStartDate").get(1));
					filterStartDate.clear();
					Thread.sleep(2000);
					filterStartDate.sendKeys("07/01/2019");
					Thread.sleep(3000);
					filterEndDate=Utilities.returnElement(driver, mapObjProjectData.get("FilterEndDate").get(0),mapObjProjectData.get("FilterEndDate").get(1));
					filterEndDate.clear();
					Thread.sleep(2000);
					filterEndDate.sendKeys("08/25/2020");
					Thread.sleep(2000);
					endDateLabel = Utilities.returnElement(driver, mapObjProjectData.get("Enddatelabel").get(0),mapObjProjectData.get("Enddatelabel").get(1));
					endDateLabel.click();
					filterEndDate.sendKeys(Keys.ENTER);
					
					
					filter = Utilities.returnElement(driver, mapObjProjectData.get("Filtericon").get(0),
							mapObjProjectData.get("Filtericon").get(1));
					Thread.sleep(3000);
					filter.click();
					//
					titleFilter = Utilities.returnElement(driver, mapObjProjectData.get("projecttitle").get(0),
							mapObjProjectData.get("projecttitle").get(1));
					//titleFilter.sendKeys(map.get("projectId"));
					titleFilter.sendKeys(projectnumber1);
					Thread.sleep(3000);
					srchResult1 = Utilities.returnElement(driver, mapObjProjectData.get("verification").get(0),
							mapObjProjectData.get("verification").get(1));
					srchResult=srchResult1.isDisplayed();
					if (srchResult == true) {
						Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "Created Record By Save button is displayed", driver);
					srchResult1.click();	
					viewpageoutage=driver.findElement(By.xpath("//*[@id=\"outageListIdView\"]/tbody/tr/td[2]")).getText();
					
				
					
					boolean equals = viewpageoutage.equals(reqNum);
					if (equals == true){
						Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "Created Outage Request is displayed", driver);
						
					}
					
					else {
						Extent_Reports.executionLog("Fail", Extent_Reports.logExpected + "Created Outage Request is not displayed", driver);
					}
					
	
				}
					else {
						
						Extent_Reports.executionLog("Fail", Extent_Reports.logExpected + "Created Record By Save button is not displayed", driver);
					}
					
					Extent_Reports.executionLog("PASS", "Project entry By Save button with create request working as expected", driver);
					i = i + 7;
			}}

			catch (Exception exc) {

				System.out.println(exc.getMessage());
				exc.printStackTrace();
				 Extent_Reports.executionLog("FAIL","Project entry By Save button with create request is not working as expected", driver);
				
			}
		}

}
