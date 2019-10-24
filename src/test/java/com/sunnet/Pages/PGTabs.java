package com.sunnet.Pages;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.sunnet.GenericLib.DatabaseFunction;
import com.sunnet.GenericLib.Extent_Reports;
import com.sunnet.GenericLib.GenericFunctions;
import com.sunnet.GenericLib.Utilities;
import com.sunnet.utils.ProjectUtily;

public class PGTabs {

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

	// =============================Constructor to initialize all the Page Objects==========================================================
	public PGTabs(WebDriver driver)

	{
		try {
			this.driver = driver;
			lstObject = ProjectUtily.getObjProjectData(db);
			lstTestData = db.getTestDataObject("Select * from Tabs", "Input");
		
		}
		
		catch (Exception exc) {

			System.out.println("Exception in Constructor." + exc.getMessage());

		}
	}

	// ================================BUSINESS VALIDATION LOGIC=================================================

	
	public void verifyProjecttabs() throws Exception {
		//	Random random = new Random();
			


		try {

			Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
			  // Map<String, String> maps = new HashMap<String, String>();
			pentry = Utilities.returnElement(driver, mapObjProjectData.get("Project").get(0),
					mapObjProjectData.get("Project").get(1));
			
			pentry.click();
			int i = 0;
			while (i < 6) {
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
			//	endTime = Utilities.returnElement(driver, mapObjProjectData.get("Endtime").get(0),mapObjProjectData.get("Endtime").get(1));
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
				pt.selectByIndex(1);
				projNum = Utilities.returnElement(driver, mapObjProjectData.get("projnum").get(0),
						mapObjProjectData.get("projnum").get(1));
				projNum.sendKeys(lstTestData.get(i + 4));
				Thread.sleep(3000);
				costCentre = Utilities.returnElement(driver, mapObjProjectData.get("costcentre").get(0),mapObjProjectData.get("costcentre").get(1));
				Select st=new Select(costCentre);
				st.selectByIndex(1);
				wrkCentre = Utilities.returnElement(driver, mapObjProjectData.get("WorkCentre").get(0),mapObjProjectData.get("WorkCentre").get(1));
				wrkCentre.sendKeys(lstTestData.get(i + 5));
			//	PGPersonalInfo df=new PGPersonalInfo(driver);
			//	df.personalInfoManual();
				
			Thread.sleep(2000);
			 saveAndReturn = Utilities.returnElement(driver, mapObjProjectData.get("saveandreturn").get(0),mapObjProjectData.get("saveandreturn").get(1));
				saveAndReturn.click();
				Thread.sleep(3000);
				String projectNum=driver.findElement(By.xpath("//*[@class='inlineBlock']")).getText();
				String[] viewHeader= projectNum.split("#");
				String projectnumber1=viewHeader[1];
				System.out.println(projectnumber1);
			
			/*	driver.findElement(By.linkText("Remarks")).click();
				for(int j=2;j<6;j++) {
				driver.findElement(By.id("remark")).sendKeys("textarea");
				Thread.sleep(1000);
				driver.findElement(By.id("addRemark")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr[2]/td[3]/label[2]")).click();
				System.out.println(j);
				Thread.sleep(1000);
				driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr[2]/td[3]/textarea")).sendKeys("edit the data"+j);
				Thread.sleep(1000);
				driver.findElement(By.xpath("//*[@id='saveRemarksComment']")).click();
				Thread.sleep(1000);
				String tabletext=driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr[2]/td[3]")).getText();
				System.out.println(tabletext);
				driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr[2]/td[3]/label[2]")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr[2]/td[3]/textarea")).sendKeys("clear the data"+j);
				Thread.sleep(1000);
			
				driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr[2]/td[3]/label[3]/a/button[2]")).click();
				Thread.sleep(1000);
				String tabletext1=driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr[2]/td[3]")).getText();
				System.out.println(tabletext1);
				assertEquals(tabletext, tabletext1);
			//	driver.findElement(By.xpath("//*[@id='remarkList']/tbody/tr[2]/td[4]/button")).click();	
			//	driver.findElement(By.xpath("//*[@id='confirmDelete']/div/div/div[3]/a")).click();
				}
				Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "Remarks added and Edited", driver);

				driver.findElement(By.linkText("Remove Grouping")).click();
				WebElement table=driver.findElement(By.xpath("//*[@id='remarkList']/thead/tr"));
				List<WebElement> cols=table.findElements(By.tagName("th"));
				System.out.println(cols.size());
				/*for(int j=2;j<6;j++) {
				driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr["+j+"]/td[3]/label[2]")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr["+j+"]/td[3]/textarea")).sendKeys("clear the data"+j);
				Thread.sleep(1000);
			
				driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr["+j+"]/td[3]/label[3]/a/button[2]")).click();
				Thread.sleep(1000);
				}
				for(int j=2;j<6;j++) {
					driver.findElement(By.xpath("//*[@id='remarkList']/tbody/tr["+j+"]/td[4]/button")).click();	
					driver.findElement(By.xpath("//*[@id='confirmDelete']/div/div/div[3]/a")).click();	
					}
				Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "Remarks Deleted", driver);
				//verifyattachtabs();*/
				/*
				 * verifyRemarksTab(); verifyattachTabs(); verifyEmailTabs();
				 */
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
	public void  verifyRemarksTab() throws Exception {
		
		driver.findElement(By.linkText("Remarks")).click();
		for(int j=06;j<12;j++) {
			String remarkstd=lstTestData.get(j);
		if(remarkstd.isEmpty())
		{
			break;
		}
		else{driver.findElement(By.id("remark")).sendKeys(remarkstd);
		Thread.sleep(1000);
		driver.findElement(By.id("addRemark")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr[2]/td[3]/label[2]")).click();
		System.out.println(j);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr[2]/td[3]/textarea")).sendKeys("Edit"+remarkstd);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='saveRemarksComment']")).click();
		Thread.sleep(1000);
		String tabletext=driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr[2]/td[3]")).getText();
		System.out.println(tabletext);
		driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr[2]/td[3]/label[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr[2]/td[3]/textarea")).sendKeys("clear the data"+j);
		Thread.sleep(1000);
	
		driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr[2]/td[3]/label[3]/a/button[2]")).click();
		Thread.sleep(1000);
		String tabletext1=driver.findElement(By.xpath("//table[@id='remarkList']/tbody/tr[2]/td[3]")).getText();
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
			driver.findElement(By.xpath("//*[@id='remarkList']/tbody/tr["+c+"]/td[4]/button")).click();	
			driver.findElement(By.xpath("//*[@id='confirmDelete']/div/div/div[3]/a")).click();	
			}
		Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "Remarks Deleted", driver);
	}
	
	public void verifyattachTabs() throws Exception{
		
		ArrayList<String>links=new ArrayList<String>();
		ArrayList<String>checkB=new ArrayList<String>();
		ArrayList<String>emailCheckB=new ArrayList<String>();
		driver.findElement(By.linkText("Attach")).click();
		for(int j=1;j<18;j++) {
			String text=lstTestData.get(j+11);
			System.out.println("data text : "+text);
			if(text.isEmpty()) {
				driver.findElement(By.id("fileupload")).sendKeys("C:\\Users\\sunilkumar.a\\Desktop\\test management tools.docx");
				driver.findElement(By.id("upload-button")).click();
				 String link  =driver.findElement(By.xpath("//*[@id='fileList']/tbody/tr["+j+"]/td[2]")).getText();
				 links.add(link);
				break;
			}else {
		driver.findElement(By.id("addHyperlinkBtn")).click();
		Select se=new Select(driver.findElement(By.id("hyperlinkType")));
		se.selectByVisibleText("http://");
		driver.findElement(By.id("hyperlink")).sendKeys(text);
		driver.findElement(By.id("addHyperlink")).click();
		Thread.sleep(3000);
	     String link  =driver.findElement(By.xpath("//*[@id='fileList']/tbody/tr["+j+"]/td[2]")).getText();
	     links.add(link);
	  
	     Thread.sleep(1000);
			}}
		System.out.println(links);//links
		driver.findElement(By.linkText("Email")).click();
		List<WebElement>cb=driver.findElements(By.xpath("//*[@class='displayInlineBlock']"));
		for(WebElement checkboxes:cb) {
			checkB.add(checkboxes.getText());
		}
		Assert.assertEquals(links,checkB);
		System.out.println(checkB);//check boxes
			//editing the attachment data
		driver.findElement(By.partialLinkText("Attac")).click();
			WebElement table1 = driver.findElement(By.xpath("//*[@id='fileList']/tbody"));
			List<WebElement> rowcount = table1.findElements(By.tagName("tr"));
			System.out.println("attachment tab"+rowcount.size());
			
			for(int d=1;d<=rowcount.size();d++) {
	//		String attachtabcb=driver.findElement(By.xpath("//*[@id='fileList']/tbody/tr[1]/td[2]")).getText();
	/*		if(d<rowcount.size()) {
				Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='fileList']/tbody/tr[1]/td[5]/a")).click();
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("#confirmDeleteAttach > div > div > div.modal-footer > a")).click();
			Thread.sleep(2000);
			WebElement table12 = driver.findElement(By.xpath("//*[@id='fileList']/tbody"));
			List<WebElement> rowcounts = table12.findElements(By.tagName("tr"));
			driver.findElement(By.linkText("Email")).click();
			List<WebElement>cba=driver.findElements(By.xpath("//*[@class='displayInlineBlock']"));
			assertEquals(rowcounts.size(), cba.size());
			driver.findElement(By.partialLinkText("Attac")).click();
			}
			}*/
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='fileList']/tbody/tr[1]/td[5]/a")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='confirmDeleteAttach']/div/div/div[3]/a")).click();
			Thread.sleep(1000);
			}
			if(driver.findElement(By.xpath("//*[@id='attachTab']/div[1]")).isDisplayed()==true){
			   driver.findElement(By.linkText("Email")).click();
		//   Thread.sleep(1000);
		//	assertTrue(driver.findElement(By.xpath("//*[@class='displayInlineBlock']")).isDisplayed()==false);	
			System.out.println("pass");
			}
			else {
				System.out.println("fail");	
				}
			}
			
	
	

