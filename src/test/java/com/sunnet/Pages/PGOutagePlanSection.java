package com.sunnet.Pages;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sunnet.GenericLib.DatabaseFunction;
import com.sunnet.GenericLib.Extent_Reports;
import com.sunnet.GenericLib.GenericFunctions;
import com.sunnet.GenericLib.Utilities;
import com.sunnet.utils.ProjectUtily;

public class PGOutagePlanSection {

	// =========================================Variables=================================================================================
	DatabaseFunction db = new DatabaseFunction();
	GenericFunctions gfObj = new GenericFunctions();
	private WebDriver driver;
	List<WebElement> elements,outageItems;
	public List<String> lstObject, lstTestData;
	WebElement pentry,existingOutages,totalOutageItems, startDate, endDate, title, startLabel, desc, projTyp, projItem, costCentre, costCentreItem,actions,outageLink,listOutageTable,
			projNum, wrkCentre, saveAndReturn, logo, projList, filter, titleFilter,addOutage,confirm,deleteElement,save,Return,filterStartDate,filterEndDate,endDateLabel,viewOutageTable;
	boolean srchResult;
	
	// =============================Constructor to initialize all the PageObjects==========================================================
	public PGOutagePlanSection(WebDriver driver)

	{

		try {
			this.driver = driver;
			lstObject = ProjectUtily.getObjProjectData(db);
			lstTestData = db.getTestDataObject("Select * from OutageData", "Input");
		} catch (Exception exc) {

			System.out.println("Exception in Constructor." + exc.getMessage());

		}
	}

	// ================================BUSINESS VALIDATION LOGIC=================================================

	public void verifyingProjectEntryOutage() {
		try {		
			driver.findElement(By.id("s2id_outage_items")).click();
			 
			WebElement listbox=driver.findElement(By.xpath("//*[@id='select2-results-8']"));

			List<WebElement> items=listbox.findElements(By.tagName("li"));
			
			System.out.println(items.size());
			
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			
              
                 for(int i =0;i<items.size();i++) {
                	 
                	 jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                                	 
                	 WebElement Value = items.get(i);
                	 
                	 System.out.println("Value : "+Value.getText());
 
                	 WebElement wb = items.get(i).findElement(By.tagName("div"));
                	 
                	 items.get(i);
                	 
                	 Thread.sleep(3000);
                 	
                	 String att = wb.getAttribute("id");
                	 
                	 WebElement searchdivElement = driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div")).findElement(By.tagName("input"));
                	 
                	 String att2 = searchdivElement.getAttribute("id");
                	 
                	 System.out.println("att2 : " + att2);
                	 
                	 System.out.println("Element2ff4s : "+att);
				 
                	 
                	 driver.findElement(By.id(att)).click();
				                 	 
                	 Thread.sleep(1000);
                	 
                	 driver.findElement(By.id("outageId")).click();
                	 
                	 System.out.println("Element2f4s : "+att);
                	 
                	 Thread.sleep(1200);
                	 
                	 if(i != items.size()-1) {
                	 
                	 driver.findElement(By.id("s2id_outage_items")).click();
                	 
                	 
                	 Thread.sleep(1000);

                	items=listbox.findElements(By.tagName("li"));
                	
                	 }

                 }
			
			Thread.sleep(1000);
				
			}
		

		catch (Exception exc) {

			System.out.println(exc.getMessage());
			exc.printStackTrace();
		}
	}

