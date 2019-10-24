package com.sunnet.Pages;

import static org.testng.Assert.assertEquals;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.sunnet.GenericLib.DatabaseFunction;
import com.sunnet.GenericLib.Extent_Reports;
import com.sunnet.GenericLib.GenericFunctions;
import com.sunnet.GenericLib.Utilities;
import com.sunnet.utils.ProjectUtily;

public class PGProjectentryCreateAndEdit {
	// =========================================Variables=================================================================================
	DatabaseFunction db = new DatabaseFunction();
	GenericFunctions gfObj = new GenericFunctions();
	private WebDriver driver;
	List<WebElement> elements;
	public List<String> lstObject, lstTestData,lstTestData1,datamodifications;
	WebElement pentry, startDate, endDate, title, startLabel, desc, projTyp, projItem, costCentre, costCentreItem,
			projNum, wrkCentre, saveAndReturn, logo, projList, filter, titleFilter, addoutage, createRequest, edit,
			save, srchResult1,Return,endTime,startTime,filterStartDate,filterEndDate,endDateLabel;
	boolean srchResult, srchCreateRequest;
	// String expurl="https://sunnetqa.anblicks.com/itoa/login.htmlx";

	// =============================Constructor to initialize all the Page
	// Objects==========================================================
	public PGProjectentryCreateAndEdit(WebDriver driver)

	{
		try {
			this.driver = driver;
			lstObject = ProjectUtily.getObjProjectData(db);
			lstTestData = db.getTestDataObject("Select * from Editproject", "Input");
			lstTestData1 = db.getTestDataObject("Select * from Projectentry", "Input");
			
		} catch (Exception exc) {

			System.out.println("Exception in Constructor." + exc.getMessage());

		}
	}

	// ================================BUSINESS VALIDATION LOGIC=================================================