	public void verifyEmailTabs() throws Exception {
		
	//	driver.findElement(By.linkText("Projects List")).click();
	//	driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div/div/div[2]/table/tbody/tr[18]/td[2]")).click();
		driver.findElement(By.partialLinkText("Emai")).click();
		System.out.println(lstTestData.size());
	  for(int k=18;k<24;k++) {
		if(lstTestData.get(k).isEmpty()) {
			break;
		}else {
		driver.findElement(By.xpath("//div[@id='s2id_emailTo']/ul/li/input")).sendKeys(lstTestData.get(k));
		Thread.sleep(2000);	
		driver.findElement(By.xpath("//div[@id='s2id_emailTo']/ul/li/input")).sendKeys(Keys.ENTER);
	}}
	  driver.findElement(By.xpath("//*[@id='aShowBcc']")).click();
	driver.findElement(By.xpath("//input[@id='aShowCc']")).click();
	//driver.findElement(By.xpath("//*[@id='aShowBcc']")).click();
	Thread.sleep(2000);
	for(int l=24;l<30;l++) {
		if(lstTestData.get(l).isEmpty()) {
			break;
		}else {
		driver.findElement(By.xpath("//div[@id='s2id_emailCc']/ul/li/input")).sendKeys(lstTestData.get(l));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='s2id_emailCc']/ul/li/input")).sendKeys(Keys.ENTER);
		}}
		System.out.println("done emailAdditionalText .. ");
		Thread.sleep(2000); 
		for(int m=30;m<36;m++) {
			if(lstTestData.get(m).isEmpty()) {
				break;
			}else {
			driver.findElement(By.xpath("//div[@id='s2id_emailBcc']/ul/li/input")).sendKeys(lstTestData.get(m));
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@id='s2id_emailBcc']/ul/li/input")).sendKeys(Keys.ENTER);
			}}
		driver.findElement(By.xpath("//textarea[@name='emailAdditionalText']")).sendKeys("Additional Text..123");
		driver.findElement(By.xpath("//input[@id='sendEmailBtn']")).click();
		Thread.sleep(2000);
		System.out.println("Button click done");
	
		// Validations
		String Recipients = driver.findElement(By.xpath("//td[@data-title='Recipients']")).getText();
		String[] rec=Recipients.split("cc");
		System.out.println(rec);
		Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "Email tab is working successfully", driver);		
		System.out.println("Recipients are .. "+Recipients);
		Thread.sleep(6000);	
	/*	for(int i = 1; i < 7; i++)
		{
		String Recipients = driver.findElement(By.xpath("//td[@data-title='Recipients']")).getText();
		}
		//table[@class='table table-bordered table-striped table-condensed dataTable']/tbody/tr[1]/td[3]
		
		*/
		
	}
	
	
}
