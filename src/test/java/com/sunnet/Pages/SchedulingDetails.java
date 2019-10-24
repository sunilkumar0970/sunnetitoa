package com.sunnet.Pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.sunnet.GenericLib.DatabaseFunction;
import com.sunnet.GenericLib.Extent_Reports;
import com.sunnet.GenericLib.GenericFunctions;
import com.sunnet.GenericLib.Utilities;
import com.sunnet.utils.ProjectUtily;

public class SchedulingDetails {
	// =========================================Variables=================================================================================
	DatabaseFunction db = new DatabaseFunction();
	GenericFunctions GFObj = new GenericFunctions();
	private WebDriver driver;
	List<WebElement> elements;
	public List<String> lstObject, lstTestData;

	WebElement objUserName, objPassWord, objLoginButton, objProfileElement, Pentry, startDate, startLabel, endDate,
			startTime, endTime, projtype, projItem, projNum, wrkCentre, addCostCentre, deleteCostCentre, costCentre,
			costCentreItem, wrkCentre1, wrkCentre2, wrkCentre3, wrkCentre4;
	// WebElement objpassWord;
	// WebElement objloginButton, objProfileElement;
	String expurl = "https://sunnetqa.anblicks.com/itoa/login.htmlx";

	// =============================Constructor to initialize all the Page Objects==========================================================
	public SchedulingDetails(WebDriver driver)

	{
		try {
			this.driver = driver;
			lstObject = ProjectUtily.getObjProjectData(db);
			lstTestData = db.getTestDataObject("Select * from schedulingDetails", "Input");
		} catch (Exception exc) {

			System.out.println("Exception in Constructor." + exc.getMessage());

		}
	}

	// ================================BUSINESS VALIDATION LOGIC=================================================

