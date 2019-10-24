package com.sunnet.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.sunnet.GenericLib.DatabaseFunction;
import com.sunnet.GenericLib.Extent_Reports;
import com.sunnet.GenericLib.GenericFunctions;
import com.sunnet.utils.ProjectUtily;

public class PGPersonalinfosection {

	// =========================================Variables=================================================================================
	DatabaseFunction db = new DatabaseFunction();
	GenericFunctions gfObj = new GenericFunctions();
	private WebDriver driver;
	List<WebElement> elements;
	public List<String> lstObject, lstTestData;
	WebElement pentry, startDate, endDate, title, startLabel, desc, projTyp, projItem, costCentre, costCentreItem,
			projNum, wrkCentre, saveAndReturn, logo, projList, filter, titleFilter,addoutage;
	boolean srchResult;
	// String expurl="https://sunnetqa.anblicks.com/itoa/login.htmlx";

	// =============================Constructor to initialize all the Page
	// Objects==========================================================
	public PGPersonalinfosection(WebDriver driver)

	{

		try {
			this.driver = driver;
			lstObject = ProjectUtily.getObjProjectData(db);
			lstTestData = db.getTestDataObject("Select * from Projectentry", "Input");
		} catch (Exception exc) {

			System.out.println("Exception in Constructor." + exc.getMessage());

		}
	}

	// ================================BUSINESS VALIDATION
	// LOGIC=================================================

	public void projectentryoutage() {
		try {
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			
			Thread.sleep(3000);
			
			WebElement outageDropdownElement = driver.findElement(By.id("s2id_outage_items"));
			outageDropdownElement.click();
			
			
			WebElement divElement = driver.findElement(By.id("select2-drop"));
			
			WebElement innerUlElement = divElement.findElement(By.tagName("ul"));
			
			List<WebElement> items = innerUlElement.findElements(By.tagName("li"));

			List<String> outageItems = getOutageItems(items);

			System.out.println("outage_Items : "+outageItems.size());

			

			for (int i = 0;i<outageItems.size();) {

				jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");

				WebElement DivElement = driver.findElement(By.id("select2-drop"));
				WebElement innerDivElement = DivElement.findElement(By.tagName("div"));
				WebElement innerInputElement = innerDivElement.findElement(By.tagName("input"));

				String searchId = innerInputElement.getAttribute("id");

				WebElement searchElement = driver.findElement(By.id(searchId));
				searchElement.sendKeys(outageItems.get(i));
				
				Thread.sleep(1500);

				innerInputElement = innerDivElement.findElement(By.tagName("input"));

				String highlightedElementId = innerInputElement.getAttribute("aria-activedescendant");

				WebElement highlightedElement = driver.findElement(By.id(highlightedElementId));
				highlightedElement.click();
				
				Thread.sleep(1000);
				
				driver.findElement(By.id("outageId")).click();
				
				Thread.sleep(1000);
				
				if(i!=outageItems.size()-1) {
				WebElement outageDropdownNewElement = driver.findElement(By.id("s2id_outage_items"));
				outageDropdownNewElement.click();
				}
				
				
				i = i + 1;
			}

			Thread.sleep(1000);
			jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Extent_Reports.executionLog("PASS", "Adding outage requests working as expected", driver);

		}
		

		catch (Exception exc) {

			System.out.println(exc.getMessage());
			exc.printStackTrace();
		}
	}

	private List<String> getOutageItems(List<WebElement> items) {
		
		List<String> newOutageList = new ArrayList<String>();
		
		items.forEach(ele -> {
			newOutageList.add(ele.findElement(By.tagName("div"))
                    .findElement(By.tagName("table"))
                    .findElement(By.tagName("tbody"))
                    .findElement(By.tagName("tr"))
                    .findElements(By.tagName("td")).get(0).getText());
		});
		
		return newOutageList;
	}

}
