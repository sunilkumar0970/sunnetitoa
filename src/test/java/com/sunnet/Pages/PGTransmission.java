package com.sunnet.Pages;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.sunnet.GenericLib.DatabaseFunction;
import com.sunnet.GenericLib.Extent_Reports;
import com.sunnet.GenericLib.GenericFunctions;
import com.sunnet.GenericLib.Synchronization;
import com.sunnet.GenericLib.Utilities;
import com.sunnet.utils.ProjectUtily;

public class PGTransmission {
	
	DatabaseFunction db = new DatabaseFunction();
	GenericFunctions GFObj=new GenericFunctions();
	private WebDriver driver;
	public List<String>lstObject,lstTestData;
	WebElement action,requestSubmit,comment,confirmSubmit,Return,filtericon,requestFilter,srchResult1;
	String RequestEntryNumber,reqNum;
	boolean srchResult;
	
public PGTransmission(WebDriver driver){
	
	this.driver=driver;
		
	lstObject = ProjectUtily.getObjProjectData(db);
	lstTestData = db.getTestDataObject("Select * from Tabs", "Input");
		
	}
public void requestEntry() throws InterruptedException {
	try{

	Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
	System.out.println("start");
	Synchronization.implicitWait(driver, 10);
	
	driver.findElement(By.partialLinkText("Request Ent")).click();
	driver.findElement(By.xpath("//*[@id='editedOutage.tagType.lookupCodeId']")).click();
	driver.findElement(By.xpath("//*[@id='editedOutage.tagType.lookupCodeId']")).click();
	driver.findElement(By.xpath("//*[@id='editedOutage.applicationType']")).click();
	driver.findElement(By.xpath("//*[@class='row distribution-affected']/div/div/div/label[1]")).click();
	driver.findElement(By.xpath("//*[@id='editedOutage.controlCenter.controlCenterId']")).click();
	driver.findElement(By.xpath("//*[@id='schedStartDate']")).sendKeys("10/26/2019");
	driver.findElement(By.xpath("//*[@id='scheduleProfile']")).click();
	driver.findElement(By.xpath("//*[@id='scheduleProfile']/div/div/ul/li[2]")).click();
//prapare from here
	driver.findElement(By.xpath("//*[@id='editedOutage.emergencyRestoreTime']")).sendKeys("3");
	driver.findElement(By.xpath("//*[@id='editedOutage.tagType.lookupCodeId']")).click();
	Thread.sleep(2000);
	System.out.println("12");
	driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/form[1]/div[5]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]")).click();
	Thread.sleep(2000);
	System.out.println("123");
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@class='btn-group bootstrap-select inlineBlock required open']/div/ul/li[3]")).click();
	Thread.sleep(2000);
	
//	Select time=new Select(driver.findElement(By.id("editedOutage.emergencyRestoreTimeC")));
//	time.selectByIndex(3);
	driver.findElement(By.xpath("//*[@id='switchStartDate']")).sendKeys("10/26/2019");
	driver.findElement(By.xpath("//*[@class='timepick input-group date switchStart']/input")).sendKeys("16:10");
	driver.findElement(By.xpath("//*[@id='s2id_typeTitle']")).click();
	driver.findElement(By.xpath("//ul[@id='select2-results-17']/li[3]")).click();
	driver.findElement(By.xpath("//*[@id='s2id_stationTitle']")).click();
	Thread.sleep(5000);
	WebElement elementsImpactedUl=driver.findElement(By.xpath("//ul[@id='select2-results-18']"));
	List<WebElement>elementsImpactedLi=elementsImpactedUl.findElements(By.tagName("li"));
	driver.findElement(By.xpath("//*[@id='s2id_autogen18_search']")).sendKeys(getSubString(elementsImpactedLi.get(2).getText()));
	
	
	System.out.println("test12345");
	Thread.sleep(2000);
	driver.findElement(By.xpath("//ul[@id='select2-results-18']/li")).click();
	driver.findElement(By.xpath("//*[@id='voltageTitle_range_filter']")).click();
	driver.findElement(By.xpath("//div[@id='s2id_voltageTitle']")).click();
	//driver.findElement(By.xpath("//*[@id='select2-results-21']")).sendKeys(Keys.ARROW_DOWN);
	//driver.findElement(By.xpath("//div[@id='s2id_voltageTitle']")).sendKeys(Keys.ARROW_DOWN);
	