	public void schedDtls() throws Exception {
		try {
			Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
			Pentry = Utilities.returnElement(driver, mapObjProjectData.get("Project").get(0),
					mapObjProjectData.get("Project").get(1));
			Pentry.click();
			int ps = 0;
			while (ps < (lstTestData.size())) {
				Thread.sleep(2000);
				startDate = Utilities.returnElement(driver, mapObjProjectData.get("StartDate").get(0),
						mapObjProjectData.get("StartDate").get(1));
				startDate.sendKeys(lstTestData.get(ps));
				Thread.sleep(3000);
				startLabel = Utilities.returnElement(driver, mapObjProjectData.get("StartdateLabel").get(0),
						mapObjProjectData.get("StartdateLabel").get(1));
				startLabel.click();
				endDate = Utilities.returnElement(driver, mapObjProjectData.get("Enddate").get(0),
						mapObjProjectData.get("Enddate").get(1));
				endDate.clear();
				endDate.sendKeys(lstTestData.get(ps + 1));
				startTime=Utilities.returnElement(driver,mapObjProjectData.get("Starttime").get(0),mapObjProjectData.get("Starttime").get(1));
				 startTime.clear();
				 startTime.sendKeys(lstTestData.get(ps + 2));
				endTime=Utilities.returnElement(driver,mapObjProjectData.get("Endtime").get(0),mapObjProjectData.get("Endtime").get(1));
				endTime.clear();
				Thread.sleep(4000);
				endTime.sendKeys(lstTestData.get(ps + 3));

				projtype = Utilities.returnElement(driver, mapObjProjectData.get("projtype").get(0),mapObjProjectData.get("projtype").get(1));
				// select2-results-1
				Select pt=new Select(projtype);
				int cc=pt.getOptions().size();
				//projtype.click();
				//WebElement Dd = driver.findElement(By.id("select2-results-1"));
				List<WebElement> projectTypeItems = pt.getOptions();
						//Dd.findElements(By.tagName("li"));
				List<String> actualProjectTypeItems = new ArrayList<String>();
               pt.selectByIndex(1);
				for (WebElement cost : projectTypeItems) {
					actualProjectTypeItems.add(cost.getText());
				}
				 projtype.click();
				boolean BoolValueForProjectType = actualProjectTypeItems.equals(ProjectConstants.PROJECTTYPELIST);

				System.out.println(BoolValueForProjectType);
				if (BoolValueForProjectType == true) {
					driver.findElement(By.xpath("//*[@id='project.projectType']")).click();
					Thread.sleep(2000);
					Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "Project type items are matching",driver);
				} else {
					Extent_Reports.executionLog("Fail",Extent_Reports.logExpected + "Project type items are not matching", driver);
				}

				//projItem = Utilities.returnElement(driver, mapObjProjectData.get("Projtypitem").get(0),
					//	mapObjProjectData.get("Projtypitem").get(1));
				//projItem.click();

			//	projNum = Utilities.returnElement(driver, mapObjProjectData.get("projnum").get(0),
			//			mapObjProjectData.get("projnum").get(1));
			//	projNum.sendKeys(lstTestData.get(ps + 4));

				costCentre = Utilities.returnElement(driver, mapObjProjectData.get("costcentre").get(0),mapObjProjectData.get("costcentre").get(1));
				Select scc=new Select(costCentre);

			//	WebElement cc = driver.findElement(By.id("select2-results-2"));
				List<WebElement> costCentreItems = scc.getOptions();
				List<String> actualCostCentreItems = new ArrayList<String>();
				for (WebElement cost : costCentreItems) {
					actualCostCentreItems.add(cost.getText());
				}

				boolean BoolValueForCostCenter = actualCostCentreItems.equals(ProjectConstants.COSTCENTERLIST);

				System.out.println(BoolValueForCostCenter);
				scc.selectByIndex(1);
				if (BoolValueForCostCenter == true) {
					driver.findElement(By.xpath("//*[@id='project.tempProjectWoLink.costCenterType']")).click();
					costCentre.click();
					Thread.sleep(2000);
					Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "Costcentre items are matching",
							driver);
				} else {
					Extent_Reports.executionLog("Fail",Extent_Reports.logExpected + "Costcentre items are not matching", driver);
				}
				//costCentreItem = Utilities.returnElement(driver, mapObjProjectData.get("costcentreitem").get(0),
					//	mapObjProjectData.get("costcentreitem").get(1));
				//costCentreItem.click();
				wrkCentre = Utilities.returnElement(driver, mapObjProjectData.get("WorkCentre").get(0),
						mapObjProjectData.get("WorkCentre").get(1));
				wrkCentre.sendKeys(lstTestData.get(ps + 5));
				for (int i = 0; i < cc; i++) {
					addCostCentre = Utilities.returnElement(driver, mapObjProjectData.get("addCostcentre").get(0),
							mapObjProjectData.get("addCostcentre").get(1));
					addCostCentre.click();
					driver.findElement(By.xpath("//*[@id='project.projectWoLink["+i+"].workCenterType']")).sendKeys(lstTestData.get(ps + 5)+i);;
				}
				Thread.sleep(2000);
				Extent_Reports.executionLog("PASS", "Cost centre and work centre Details added",driver);

				/*Select cs1 = new Select(Utilities.returnElement(driver, mapObjProjectData.get("costcentre1").get(0),
						mapObjProjectData.get("costcentre1").get(1)));
				cs1.selectByIndex(1);
				wrkCentre1 = Utilities.returnElement(driver, mapObjProjectData.get("workcentre1").get(0),
						mapObjProjectData.get("workcentre1").get(1));
				wrkCentre1.sendKeys(lstTestData.get(ps + 6));
				Select cs2 = new Select(Utilities.returnElement(driver, mapObjProjectData.get("costcentre2").get(0),
						mapObjProjectData.get("costcentre2").get(1)));
				cs2.selectByIndex(2);
				wrkCentre2 = Utilities.returnElement(driver, mapObjProjectData.get("workcentre2").get(0),
						mapObjProjectData.get("workcentre2").get(1));
				wrkCentre2.sendKeys(lstTestData.get(ps + 7));
				Thread.sleep(2000);
				Select cs3 = new Select(Utilities.returnElement(driver, mapObjProjectData.get("costcentre3").get(0),
						mapObjProjectData.get("costcentre3").get(1)));
				cs3.selectByIndex(3);
				wrkCentre3 = Utilities.returnElement(driver, mapObjProjectData.get("workcentre3").get(0),
						mapObjProjectData.get("workcentre3").get(1));
				wrkCentre3.sendKeys(lstTestData.get(ps + 8));
				Thread.sleep(2000);
				Select cs4 = new Select(Utilities.returnElement(driver, mapObjProjectData.get("costcentre4").get(0),
						mapObjProjectData.get("costcentre4").get(1)));
				cs4.selectByIndex(4);
				System.out.println("test");
				wrkCentre4 = Utilities.returnElement(driver, mapObjProjectData.get("workcentre4").get(0),
						mapObjProjectData.get("workcentre4").get(1));
				wrkCentre4.sendKeys(lstTestData.get(ps + 9));
				Thread.sleep(3000);
				Extent_Reports.executionLog("PASS", "Adding work centre and cost centre",driver);*/
				for (int j = 0; j < cc; j++) {
					deleteCostCentre = Utilities.returnElement(driver, mapObjProjectData.get("DeleteCostcentre").get(0),
							mapObjProjectData.get("DeleteCostcentre").get(1));
					deleteCostCentre.click();
				}
				Thread.sleep(3000);
				Extent_Reports.executionLog("PASS", "Project entry Scheduling Details section working as expected",driver);
			ps=ps+10;
			
			}
		} catch (Throwable ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			Extent_Reports.executionLog("FAIL", "Project entry Scheduled details section verification not successfull",driver);
		}

	}

}
