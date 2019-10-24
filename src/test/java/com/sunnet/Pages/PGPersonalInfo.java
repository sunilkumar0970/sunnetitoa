package com.sunnet.Pages;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.sunnet.GenericLib.DatabaseFunction;
import com.sunnet.GenericLib.GenericFunctions;

public class PGPersonalInfo {

	// =========================================Variables=================================================================================
	DatabaseFunction db = new DatabaseFunction();
	GenericFunctions GFObj=new GenericFunctions();
	private WebDriver driver;
	List<WebElement> elements;
	public List<String> lstObject, lstTestData, lstRoleData, lstRoleUpdateData;

	WebElement objUserName,objPassWord,objLoginButton,objProfileElement,Pentry;
	//WebElement objpassWord;
	//WebElement objloginButton, objProfileElement;
    String expurl="https://sunnetqa.anblicks.com/itoa/login.htmlx";
    
    Random rand = new Random();
    
	// =============================Constructor to initialize all the Page Objects==========================================================
	public PGPersonalInfo(WebDriver driver) 

	{
		try 
		{
			this.driver = driver;
			lstObject = db.getTestDataObject("Select * from Loginpage", "ObjectRepository");
			lstTestData = db.getTestDataObject("Select * from Loginpage", "Input");
			lstRoleData = db.getTestDataObject("Select * from personalInfoData", "Input");
			lstRoleUpdateData = db.getTestDataObject("Select * from PersonalInfoUpdated", "Input");
		} 
		catch (Exception exc) 
		{

			System.out.println("Exception in Constructor."+exc.getMessage());

		}
	}	
	