 public void addOutage() throws InterruptedException {
	 
	 Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
	try { pentry = Utilities.returnElement(driver, mapObjProjectData.get("Project").get(0),
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
	    existingOutages= Utilities.returnElement(driver, mapObjProjectData.get("ExistingRequist").get(0),
				mapObjProjectData.get("ExistingRequist").get(1));
	    existingOutages.click();
	    totalOutageItems = Utilities.returnElement(driver, mapObjProjectData.get("selectitems").get(0),
				mapObjProjectData.get("selectitems").get(1));
	     outageItems=totalOutageItems.findElements(By.tagName("option"));
	    for(int j=0;j<outageItems.size();j++){
	    	
	    	outageItems.get(j).click();
	    	totalOutageItems.sendKeys(Keys.TAB);
	    	Thread.sleep(5000);
	    	addOutage=Utilities.returnElement(driver, mapObjProjectData.get("Addoutage").get(0),
					mapObjProjectData.get("Addoutage").get(1));	    	
	           addOutage.click();
	          
	    } 
		
		for(int k=0;k<outageItems.size();k++) {
			 deleteElement = Utilities.returnElement(driver, mapObjProjectData.get("OutageDelete").get(0),
					mapObjProjectData.get("OutageDelete").get(1));
			deleteElement.click();
			
			Thread.sleep(1000);
			
		 confirm=Utilities.returnElement(driver, mapObjProjectData.get("OutagePopUp").get(0),
				mapObjProjectData.get("OutagePopUp").get(1));
		Thread.sleep(1000);
		confirm.click();
		Thread.sleep(2000);
	  }	
		i=i+2; 	}
	}
		catch(Exception e){
			
		System.out.println(e.getStackTrace());	
		}

           	
		
            }
	

public void dleteOutage() {
	
	try {
		WebElement table=driver.findElement(By.xpath("//*[@id='outageListId']/tbody"));
		List<WebElement>outagerows=table.findElements(By.tagName("tr"));
		for(int i=0;i<outagerows.size()-3;i++) {
		driver.findElement(By.xpath("//*[@id='outageListId']/tbody/tr[1]/td[11]/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();;
		Thread.sleep(1000);
		}
	
	}
	catch(Exception e){
		
		System.out.println(e.getStackTrace());
	}}
	
	
	
	
/*	WebDriver driver = null;
//	WebDriverWait wait= new WebDriverWait(driver, 10);
	JavascriptExecutor js= ((JavascriptExecutor)driver);
	WebElement outagetable=driver.findElement(By.id("outageListId_wrapper"));
	WebElement tbodyElement = driver.findElement(By.tagName("tbody"));
	List<WebElement> outagerows=tbodyElement.findElements(By.tagName("tr"));
	int outagerowscount=outagerows.size();
	System.out.println("Outage Size : "+ outagerowscount);
	try{
	for(int k=0;k<outagerows.size()-1;) {
		
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		Thread.sleep(4000);
		
		WebElement outageElement = outagerows.get(k);
		List<WebElement> tdElements = outageElement.findElements(By.tagName("td"));
		WebElement tdDeleteElement = tdElements.get(tdElements.size() - 1);
		
		WebElement deleteElement = tdDeleteElement.findElement(By.xpath("//span[@class='glyphicon glyphicon-remove btn btn-default btnDel']"));
		deleteElement.click();
		
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		Thread.sleep(2000);
	WebElement confirm=driver.findElement(By.xpath("//a[@class='btn btn-primary']"));
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='btn btn-primary']")));
	confirm.click();

	//wait.until(ExpectedConditions.elementToBeClickable(confirm));
	
	outagerows=tbodyElement.findElements(By.tagName("tr"));
	
	}
	
	}
	catch(Exception e){
		
	
	}

	//outagerows=outagetable.findElements(By.tagName("tr"));
	
	
}*/


public void outageAdd() throws Exception {
	try {

		Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
		pentry = Utilities.returnElement(driver, mapObjProjectData.get("Project").get(0),
				mapObjProjectData.get("Project").get(1));
		
		pentry.click();
		int i = 0;
			Thread.sleep(2000);
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
			Thread.sleep(3000);
			title = Utilities.returnElement(driver, mapObjProjectData.get("Title").get(0),
					mapObjProjectData.get("Title").get(1));
			title.sendKeys(lstTestData.get(i + 2));
			desc = Utilities.returnElement(driver, mapObjProjectData.get("Description").get(0),
					mapObjProjectData.get("Description").get(1));
			desc.sendKeys(lstTestData.get(i + 3));
			Thread.sleep(4000);
			projTyp = Utilities.returnElement(driver, mapObjProjectData.get("projtype").get(0),
					mapObjProjectData.get("projtype").get(1));
		
			Select pt=new Select(projTyp);
			pt.selectByIndex(2);
			projNum = Utilities.returnElement(driver, mapObjProjectData.get("projnum").get(0),
					mapObjProjectData.get("projnum").get(1));
			projNum.sendKeys(lstTestData.get(i + 4));
			Thread.sleep(3000);
			costCentre = Utilities.returnElement(driver, mapObjProjectData.get("costcentre").get(0),mapObjProjectData.get("costcentre").get(1));
			Select st=new Select(costCentre);
			st.selectByIndex(3);

			wrkCentre = Utilities.returnElement(driver, mapObjProjectData.get("WorkCentre").get(0),mapObjProjectData.get("WorkCentre").get(1));
			wrkCentre.sendKeys(lstTestData.get(i + 5));
	//		PGPersonalInfo df=new PGPersonalInfo(driver);
		//	df.personalInfoManual();
			
			save = Utilities.returnElement(driver, mapObjProjectData.get("Save").get(0),mapObjProjectData.get("Save").get(1));
				save.click();
				
		PGPersonalinfosection outagesection = new PGPersonalinfosection(driver);
		outagesection.projectentryoutage();
		
			Thread.sleep(2000);
			
			dleteOutage();
			
		Extent_Reports.executionLog("PASS", "Several Outage requests are deleted successfully", driver);
	
			Thread.sleep(2000);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollTo(0, 0)");
				saveAndReturn = Utilities.returnElement(driver, mapObjProjectData.get("saveandreturn").get(0),mapObjProjectData.get("saveandreturn").get(1));
				saveAndReturn.click();

				viewOutageTable=driver.findElement(By.xpath("//*[@id='outageListIdView']/tbody"));
				List<WebElement>viewOutageRows=viewOutageTable.findElements(By.tagName("tr"));
				ArrayList<String>viewOutageId=new ArrayList<String>();
				
				System.out.println(viewOutageRows);
				for(int k=1;k<=viewOutageRows.size();k++) {
				String voids=driver.findElement(By.xpath("//*[@id='outageListIdView']/tbody/tr["+k+"]/td[1]")).getText();
				viewOutageId.add(voids);
				String currentwindow=driver.getWindowHandle();
				driver.findElement(By.xpath("//*[@id='outageListIdView']/tbody/tr["+k+"]/td[1]/div/a")).click();
				 Thread.sleep(2000);
				Set<String>totalWindows=driver.getWindowHandles();
				for(String windows:totalWindows) {
				if(!windows.equals(currentwindow)) {
					 Thread.sleep(2000);
					driver.switchTo().window(windows);
					 Thread.sleep(2000);
				 String requesturl=driver.getCurrentUrl();
				 Thread.sleep(2000);
				 if(requesturl.contains(voids)) {
				Extent_Reports.executionLog("PASS", "Linked request is redirecting to correct page only", driver);
				driver.close();
				 Thread.sleep(2000);
			
				}
			//	 else {
			//		 Extent_Reports.executionLog("Fail", "Linked request is not redirecting to correct page", driver); 
			//	 }
				 }driver.switchTo().window(currentwindow);
				}
				}
				System.out.println("OutageID's in the view page "+viewOutageId);
				
				
			String projectNum=driver.findElement(By.xpath("//*[@class='inlineBlock']")).getText();
			String[] viewHeader= projectNum.split("#");
			String projectnumber1=viewHeader[1];
			System.out.println(projectnumber1);
			
			
			 Return=Utilities.returnElement(driver, mapObjProjectData.get("return").get(0),mapObjProjectData.get("return").get(1));
			 Return.click();
			 filterStartDate = Utilities.returnElement(driver, mapObjProjectData.get("FilterStartDate").get(0),mapObjProjectData.get("FilterStartDate").get(1));
				filterStartDate.clear();
				Thread.sleep(2000);
				filterStartDate.sendKeys("01/01/2018");
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
			outageLink = Utilities.returnElement(driver, mapObjProjectData.get("Titlelink").get(0),
						mapObjProjectData.get("Titlelink").get(1));
			outageLink.click();
			Thread.sleep(2000);
			listOutageTable=driver.findElement(By.xpath("//*[@id='remarkList']/tbody"));
			List<WebElement>listOutageRows=listOutageTable.findElements(By.tagName("tr"));
			ArrayList<String>listOutageId=new ArrayList<String>();
			
			System.out.println(listOutageRows+"list rows");
			for(int j=1;j<=listOutageRows.size();j++) {
			String oid=driver.findElement(By.xpath("//*[@id='remarkList']/tbody/tr["+j+"]/td[1]")).getText();
			listOutageId.add(oid);
			}
			System.out.println("OutageID's in the list page "+listOutageId);
			assertEquals(listOutageId, viewOutageId);
				Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "Created Record is displayed", driver);
			} else {
				Extent_Reports.executionLog("Fail", Extent_Reports.logExpected + "Created Record is not displayed", driver);
			}

		Extent_Reports.executionLog("PASS", "Project entry outage section is working as expected", driver);
	}

	catch (Exception exc) {

		System.out.println(exc.getMessage());
		exc.printStackTrace();
		 Extent_Reports.executionLog("FAIL","Project entry outage section is not working as expected",driver);
		
	}
}


}