	public void verifyEditProjectEntry() throws Exception {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		try {

			Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
			  datamodifications=new ArrayList<String>();
			pentry = Utilities.returnElement(driver, mapObjProjectData.get("Project").get(0),
					mapObjProjectData.get("Project").get(1));
			pentry.click();
			int i = 0;
			while (i < (lstTestData.size())) {
				Thread.sleep(2000);
				startDate = Utilities.returnElement(driver, mapObjProjectData.get("StartDate").get(0),
						mapObjProjectData.get("StartDate").get(1));
		    	startDate.clear();
				startDate.sendKeys(lstTestData.get(i));
			//	Thread.sleep(1000);
				
				startLabel = Utilities.returnElement(driver, mapObjProjectData.get("StartdateLabel").get(0),
						mapObjProjectData.get("StartdateLabel").get(1));
				startLabel.click();
				endDate = Utilities.returnElement(driver, mapObjProjectData.get("Enddate").get(0),
						mapObjProjectData.get("Enddate").get(1));
				endDate.clear();
				endDate.sendKeys(lstTestData.get(i + 1));
			//	Thread.sleep(1000);
				
				title = Utilities.returnElement(driver, mapObjProjectData.get("Title").get(0),
						mapObjProjectData.get("Title").get(1));
			//	Thread.sleep(3000);
				title.clear();
				title.sendKeys(lstTestData.get(i + 2));
				Thread.sleep(1000);
				
				desc = Utilities.returnElement(driver, mapObjProjectData.get("Description").get(0),
						mapObjProjectData.get("Description").get(1));
				desc.clear();
				desc.sendKeys(lstTestData.get(i + 3));
			//	Thread.sleep(1000);
					
				projTyp = Utilities.returnElement(driver, mapObjProjectData.get("projtype").get(0),
						mapObjProjectData.get("projtype").get(1));
				Select pt=new Select(projTyp);
				pt.selectByIndex(1);
			
				projNum = Utilities.returnElement(driver, mapObjProjectData.get("projnum").get(0),
						mapObjProjectData.get("projnum").get(1));
				projNum.clear();
				projNum.sendKeys(lstTestData.get(i + 4));
			
				Thread.sleep(3000);
				PGPersonalInfo pgPersonalInfo = new PGPersonalInfo(driver);
				pgPersonalInfo.personalInfoManual();
				Thread.sleep(2000);
				costCentre = Utilities.returnElement(driver, mapObjProjectData.get("costcentre").get(0),
						mapObjProjectData.get("costcentre").get(1));
				Select st=new Select(costCentre);
				st.selectByIndex(1);
				int ic=costCentre.findElements(By.tagName("option")).size();
				
				wrkCentre = Utilities.returnElement(driver, mapObjProjectData.get("WorkCentre").get(0),
						mapObjProjectData.get("WorkCentre").get(1));
				wrkCentre.clear();
				wrkCentre.sendKeys(lstTestData.get(i + 5));

				WebElement table = driver.findElement(By.id("tblElementsRelays"));
				WebElement tbody = table.findElement(By.tagName("tbody"));
				List<WebElement> trelements = tbody.findElements(By.tagName("tr"));
				System.out.println("trElemts Size : " + trelements.size());
				if (trelements.size() <= 2) {
					for (int j = 0; j < ic-1; j++) {
						WebElement addCostCentre = Utilities.returnElement(driver,mapObjProjectData.get("addCostcentre").get(0),mapObjProjectData.get("addCostcentre").get(1));
						//WebElement addCostCentre=driver.findElement(By.id("addCenter"));
						addCostCentre.click();
						Select cs1 = new Select(driver
								.findElement(By.xpath("//*[@id='project.projectWoLink[" + j + "].costCenterType']")));
						cs1.selectByIndex(j+1);
					}
				} else {
					for (int k = 0; k < ic-1; k++) {
					driver.findElement(By.xpath("//*[@id='project.projectWoLink["+k+"].workCenterType']")).sendKeys(lstTestData.get(i + 5)+k);

				}
				}

				Thread.sleep(3000);

			//	PGPersonalinfosection outagesection = new PGPersonalinfosection(driver);
			//	outagesection.projectentryoutage();

			
			//	System.out.println("Came Sunil !!");
				jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				saveAndReturn = Utilities.returnElement(driver, mapObjProjectData.get("saveandreturn").get(0),
						mapObjProjectData.get("saveandreturn").get(1));		
				saveAndReturn.click();
				Thread.sleep(2000);
				
				
				String sd=driver.findElement(By.xpath("//*[@id='projectTab']/div[1]/div[2]/div/div[1]/div[1]/label[2]")).getText();
			//	String viewsd=sd.substring(0, sd.length() - 5);
				System.out.println(sd);
				datamodifications.add(sd);


				String ed=driver.findElement(By.xpath("//*[@id='projectTab']/div[1]/div[2]/div/div[1]/div[2]/label[2]")).getText();
			//	String viewed=sd.substring(0, sd.length() - 6);
				System.out.println(ed);
				datamodifications.add(ed);

				String t=driver.findElement(By.xpath("//*[@id='projectTab']/div[1]/div[2]/div/div[3]/div[1]/label[2]")).getText();
				System.out.println(t);
				datamodifications.add(t);

				String des=driver.findElement(By.xpath("//*[@id='projectTab']/div[1]/div[2]/div/div[3]/div[2]/label[2]")).getText();
				System.out.println(des);
				datamodifications.add(des);

				String pn=driver.findElement(By.xpath("//*[@id=\"projectTab\"]/div[1]/div[2]/div/div[2]/div[2]/label[2]")).getText();
				System.out.println(pn);
				datamodifications.add(pn);
				

				for (int j = 1; j > i; j--) {
					Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + " New Project Entry Record created",driver);
					edit = Utilities.returnElement(driver, mapObjProjectData.get("Editbutton").get(0),
							mapObjProjectData.get("Editbutton").get(1));
					edit.click();
				}

				i = i + 6;
			}
			String status=driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/strong/span")).getText();
			String auditStatus=status.substring(5);
			System.out.println("view page status"+auditStatus);
			Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "Project Entry Record Edited", driver);
			driver.findElement(By.partialLinkText("Audit Trai")).click();
			String auditStatus1=driver.findElement(By.xpath("//*[@id='auditTab']/div[1]/table/tbody/tr/td[2]")).getText();
			System.out.println("audit tab status"+auditStatus);
			assertEquals(auditStatus, auditStatus1);
			driver.findElement(By.xpath("//*[@id='accordionAudit']/div/div/div/a")).click();
			Thread.sleep(1000);
			Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "opened the audit tab of project details", driver);
			for(int m=7;m<datamodifications.size();m++) {
			driver.findElement(By.xpath("//*[@id='fieldAudit']/thead/tr[2]/th[4]/input")).sendKeys(datamodifications.get(m));
			String updatedData=driver.findElement(By.xpath("//*[@id='fieldAudit']/tbody/tr/td[4]")).getText();
			System.out.println(updatedData);
			System.out.println(datamodifications.get(m));
			Thread.sleep(2000);
			assertEquals(datamodifications.get(m), updatedData);
			
			driver.findElement(By.xpath("//*[@id='fieldAudit']/thead/tr[2]/th[4]/input")).clear();
			}
			WebElement auditfiles=driver.findElement(By.xpath("//*[@id='fieldAudit_wrapper']/div[1]"));
			List<WebElement>buttons= auditfiles.findElements(By.tagName("button"));
			for(int k=0;k<buttons.size();k++) {
				Thread.sleep(2000);
				buttons.get(k).click();
				 Robot robot = new Robot();
			        Thread.sleep(2000);
			        robot.keyPress(KeyEvent.VK_ENTER);
				Set<String>handles= driver.getWindowHandles(); 
				String currentwindow=driver.getWindowHandle();
				if(handles.size()>0) {
			for(String list:handles) {
				if(!list.contains(currentwindow)) {
					driver.switchTo().window(list);
					String test1=driver.getCurrentUrl();
			       test1.contains("blank");
			       Thread.sleep(1000);
			 Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "opened the print page in browser ", driver);		
				}}}else {
					
				}
				
				
			}
		
			
		}

		catch (Exception exc) {

			System.out.println(exc.getMessage());
			exc.printStackTrace();
		}
	}
	
	
	public void verifyProjectEntry() throws Exception {
		//	Random random = new Random();
	//	JavascriptExecutor jse = (JavascriptExecutor) driver;

			try {

				Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
				  // Map<String, String> maps = new HashMap<String, String>();
				pentry = Utilities.returnElement(driver, mapObjProjectData.get("Project").get(0),
						mapObjProjectData.get("Project").get(1));
				
				pentry.click();
				int i = 0;
				while (i < (lstTestData1.size())) {
					Thread.sleep(2000);
					startDate = Utilities.returnElement(driver, mapObjProjectData.get("StartDate").get(0),
							mapObjProjectData.get("StartDate").get(1));
					startDate.clear();
					startDate.sendKeys(lstTestData1.get(i));
					Thread.sleep(3000);
					startLabel = Utilities.returnElement(driver, mapObjProjectData.get("StartdateLabel").get(0),
							mapObjProjectData.get("StartdateLabel").get(1));
					startLabel.click();
					endDate = Utilities.returnElement(driver, mapObjProjectData.get("Enddate").get(0),
							mapObjProjectData.get("Enddate").get(1));
					endDate.clear(); 
					endDate.sendKeys(lstTestData1.get(i + 1));
					Thread.sleep(3000);
					endTime = Utilities.returnElement(driver, mapObjProjectData.get("Endtime").get(0),mapObjProjectData.get("Endtime").get(1));
					title = Utilities.returnElement(driver, mapObjProjectData.get("Title").get(0),
							mapObjProjectData.get("Title").get(1));
					title.sendKeys(lstTestData1.get(i + 2));
					desc = Utilities.returnElement(driver, mapObjProjectData.get("Description").get(0),
							mapObjProjectData.get("Description").get(1));
					desc.sendKeys(lstTestData1.get(i + 3));
					Thread.sleep(4000);
					projTyp = Utilities.returnElement(driver, mapObjProjectData.get("projtype").get(0),
							mapObjProjectData.get("projtype").get(1));
				
					Select pt=new Select(projTyp);
					pt.selectByIndex(1);
					projNum = Utilities.returnElement(driver, mapObjProjectData.get("projnum").get(0),
							mapObjProjectData.get("projnum").get(1));
					projNum.sendKeys(lstTestData1.get(i + 4));
					Thread.sleep(3000);
					costCentre = Utilities.returnElement(driver, mapObjProjectData.get("costcentre").get(0),mapObjProjectData.get("costcentre").get(1));
					Select st=new Select(costCentre);
					st.selectByIndex(1);
	
					wrkCentre = Utilities.returnElement(driver, mapObjProjectData.get("WorkCentre").get(0),mapObjProjectData.get("WorkCentre").get(1));
					wrkCentre.sendKeys(lstTestData1.get(i + 5));
					PGPersonalInfo df=new PGPersonalInfo(driver);
					df.personalInfoManual();
					
					save = Utilities.returnElement(driver, mapObjProjectData.get("Save").get(0),mapObjProjectData.get("Save").get(1));
						save.click();
				PGPersonalinfosection outagesection = new PGPersonalinfosection(driver);
				outagesection.projectentryoutage();
					// driver.findElement(By.xpath("//*[@id='submitSaveReturn']")).click();
					//addoutage=Utilities.returnElement(driver, mapObjProjectData.get("Addoutage").get(0), mapObjProjectData.get("Addoutage").get(1));
					//addoutage.click();
					Thread.sleep(2000);
			
				//	save = Utilities.returnElement(driver, mapObjProjectData.get("Save").get(0),mapObjProjectData.get("Save").get(1));
				//	save.click();
				//	Thread.sleep(3000);
					//	PGPersonalinfosection outagesection = new PGPersonalinfosection(driver);
					//	outagesection.projectentryoutage();
					//	Thread.sleep(2000);
				//		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");	
						saveAndReturn = Utilities.returnElement(driver, mapObjProjectData.get("saveandreturn").get(0),mapObjProjectData.get("saveandreturn").get(1));
						saveAndReturn.click();

					String projectNum=driver.findElement(By.xpath("//*[@class='inlineBlock']")).getText();
					String[] viewHeader= projectNum.split("#");
					String projectnumber1=viewHeader[1];
					System.out.println(projectnumber1);
					
					/*String pid=driver.getCurrentUrl();
					System.out.println(pid);
					String[] params1 = pid.split("\\?");
					
					 String[] params = params1[1].split("&");
					    Map<String, String> maps = new HashMap<String, String>();
					    for (String param : params)
					    {
					        String name = param.split("=")[0];
					        String value = param.split("=")[1];
					        maps.put(name, value);
					    }
					 System.out.println(maps.get("projectId"));*/
					 Return=Utilities.returnElement(driver, mapObjProjectData.get("return").get(0),mapObjProjectData.get("return").get(1));
					 Return.click();
					 filterStartDate = Utilities.returnElement(driver, mapObjProjectData.get("FilterStartDate").get(0),mapObjProjectData.get("FilterStartDate").get(1));
						filterStartDate.clear();
						Thread.sleep(2000);
						filterStartDate.sendKeys("07/01/2018");
						Thread.sleep(3000);
						filterEndDate=Utilities.returnElement(driver, mapObjProjectData.get("FilterEndDate").get(0),mapObjProjectData.get("FilterEndDate").get(1));
						filterEndDate.clear();
						Thread.sleep(2000);
						filterEndDate.sendKeys("12/31/2019");
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
					srchResult = Utilities.returnElement(driver, mapObjProjectData.get("verification").get(0),
							mapObjProjectData.get("verification").get(1)).isDisplayed();
					if (srchResult == true) {
						Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "Created Record is displayed", driver);
					} else {
						Extent_Reports.executionLog("Fail", Extent_Reports.logExpected + "Created Record is not displayed", driver);
					}
					logo = Utilities.returnElement(driver, mapObjProjectData.get("Logo").get(0),mapObjProjectData.get("Logo").get(1));
					logo.click();
					pentry = Utilities.returnElement(driver, mapObjProjectData.get("Project").get(0),mapObjProjectData.get("Project").get(1));
					pentry.click();

					i = i + 6;
				}
				Extent_Reports.executionLog("PASS", "Project entry validation working as expected", driver);
			}

			catch (Exception exc) {

				System.out.println(exc.getMessage());
				exc.printStackTrace();
				 Extent_Reports.executionLog("FAIL","Project entry validation not working as expected",driver);
				
			}
		}
	
	
	
	

}