	Thread.sleep(5000);
	System.out.println("test123456");
	driver.findElement(By.xpath("/html[1]/body[1]/div[15]/ul/li[12]")).click();
	Thread.sleep(2000);
	System.out.println("test1");
	driver.findElement(By.xpath("//*[@id='s2id_equipmentTitle']/a")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//input[@id='s2id_autogen20_search']")).sendKeys("test");
	Thread.sleep(3000);
	//Actions ac=new Actions(driver);
	//ac.sendKeys(Keys.TAB);
	System.out.println("12");
	driver.findElement(By.xpath("//*[@id='select2-results-20']/li")).click();
	System.out.println("21");
	driver.findElement(By.xpath("//*[@id='addTitle']")).click();
	System.out.println("211");
	driver.findElement(By.xpath("//*[@id='s2id_type']")).click(); //check y it is not executing.
	System.out.println("212");
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[@id='select2-results-1']/li")).click();
	System.out.println("2");
	driver.findElement(By.xpath("//*[@id='s2id_station']")).click();
	Thread.sleep(5000);
	System.out.println("2115");
	driver.findElement(By.xpath("//*[@id='select2-results-2']/li[2]")).click();
	driver.findElement(By.xpath("//*[@id='s2id_voltage']")).click();
	Thread.sleep(5000);
	driver.findElement(By.xpath("//*[@id='select2-results-3']/li")).click();
	driver.findElement(By.xpath("//*[@id='s2id_equipment']")).click();
	driver.findElement(By.xpath("//*[@id='s2id_autogen11_search']")).sendKeys("elements involved");
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[@id='select2-results-11']/li")).click();
	driver.findElement(By.xpath("//*[@id='addEqui']")).click();
	Thread.sleep(5000);
	JavascriptExecutor jse=(JavascriptExecutor)driver;
	jse.executeScript("window.scrollTo(0, 350)");
	
	driver.findElement(By.xpath("//*[@id='s2id_cleType']")).click();
	Thread.sleep(5000);
	driver.findElement(By.xpath("//*[@id='select2-results-14']/li")).click();
	driver.findElement(By.xpath("//*[@id='s2id_cleStation']")).click();
	Thread.sleep(5000);
	driver.findElement(By.xpath("//*[@id='select2-results-12']/li")).click();
	//driver.findElement(By.xpath("//div[@id='s2id_cleVoltage']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//div[@id='s2id_cleEquipment']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//input[@id='s2id_autogen15_search']")).sendKeys("test1");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[@id='select2-results-15']/li")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@id='addClearance']")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@id='editedOutage.clearancePointsDesc']")).sendKeys("clearance description field");
	jse.executeScript("window.scrollTo(0, 500)");
	driver.findElement(By.xpath("//*[@id='editedOutage.workDescription']")).sendKeys("work details field");
	driver.findElement(By.xpath("//*[@id='editedOutage.miscellaneousComments']")).sendKeys("work location details field");
	jse.executeScript("window.scrollTo(0, 600)");
	driver.findElement(By.xpath("//*[@id='editedOutage.outageWrNums[0].outageWrNumPk.wrNum']")).sendKeys("order1");
	driver.findElement(By.xpath("//*[@id='editedOutage.projectNum']")).sendKeys("task1");
	
	driver.findElement(By.xpath("//*[@id='collapseOne']/div/div/div[3]/div/div/button")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@id='collapseOne']/div/div/div[3]/div/div/div/ul/li[3]/a")).click();
	
	jse.executeScript("window.scrollTo(0, 800)");
	
	
	driver.findElement(By.xpath("//*[@id='s2id_editedOutage.requestBy']")).click();
	Thread.sleep(5000);
	driver.findElement(By.xpath("//*[@id='select2-results-8']/li")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@id='sameAsReq']")).click();
	driver.findElement(By.xpath("//*[@id='s2id_editedOutage.switchman']")).click();
	Thread.sleep(5000);
	driver.findElement(By.xpath("//*[@id='select2-results-10']/li")).click();
	
	
//	jse.executeScript("window.scrollTo(0, 950)");
	WebElement reqchecklst=driver.findElement(By.xpath("//*[@class='table table-condensed marginBottom0']/tbody"));
	List<WebElement> reqchecklstcount=reqchecklst.findElements(By.tagName("tr"));
	System.out.println("lasttablerows"+reqchecklstcount.size());
	
	for(int i=1;i<=reqchecklstcount.size();i++) {
	driver.findElement(By.xpath("//*[@class='table table-condensed marginBottom0']/tbody/tr["+i+"]/td[2]/div/label[2]")).click(); 
	}
	jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	driver.findElement(By.xpath("//*[@id='submitSaveReturn']")).click();
	Thread.sleep(3000);
	action=Utilities.returnElement(driver, mapObjProjectData.get("Action").get(0), mapObjProjectData.get("Action").get(1));
	action.click();
	Thread.sleep(2000);
	driver.findElement(By.linkText("Submit")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@id='comments']")).sendKeys("testcomments");
	driver.findElement(By.linkText("Confirm Submit")).click();
	Thread.sleep(3000);
	//requestSubmit=Utilities.returnElement(driver, mapObjProjectData.get("RequestSubmit").get(0), mapObjProjectData.get("RequestSubmit").get(1));
	//requestSubmit.click();
	Extent_Reports.executionLog("Pass","Request entry submitted successfully",driver);
	Thread.sleep(3000);
	driver.findElement(By.linkText("Tasks")).click();
	Thread.sleep(3000);
	driver.findElement(By.linkText("Edit")).click();
	Thread.sleep(3000);
	jse.executeScript("window.scrollBy(0,150)");
	driver.findElement(By.id("addTask")).click();
	driver.findElement(By.xpath("//*[@id='taskList']/tbody/tr/td[2]/div")).click();
	driver.findElement(By.xpath("//div[@id='select2-drop']/ul/li[6]")).click();
	Thread.sleep(3000);
//	driver.findElement(By.xpath("//*[@id='required']")).click();
//	Thread.sleep(3000);
	WebElement Table=driver.findElement(By.xpath("//*[@id='taskList']/tbody"));
	int tasksrows=Table.findElements(By.tagName("tr")).size();
	System.out.println("no.of task rows"+tasksrows);
//	driver.findElement(By.xpath("//*[@id='taskList']/tbody/tr["+tasksrows+"]/td[3]/input[5]")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//*[@id='taskList']/tbody/tr["+tasksrows+"]/td[5]/input[1]")).click();
	driver.findElement(By.xpath("//*[@id='taskList']/tbody/tr["+tasksrows+"]/td[13]/div/a")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li[2]")).click();
	Thread.sleep(3000);
	jse.executeScript("window.scrollTo(document.body.scrollHeight,0)");
//	Thread.sleep(3000);
//	driver.findElement(By.xpath("//*[@id='taskForm']/div[4]/button")).click();
	driver.findElement(By.xpath("//*[@class='form-actions-top taskTab']/button")).click();
	Thread.sleep(2000);
//	jse.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	driver.findElement(By.xpath("//*[@id='required']")).click();
	jse.executeScript("window.scrollBy(300,0)", "");
	Thread.sleep(4000);
	for(int i=1;i<=tasksrows;i++) {
		
		
		if(driver.findElement(By.xpath("//*[@id='taskList']/tbody/tr["+i+"]/td[4]/input[1]")).isDisplayed()) {
			
			if(driver.findElement(By.xpath("//*[@id='taskList']/tbody/tr["+i+"]/td[4]/input[1]")).isSelected()) {
				
			}
			else {
		driver.findElement(By.xpath("//*[@id='taskList']/tbody/tr["+i+"]/td[4]/input[1]")).click();
		driver.findElement(By.xpath("//*[@id='taskList']/tbody/tr["+i+"]/td[6]/div/a")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li[38]")).click();
		//	driver.findElement(By.xpath(""))
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='taskList']/tbody/tr["+i+"]/td[7]/div/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li[1]")).click();
		driver.findElement(By.xpath("//*[@id='taskList']/tbody/tr["+i+"]/td[8]/div/div/input")).sendKeys("10/25/2019");
		driver.findElement(By.xpath("//*[@id='taskList']/tbody/tr["+i+"]/td[12]/input")).sendKeys("test"+i);
		
			}}
	
	}
	Extent_Reports.executionLog("Pass","Request entry required tasks are working as expected",driver);
	Thread.sleep(3000);
	driver.findElement(By.id("taskForm_2")).click();
	Thread.sleep(3000);
	Extent_Reports.executionLog("Pass","Total Request entry tasks are working as expected",driver);
	driver.findElement(By.linkText("Remarks")).click();
	for(int j=06;j<12;j++) {
		String remarkstd=lstTestData.get(j);
	if(remarkstd.isEmpty())
	{
		break;
	}
	else{
		driver.findElement(By.id("remark")).sendKeys(remarkstd);
	Thread.sleep(1000);
	driver.findElement(By.id("addRemark")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr[2]/td[4]/label[2]")).click();
	System.out.println(j);
	Thread.sleep(5000);
	driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr[2]/td[4]/textarea")).sendKeys("Edit"+remarkstd);
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@id='saveRemarksComment']")).click();
	Thread.sleep(3000);
	String tabletext=driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr[2]/td[4]")).getText();
	System.out.println(tabletext);
	driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr[2]/td[4]/label[2]")).click();
	Thread.sleep(4000);
	driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr[2]/td[4]/textarea")).sendKeys("clear the data"+j);
	Thread.sleep(2000);

	driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr[2]/td[4]/label[3]/a/button[2]")).click();
	Thread.sleep(2000);
	String tabletext1=driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr[2]/td[4]")).getText();
	System.out.println(tabletext1);
	assertEquals(tabletext, tabletext1);
	
	}}
	
	Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "Remarks added and Edited", driver);

	WebElement table=driver.findElement(By.xpath("//*[@id='remarkList']/thead/tr"));
	List<WebElement> cols=table.findElements(By.tagName("th"));
	driver.findElement(By.partialLinkText("Remove")).click();
	driver.findElement(By.partialLinkText("Show")).click();
	WebElement tableh=driver.findElement(By.xpath("//*[@id='remarkList']/thead/tr"));
	List<WebElement> colsh=tableh.findElements(By.tagName("th"));
	assertEquals(cols.size(), colsh.size());
	System.out.println(cols.size());
	WebElement remarktable=driver.findElement(By.xpath("//*[@id='remarkList']/tbody"));
	List<WebElement>rows=remarktable.findElements(By.tagName("tr"));
	System.out.println(rows.size()+"rows count");
	for(int c=2;c<=rows.size();c++) {
		driver.findElement(By.xpath("//*[@id='remarkList']/tbody/tr["+c+"]/td[5]/button")).click();	
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id='confirmDelete']/div/div/div[3]/a")).click();
		Thread.sleep(3000);
		}
	Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "Remarks Deleted", driver);
	Thread.sleep(3000);
