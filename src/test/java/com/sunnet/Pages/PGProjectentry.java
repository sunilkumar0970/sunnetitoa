package com.sunnet.Pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.sunnet.GenericLib.DatabaseFunction;
import com.sunnet.GenericLib.Extent_Reports;
import com.sunnet.GenericLib.GenericFunctions;
import com.sunnet.GenericLib.Utilities;
import com.sunnet.utils.ProjectUtily;

public class PGProjectentry {
	// =========================================Variables=================================================================================
	DatabaseFunction db = new DatabaseFunction();
	GenericFunctions gfObj = new GenericFunctions();
	private WebDriver driver;
	List<WebElement> elements;
	public List<String> lstObject, lstTestData;
	WebElement pentry, startDate, endDate, title, startLabel, desc, projTyp, projItem, costCentre, costCentreItem,
			projNum, wrkCentre, saveAndReturn, logo, projList, filter, titleFilter, addoutage, createRequest, edit,
			save, srchResult1;
	boolean srchResult, srchCreateRequest;
	// String expurl="https://sunnetqa.anblicks.com/itoa/login.htmlx";

	// =============================Constructor to initialize all the Page
	// Objects==========================================================
	public PGProjectentry(WebDriver driver)

	{
		try {
			this.driver = driver;
			lstObject = ProjectUtily.getObjProjectData(db);
			lstTestData = db.getTestDataObject("Select * from Editproject", "Input");
		} catch (Exception exc) {

			System.out.println("Exception in Constructor." + exc.getMessage());

		}
	}

	// ================================BUSINESS VALIDATION
	// LOGIC=================================================