	// ================================BUSINESS VALIDATION LOGIC================================================= 


	
	public void personalInfoAuto() {
		try {
			 staticAutoConent();
				
		     Map<Integer, List<String>> mapElementsofPersonnelInfo = getMapElementsofPersonnelInfo(lstRoleData);
		     
		     JavascriptExecutor jse = (JavascriptExecutor)driver;
		     
		     for(Map.Entry<Integer,List<String>> entry : mapElementsofPersonnelInfo.entrySet()) {

					jse.executeScript("window.scrollTo(0, 4000)");
					
					List<String> personnelDataCsv = entry.getValue();
					
					clickElement("addPersonnel", "id", 1500);

					List<WebElement> tdElementsByTableId = getStringIdByTable("responsiblePersonnel",-1);
					
					WebElement roleWebElement = tdElementsByTableId.get(0);
					WebElement roleElement = roleWebElement.findElement(By.tagName("div"));
					clickElement(roleElement.getAttribute("id"), "id", 2000);

					String attribute1 = roleElement.findElement(By.tagName("input")).getAttribute("id");
					String ulList1 = roleElement.findElement(By.tagName("input")).getAttribute("aria-labelledby");

					clickElement(SearchStringByListAndId(personnelDataCsv.get(0), replaceString(ulList1, "chosen", "results"), concatString(attribute1, "_search")), "id", 0);

					WebElement nameWebElement = tdElementsByTableId.get(1);
					WebElement nameElement = nameWebElement.findElement(By.tagName("div"));
					clickElement(nameElement.getAttribute("id"), "id", 2000);

					String attribute = nameElement.findElement(By.tagName("input")).getAttribute("id");
					String ulList = nameElement.findElement(By.tagName("input")).getAttribute("aria-labelledby");
					String newUlString = replaceString(ulList, "chosen", "results");
					
					List<WebElement> dropdownList = getDropdownList(newUlString);

					clickElement(SearchStringByListAndId(dropdownList.get(getRandomNumber(dropdownList.size()-1)).getText(), newUlString, concatString(attribute, "_search")), "id", 0);
				/*
				 * WebElement phoneWebElement = tdElementsByTableId.get(2);
				 * enterTheInput(phoneWebElement.findElement(By.tagName("input")).getAttribute(
				 * "id"), personnelDataCsv.get(2), 1000);
				 * 
				 * WebElement cellwebElement = tdElementsByTableId.get(3);
				 * enterTheInput(cellwebElement.findElement(By.tagName("input")).getAttribute(
				 * "id"), personnelDataCsv.get(3), 1000);
				 * 
				 * WebElement emailWebElement = tdElementsByTableId.get(4);
				 * enterTheInput(emailWebElement.findElement(By.tagName("input")).getAttribute(
				 * "id"), personnelDataCsv.get(4), 1000);
				 */
					
					WebElement notifyWebElement = tdElementsByTableId.get(5);
					clickElement(notifyWebElement.findElement(By.tagName("input")).getAttribute("id"), "id", 1000);
					
				
		    	 
		    	 
		    	 
		    	 
		     }
		     
		     
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public void personalInfoManual() {
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		try {
			
			WebElement table = driver.findElement(By.id("responsiblePersonnel"));
			
			WebElement tbody = table.findElement(By.tagName("tbody"));
			
			List<WebElement> trElements = tbody.findElements(By.tagName("tr"));
			
			System.out.println("trElements : "+trElements.size());
			
			
			if(trElements.size() <= 2) {
		    staticConent();
			
			Map<Integer, List<String>> mapElementsofPersonnelInfo = getMapElementsofPersonnelInfo(lstRoleData);
			
			for(Map.Entry<Integer,List<String>> entry : mapElementsofPersonnelInfo.entrySet()) {
				
				jse.executeScript("window.scrollTo(0, 4000)");
				
				List<String> personnelDataCsv = entry.getValue();
				
				clickElement("addPersonnel", "id", 1500);

				List<WebElement> tdElementsByTableId = getStringIdByTable("responsiblePersonnel",-1);
				
				WebElement roleWebElement = tdElementsByTableId.get(0);
				WebElement roleElement = roleWebElement.findElement(By.tagName("div"));
				clickElement(roleElement.getAttribute("id"), "id", 2000);

				String attribute1 = roleElement.findElement(By.tagName("input")).getAttribute("id");
				String ulList1 = roleElement.findElement(By.tagName("input")).getAttribute("aria-labelledby");

				clickElement(SearchStringByListAndId(personnelDataCsv.get(0), replaceString(ulList1, "chosen", "results"), concatString(attribute1, "_search")), "id", 0);

				WebElement nameWebElement = tdElementsByTableId.get(1);
				WebElement nameElement = nameWebElement.findElement(By.tagName("div"));
				clickElement(nameElement.getAttribute("id"), "id", 2000);

				String attribute = nameElement.findElement(By.tagName("input")).getAttribute("id");
				String ulList = nameElement.findElement(By.tagName("input")).getAttribute("aria-labelledby");

				clickElement(SearchStringByListAndId(personnelDataCsv.get(1), replaceString(ulList, "chosen", "results"), concatString(attribute, "_search")), "id", 0);

				WebElement phoneWebElement = tdElementsByTableId.get(2);
				enterTheInput(phoneWebElement.findElement(By.tagName("input")).getAttribute("id"), personnelDataCsv.get(2),
						1000);
				
				WebElement cellwebElement = tdElementsByTableId.get(3);
				enterTheInput(cellwebElement.findElement(By.tagName("input")).getAttribute("id"), personnelDataCsv.get(3), 1000);
				
				WebElement emailWebElement = tdElementsByTableId.get(4);
				enterTheInput(emailWebElement.findElement(By.tagName("input")).getAttribute("id"), personnelDataCsv.get(4),
						1000);
				
				WebElement notifyWebElement = tdElementsByTableId.get(5);
				clickElement(notifyWebElement.findElement(By.tagName("input")).getAttribute("id"), "id", 1000);
				
			}
			
			}
			
			
			
			else {
				 staticUpdatedConent();
				 
				 int i = 2;
				 
				 Map<Integer, List<String>> mapElementsofPersonnelInfo = getMapElementsofPersonnelInfo(lstRoleUpdateData);
					
					for(Map.Entry<Integer,List<String>> entry : mapElementsofPersonnelInfo.entrySet()) {
						
						jse.executeScript("window.scrollTo(0, 4000)");
						
						List<String> personnelDataCsv = entry.getValue();

						List<WebElement> tdElementsByTableId = getStringIdByTable("responsiblePersonnel",i);
						
						
						
						WebElement roleWebElement = tdElementsByTableId.get(0);
						WebElement roleElement = roleWebElement.findElement(By.tagName("div"));
						clickElement(roleElement.getAttribute("id"), "id", 2000);

						String attribute1 = roleElement.findElement(By.tagName("input")).getAttribute("id");
						String ulList1 = roleElement.findElement(By.tagName("input")).getAttribute("aria-labelledby");

						clickElement(SearchStringByListAndId(personnelDataCsv.get(0), replaceString(ulList1, "chosen", "results"), concatString(attribute1, "_search")), "id", 0);

						WebElement nameWebElement = tdElementsByTableId.get(1);
						WebElement nameElement = nameWebElement.findElement(By.tagName("div"));
						clickElement(nameElement.getAttribute("id"), "id", 2000);

						String attribute = nameElement.findElement(By.tagName("input")).getAttribute("id");
						String ulList = nameElement.findElement(By.tagName("input")).getAttribute("aria-labelledby");

						clickElement(SearchStringByListAndId(personnelDataCsv.get(1), replaceString(ulList, "chosen", "results"), concatString(attribute, "_search")), "id", 0);

						WebElement phoneWebElement = tdElementsByTableId.get(2);
						enterTheInput(phoneWebElement.findElement(By.tagName("input")).getAttribute("id"), personnelDataCsv.get(2),
								1000);
						
						WebElement cellwebElement = tdElementsByTableId.get(3);
						enterTheInput(cellwebElement.findElement(By.tagName("input")).getAttribute("id"), personnelDataCsv.get(3), 1000);
						
						WebElement emailWebElement = tdElementsByTableId.get(4);
						enterTheInput(emailWebElement.findElement(By.tagName("input")).getAttribute("id"), personnelDataCsv.get(4),
								1000);
						
						WebElement notifyWebElement = tdElementsByTableId.get(5);
						clickElement(notifyWebElement.findElement(By.tagName("input")).getAttribute("id"), "id", 1000);
						
						i++;
						
					}
				 
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void staticConent() {
		try {
			
			sleepTime(2000);
	//		clickElement("Project Entry","linktext",0);

			clickElement("s2id_project.projPerson1","id",2000);
			
            List<WebElement> tdElementsByTableId = getStringIdByTable("responsiblePersonnel",0);
			
			WebElement roleWebElement = tdElementsByTableId.get(1);
			WebElement roleElement = roleWebElement.findElement(By.tagName("div"));

			String attribute1 = roleElement.findElement(By.tagName("input")).getAttribute("id");
			String ulList1 = roleElement.findElement(By.tagName("input")).getAttribute("aria-labelledby");

			clickElement(SearchStringByListAndId("kranthi", replaceString(ulList1, "chosen", "results"), concatString(attribute1, "_search")), "id", 0);
			clearTheInput("project.projPerson1Phone",0);
			enterTheInput("project.projPerson1Phone","123-456-7890",1000);
			clearTheInput("project.projPerson1Cell",0);
			enterTheInput("project.projPerson1Cell","9876543210",1000);
			clearTheInput("project.projPerson1Email",0);
			enterTheInput("project.projPerson1Email","sunnetqa1@sunnet.com",1000);
			clickElement("project.projPerson1Notify","id",2000);
			
			
			clickElement("s2id_project.projPerson2","id",2000);
			
			 tdElementsByTableId = getStringIdByTable("responsiblePersonnel",1);
				
				roleWebElement = tdElementsByTableId.get(1);
				roleElement = roleWebElement.findElement(By.tagName("div"));

				 attribute1 = roleElement.findElement(By.tagName("input")).getAttribute("id");
				 ulList1 = roleElement.findElement(By.tagName("input")).getAttribute("aria-labelledby");

			clickElement(SearchStringByListAndId("sunil", replaceString(ulList1, "chosen", "results"), concatString(attribute1, "_search")), "id", 0);
			clearTheInput("project.projPerson2Phone",0);
			enterTheInput("project.projPerson2Phone","123-456-7890",1000);
			clearTheInput("project.projPerson2Cell",0);
			enterTheInput("project.projPerson2Cell","9876543210",1000);
			clearTheInput("project.projPerson2Email",0);
			enterTheInput("project.projPerson2Email","sunnetqa2@sunnet.com",1000);
			clickElement("project.projPerson2Notify","id",2000);
			
		} catch (Exception e) {
		     e.printStackTrace();
		}
		
		
	}
	
	
	private void staticUpdatedConent() {
		try {
			
			sleepTime(2000);
	//		clickElement("Project Entry","linktext",0);
			
			
			clickElement("s2id_project.projPerson1","id",2000);
			
            List<WebElement> tdElementsByTableId = getStringIdByTable("responsiblePersonnel",0);
			
			WebElement roleWebElement = tdElementsByTableId.get(1);
			WebElement roleElement = roleWebElement.findElement(By.tagName("div"));

			String attribute1 = roleElement.findElement(By.tagName("input")).getAttribute("id");
			String ulList1 = roleElement.findElement(By.tagName("input")).getAttribute("aria-labelledby");

			clickElement(SearchStringByListAndId("kranthiUpdated", replaceString(ulList1, "chosen", "results"), concatString(attribute1, "_search")), "id", 0);
			
		//	clickElement(SearchStringByListAndId("kranthiUpdated","select2-results-3","s2id_autogen3_search"),"id",0);
			clearTheInput("project.projPerson1Phone",0);
			enterTheInput("project.projPerson1Phone","098-765-4321",1000);
			clearTheInput("project.projPerson1Cell",0);
			enterTheInput("project.projPerson1Cell","0123456789",1000);
			clearTheInput("project.projPerson1Email",0);
			enterTheInput("project.projPerson1Email","sunnetqa1Updated@sunnet.com",1000);
			clickElement("project.projPerson1Notify","id",2000);
			
			
			clickElement("s2id_project.projPerson2","id",2000);
			
			
			 tdElementsByTableId = getStringIdByTable("responsiblePersonnel",1);
				
				roleWebElement = tdElementsByTableId.get(1);
				roleElement = roleWebElement.findElement(By.tagName("div"));

				 attribute1 = roleElement.findElement(By.tagName("input")).getAttribute("id");
				 ulList1 = roleElement.findElement(By.tagName("input")).getAttribute("aria-labelledby");

			clickElement(SearchStringByListAndId("sunilUpdated", replaceString(ulList1, "chosen", "results"), concatString(attribute1, "_search")), "id", 0);
			
		//	clickElement(SearchStringByListAndId("sunilUpdated","select2-results-4","s2id_autogen4_search"),"id",0);
			clearTheInput("project.projPerson2Phone",0);
			enterTheInput("project.projPerson2Phone","098-765-4321",1000);
			clearTheInput("project.projPerson2Cell",0);
			enterTheInput("project.projPerson2Cell","0123456789",1000);
			clearTheInput("project.projPerson2Email",0);
			enterTheInput("project.projPerson2Email","sunnetqa2Updated@sunnet.com",1000);
			clickElement("project.projPerson2Notify","id",2000);
			
		} catch (Exception e) {
		     e.printStackTrace();
		}
		
		
	}
	
	
	
	private void staticAutoConent() {
		
		try {
			
			sleepTime(2000);
			clickElement("Project Entry","linktext",0);

			clickElement("s2id_project.projPerson1","id",1000);
			
			List<WebElement> dropdownList = getDropdownList("select2-results-1");
			
			clickElement(SearchStringByListAndId(dropdownList.get(getRandomNumber(dropdownList.size())).getText(),"select2-results-1","s2id_autogen1_search"),"id",0);
			sleepTime(1000);
			clickElement("project.projPerson1Notify","id",1000);
            clickElement("s2id_project.projPerson2","id",1000);
            
            List<WebElement> dropdownList2 = getDropdownList("select2-results-2");
			
			clickElement(SearchStringByListAndId(dropdownList2.get(getRandomNumber(dropdownList2.size())).getText(),"select2-results-2","s2id_autogen2_search"),"id",0);
			sleepTime(1000);
			clickElement("project.projPerson2Notify","id",1000);
		} catch (Exception e) {
             e.printStackTrace();
		}
		
	}
	
	
	
	private List<WebElement> getDropdownList(String id) {
		
		WebElement ulElement = driver.findElement(By.id(id));
		
		return ulElement.findElements(By.tagName("li"));
	}

	public void personalInfoDele() {
		for(int m=0;m<lstRoleData.size();m++) {
		driver.findElement(By.xpath("//*[@id='deletePersonnel']")).click();
	}}
	/**
	 * Reading Manual Data from the Csv to input
	 * @param listRoleData
	 * @return
	 */
	
	private Map<Integer, List<String>> getMapElementsofPersonnelInfo(List<String> listRoleData) {
		Map<Integer, List<String>> MapPersonalInfoData = new HashMap<Integer, List<String>>();
		int personalDataKey = 0;
		for(int i=0;i<listRoleData.size();) {
			
			MapPersonalInfoData.put(personalDataKey,Arrays.asList(listRoleData.get(i),listRoleData.get(i+1),
					listRoleData.get(i+2),listRoleData.get(i+3),listRoleData.get(i+4)));
			
			i = i+5;
			personalDataKey++;
			
		}
		
		return MapPersonalInfoData;
		
	}


	/**
	 * 
	 * @param Id
	 * @return
	 */
	private List<WebElement> getStringIdByTable(String Id,int posisition) {
		
		WebElement tableElement = driver.findElement(By.id(Id));
		
		WebElement tbodyElement = tableElement.findElement(By.tagName("tbody"));
		
		List<WebElement> trElements = tbodyElement.findElements(By.tagName("tr"));
		
		if(posisition == -1 ) {
			posisition = trElements.size() - 1;
		}
		
		WebElement trElement = trElements.get(posisition);
		
		List<WebElement> tdElements = trElement.findElements(By.tagName("td"));
		
		return tdElements;
		
		
	}
	
	
	
	
	/**
	 * Method for Searching the String and return the id of the string in Personnel Info Section
	 * @param searchString
	 * @return String Id
	 */
	
	private String SearchStringByListAndId(String searchString,String ulStringId,String SearchBoxId) {
		try {
			WebElement searchBoxElement = driver.findElement(By.id(SearchBoxId));
			searchBoxElement.sendKeys(searchString);
			sleepTime(2000);
			WebElement Dropdownul_items = driver.findElement(By.id(ulStringId));
			WebElement Dropdownli_items = Dropdownul_items.findElement(By.tagName("li"));
			WebElement SearchItem_element = Dropdownli_items.findElement(By.tagName("div"));
			
			return SearchItem_element.getAttribute("id");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
		
	/**
	 * Generic Method for clearing the Input Data
	 * @param id
	 * @throws InterruptedException 
	 */
	private void clearTheInput(String id,long sleep) throws InterruptedException {
		driver.findElement(By.id(id)).clear();
		sleepTime(sleep);
	}
	
	
	/**
	 * Generic Method for entering the Input Data
	 * @param id
	 * @param inputString
	 * @throws InterruptedException 
	 */
	private void enterTheInput(String id,String inputString,long sleep) throws InterruptedException {
		driver.findElement(By.id(id)).sendKeys(inputString);
		sleepTime(sleep);
	}
	
		
	/**
	 * Generic Method for Clicking the Element
	 * @param id
	 * @throws InterruptedException 
	 */
	private void clickElement(String typeString,String type,long sleep) throws InterruptedException {
		switch (type) {
		case "id":
			driver.findElement(By.id(typeString)).click();
			break;
        case "linktext":
        	driver.findElement(By.linkText(typeString)).click();
			break;
		}
		sleepTime(sleep);
	}
	
	
	/**
	 * Generic Method for WaitingTime
	 * @param timeInMilliSecs
	 * @throws InterruptedException
	 */
	private void sleepTime(long timeInMilliSecs) throws InterruptedException {
		Thread.sleep(timeInMilliSecs);
	}
	
	
	/**
	 * Concat the resultant Strings to a relevant String
	 * @param source
	 * @param destination
	 * @return String
	 */
	private String concatString(String source,String destination) {
		return source+destination;
	}
	
	
	/**
	 * Replacing the String 
	 * @param source
	 * @param existingString
	 * @param changeString
	 * @return ChangedString
	 */
	private String replaceString(String source,String existingString,String changeString) {
		return source.replaceAll(existingString, changeString);
	}
	
	
	private int getRandomNumber(int size) {
		return rand.nextInt(size);
	}
}

		