//	WebElement element = driver.findElement(By.id("id_of_element"));
//	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//	element.click();
	driver.findElement(By.linkText("Resources")).click();
	driver.findElement(By.linkText("Edit")).click();
	Thread.sleep(2000);
	List<WebElement> resrctimes=driver.findElements(By.linkText("Return"));
	System.out.println(resrctimes.size()+"total Returns");
//	driver.findElement(By.xpath("//*[@id='shortTermSwitchingArrangement']/div/div/button")).click();
	Thread.sleep(2000);
	
	for(int i=0;i<=resrctimes.size();i++) {
	if(i<resrctimes.size()) {
	driver.findElement(By.xpath("//*[contains(text(),'Add')]")).click();
	Thread.sleep(2000);
	}	
	else {
		driver.findElement(By.xpath("//*[@id='switchingPersonList']/tbody/tr[1]/td[1]/button")).click();
		Thread.sleep(2000);
	}
	driver.findElement(By.xpath("//*[@id='s2id_switchman.switchmanSubstation']/a")).click();
	Thread.sleep(8000);
	driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li["+i+1+"]")).click();
	driver.findElement(By.xpath("//*[@id='s2id_switchman.personIntv']/a")).click();
	Thread.sleep(8000);
	driver.findElement(By.xpath("//*[@id='select2-drop']/ul/li["+(i+1)+"]")).click();
	driver.findElement(By.xpath("//*[@id='switchman.cell']")).clear();
	driver.findElement(By.xpath("//*[@id='switchman.cell']")).sendKeys("7894561351");
	
	driver.findElement(By.xpath("//*[@id='switchmanNeededDiv']/div/button")).click();
	driver.findElement(By.xpath("//*[@id='switchmanNeededDiv']/div/div/ul/li[5]")).click();
	driver.findElement(By.xpath("//*[@id='switchman.switchmanDate']")).sendKeys("10/26/2019");
	driver.findElement(By.xpath("//*[@id='editSwitchman']/div[6]/div/button")).click();
	driver.findElement(By.xpath("//*[@id='editSwitchman']/div[6]/div/div/ul/li[4]")).click();
	driver.findElement(By.xpath("//*[@id='switchman.remarks']")).sendKeys("test1");
	driver.findElement(By.xpath("//*[@id='saveSwitchman']")).click();
	Thread.sleep(2000);	
	}
	Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "Resources added", driver);
	driver.findElement(By.xpath("//*[@id='switchingPersonList']/tbody/tr[2]/td[9]/button")).click();
	driver.findElement(By.xpath("//*[@id='shortTermPlanningForm_0']")).click();
	Thread.sleep(2000);
	Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "Resource deleted", driver);
	
	}
	catch(Exception e){
		e.printStackTrace();}
	}

private String getSubString(String str) {
	
	if(str.indexOf('(')!=-1) {
		return str.substring(0, str.indexOf('(')-1);
	}	
	return str;	
}

}