	public void verifyEditProjectEntry() throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		try {

			Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
			pentry = Utilities.returnElement(driver, mapObjProjectData.get("Project").get(0),
					mapObjProjectData.get("Project").get(1));
			pentry.click();
			int i = 0;
			while (i < (lstTestData.size())) {
				startDate = Utilities.returnElement(driver, mapObjProjectData.get("StartDate").get(0),
						mapObjProjectData.get("StartDate").get(1));
				startDate.clear();
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
				title.clear();

				title.sendKeys(lstTestData.get(i + 2));
				desc = Utilities.returnElement(driver, mapObjProjectData.get("Description").get(0),
						mapObjProjectData.get("Description").get(1));
				desc.clear();
				desc.sendKeys(lstTestData.get(i + 3));
				Thread.sleep(4000);
				projTyp = Utilities.returnElement(driver, mapObjProjectData.get("projtype").get(0),
						mapObjProjectData.get("projtype").get(1));
				projTyp.click();
				projItem = Utilities.returnElement(driver, mapObjProjectData.get("Projtypitem").get(0),
						mapObjProjectData.get("Projtypitem").get(1));
				projItem.click();
				projNum = Utilities.returnElement(driver, mapObjProjectData.get("projnum").get(0),
						mapObjProjectData.get("projnum").get(1));
				projNum.clear();
				projNum.sendKeys(lstTestData.get(i + 4));
				Thread.sleep(3000);
				costCentre = Utilities.returnElement(driver, mapObjProjectData.get("costcentre").get(0),
						mapObjProjectData.get("costcentre").get(1));
				costCentre.click();

				WebElement cc = driver.findElement(By.id("select2-results-2"));
				List<WebElement> costCentreItems = cc.findElements(By.tagName("li"));
				int itemscount = costCentreItems.size();
				System.out.println(itemscount);

				costCentreItem = Utilities.returnElement(driver, mapObjProjectData.get("costcentreitem").get(0),
						mapObjProjectData.get("costcentreitem").get(1));
				costCentreItem.click();
				wrkCentre = Utilities.returnElement(driver, mapObjProjectData.get("WorkCentre").get(0),
						mapObjProjectData.get("WorkCentre").get(1));
				wrkCentre.clear();
				wrkCentre.sendKeys(lstTestData.get(i + 5));

				WebElement table = driver.findElement(By.id("tblElementsRelays"));
				WebElement tbody = table.findElement(By.tagName("tbody"));
				List<WebElement> trelements = tbody.findElements(By.tagName("tr"));
				System.out.println("trElemts Size : " + trelements.size());
				if (trelements.size() <= 2) {
					for (int j = 0; j < costCentreItems.size(); j++) {
						WebElement addCostCentre = Utilities.returnElement(driver,mapObjProjectData.get("addCostcentre").get(0),mapObjProjectData.get("addCostcentre").get(1));
						//WebElement addCostCentre=driver.findElement(By.id("addCenter"));
						addCostCentre.click();
						Select cs1 = new Select(driver
								.findElement(By.xpath("//*[@id='project.projectWoLink[" + j + "].costCenterType']")));
						cs1.selectByIndex(j);
					}
				} else {

				}

				Thread.sleep(2000);

				PGPersonalInfo pgPersonalInfo = new PGPersonalInfo(driver);
				pgPersonalInfo.personalInfoManual();

				Thread.sleep(3000);

				PGPersonalinfosection outagesection = new PGPersonalinfosection(driver);
				outagesection.projectentryoutage();

				jse.executeScript("window.scrollTo(0, 0)");

				Thread.sleep(1500);

				System.out.println("Came Sunil !!");

				saveAndReturn = driver.findElement(By.id("submitSaveReturn"));
				saveAndReturn.click();
				Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + " New Project Entry Record created",driver);

				for (int j = 1; j > i; j--) {
					System.out.println("123");
					edit = Utilities.returnElement(driver, mapObjProjectData.get("saveandreturn").get(0),
							mapObjProjectData.get("saveandreturn").get(1));
					System.out.println("456");
					edit.click();
				}

				i = i + 7;
				Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "Project Entry Record Edited", driver);
				System.out.println("test");
			}
		}

		catch (Exception exc) {

			System.out.println(exc.getMessage());
			exc.printStackTrace();
		}
	}
	
	
	public void verifyProjectEntry() throws Exception {
		//	Random random = new Random();
			

			try {

				Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
				   Map<String, String> map = new HashMap<String, String>();
				pentry = Utilities.returnElement(driver, mapObjProjectData.get("Project").get(0),
						mapObjProjectData.get("Project").get(1));
				
				pentry.click();
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
					projTyp.click();
					projItem = Utilities.returnElement(driver, mapObjProjectData.get("Projtypitem").get(0),
							mapObjProjectData.get("Projtypitem").get(1));
					projItem.click();
					projNum = Utilities.returnElement(driver, mapObjProjectData.get("projnum").get(0),
							mapObjProjectData.get("projnum").get(1));
					projNum.sendKeys(lstTestData.get(i + 4));
					Thread.sleep(3000);
					costCentre = Utilities.returnElement(driver, mapObjProjectData.get("costcentre").get(0),
							mapObjProjectData.get("costcentre").get(1));
					costCentre.click();
					costCentreItem = Utilities.returnElement(driver, mapObjProjectData.get("costcentreitem").get(0),
							mapObjProjectData.get("costcentreitem").get(1));
					costCentreItem.click();
					wrkCentre = Utilities.returnElement(driver, mapObjProjectData.get("WorkCentre").get(0),
							mapObjProjectData.get("WorkCentre").get(1));
					wrkCentre.sendKeys(lstTestData.get(i + 5));
					//PGPersonalinfosection outagesection = new PGPersonalinfosection(driver);
					//outagesection.projectentryoutage();
					// driver.findElement(By.xpath("//*[@id='submitSaveReturn']")).click();
					//addoutage=Utilities.returnElement(driver, mapObjProjectData.get("Addoutage").get(0), mapObjProjectData.get("Addoutage").get(1));
					//addoutage.click();
				Thread.sleep(3000);
				srchCreateRequest=Utilities.returnElement(driver, mapObjProjectData.get("CreateRequest").get(0), mapObjProjectData.get("CreateRequest").get(1)).isDisplayed();
					
					if(srchCreateRequest==false) {
						saveAndReturn = Utilities.returnElement(driver, mapObjProjectData.get("saveandreturn").get(0),mapObjProjectData.get("saveandreturn").get(1));
						saveAndReturn.click();
						String pid=driver.getCurrentUrl();
						System.out.println(pid);
						String[] params1 = pid.split("\\?");
						
						 String[] params = params1[1].split("&");
						
						    for (String param : params)
						    {
						        String name = param.split("=")[0];
						        String value = param.split("=")[1];
						        map.put(name, value);
						    }
						 System.out.println(map.get("projectId"));
						  edit= Utilities.returnElement(driver, mapObjProjectData.get("saveandreturn").get(0),mapObjProjectData.get("saveandreturn").get(1));
							edit.click();	 
					}
					else {
						
						save = Utilities.returnElement(driver, mapObjProjectData.get("Save").get(0),
								mapObjProjectData.get("Save").get(1));
						save.click();
					}
					/*saveAndReturn = Utilities.returnElement(driver, mapObjProjectData.get("saveandreturn").get(0),
							mapObjProjectData.get("saveandreturn").get(1));
					saveAndReturn.click();
					Thread.sleep(3000);
					String pid=driver.getCurrentUrl();
					System.out.println(pid);
					String[] params1 = pid.split("\\?");
					
					 String[] params = params1[1].split("&");
					    Map<String, String> map = new HashMap<String, String>();
					    for (String param : params)
					    {
					        String name = param.split("=")[0];
					        String value = param.split("=")[1];
					        map.put(name, value);
					    }
					 System.out.println(map.get("projectId"));
					 */
					logo = Utilities.returnElement(driver, mapObjProjectData.get("Logo").get(0),
							mapObjProjectData.get("Logo").get(1));
					logo.click();
					projList = Utilities.returnElement(driver, mapObjProjectData.get("ProjectList").get(0),
							mapObjProjectData.get("ProjectList").get(1));
					projList.click();
					filter = Utilities.returnElement(driver, mapObjProjectData.get("Filtericon").get(0),
							mapObjProjectData.get("Filtericon").get(1));
					Thread.sleep(3000);
					filter.click();
					//
					titleFilter = Utilities.returnElement(driver, mapObjProjectData.get("projecttitle").get(0),
							mapObjProjectData.get("projecttitle").get(1));
					titleFilter.sendKeys(map.get("projectId"));
					Thread.sleep(3000);
					srchResult = Utilities.returnElement(driver, mapObjProjectData.get("verification").get(0),
							mapObjProjectData.get("verification").get(1)).isDisplayed();
					if (srchResult == true) {
						Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "Created Record is displayed", driver);
					} else {
						Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "Created Record is not displayed", driver);
					}
					logo = Utilities.returnElement(driver, mapObjProjectData.get("Logo").get(0),
							mapObjProjectData.get("Logo").get(1));
					logo.click();
					pentry = Utilities.returnElement(driver, mapObjProjectData.get("Project").get(0),
							mapObjProjectData.get("Project").get(1));
					pentry.click();

					Extent_Reports.executionLog("PASS", "Project entry validation working as expected", driver);

					i = i + 7;
				}
			}

			catch (Exception exc) {

				System.out.println(exc.getMessage());
				exc.printStackTrace();
				// Extent_Reports.executionLog("FAIL","Project entry validation not working as
				// expected",driver);
			}
		}
	
	
	
	

}
