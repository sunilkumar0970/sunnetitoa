package com.sunnet.Pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openqa.selenium.support.ui.Select;
//import org.bson.codecs.CollectibleCodec;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.interactions.ClickAction;

//import com.google.common.base.Throwables;
import com.sunnet.GenericLib.DatabaseFunction;
import com.sunnet.GenericLib.Extent_Reports;
import com.sunnet.GenericLib.GenericFunctions;
import com.sunnet.GenericLib.Synchronization;
import com.sunnet.GenericLib.Utilities;
import com.sunnet.utils.ProjectUtily;

public class PGFiltersSection {
//	private static final String Created = null;
	// =========================================Variables=================================================================================
		DatabaseFunction db = new DatabaseFunction();
		GenericFunctions GFObj=new GenericFunctions();
		private WebDriver driver;
		List<WebElement> elements,startOrEndDaterowcount,rowcount,exportItems,checkboxes;
		public List<String> lstObject, lstTestData,startDate,endDate;
		Set<String> allstatus,handles,test;

		WebElement objUserName,objPassWord,objLoginButton,objProfileElement,projList,logo,filterStartDate,filterEndDate,endDateLabel,table,datetype,range,startOrEnd,startOrEndDateTable,projectStauts,statusItems,
		textinColumn,exportListBox,exportListItems,showHideColumns,tableColumns,clearFilter,projstaitems;
		String statusText,statusRows,status,subWindowHandler,currentwindow;
		//List<WebElement> startOrEndDaterowcount,rowcount;
		//List<String>startDate,endDate;
	   // String expurl="https://sunnetqa.anblicks.com/itoa/login.htmlx";
	    
		// =============================Constructor to initialize all the Page Objects==========================================================
		public PGFiltersSection(WebDriver driver) 

		{
			try 
			{
				this.driver = driver;
				lstObject = db.getTestDataObject("Select * from projectList", "ObjectRepository");
				lstTestData = db.getTestDataObject("Select * from ProjectTypeFilter", "Input");
			} 
			catch (Exception exc) 
			{

				System.out.println("Exception in Constructor."+exc.getMessage());

			}
		}	
		

		// ================================BUSINESS VALIDATION LOGIC================================================= 


		public void datetypdDb() throws ClassNotFoundException, SQLException {
		
				try { 
					 System.out.println("12------------started executig the script in list page");
					 Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
					//step1 load the driver class  
					Class.forName("oracle.jdbc.driver.OracleDriver");  
					  
					//step2 create  the connection object  
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.101.248:1525/pdborcl.mdc.anblicks.com","toa_trunk","toa_trunk1");  
					  
			//		String allInRange =  "select count(*) from toa_projects where end_date >= TO_DATE('2019-07-10', 'YYYY-MM-DD') AND start_date <= TO_DATE('2019-07-29', 'YYYY-MM-DD')";
			//		String startOrEnd = "select count(*) from toa_projects where (start_date >= TO_DATE('2019-07-10', 'YYYY-MM-DD') AND start_date <= TO_DATE('2019-07-29', 'YYYY-MM-DD') ) OR (end_date >= TO_DATE('2019-07-10', 'YYYY-MM-DD') AND  end_date <= TO_DATE('2019-07-29', 'YYYY-MM-DD') )";
			//		String lastUpdated = "select count(*) from toa_projects where (last_modified_date >=  TO_DATE('2019-07-10', 'YYYY-MM-DD') AND last_modified_date <= TO_DATE('2019-08-29', 'YYYY-MM-DD') )";
					Statement stmt = con.createStatement();		
					
					  int allInRangeCount = 0;
					  int startOrEndCount = 0;
					  int lastUpdatedcount=0;
					  
					// Execute the SQL Query. Store results in ResultSet		
					/*	ResultSet resa= stmt.executeQuery(allInRange);
						 while (resa.next()){
							   allInRangeCount = resa.getInt(1);
			                }
						 System.out.println("Number of rows all in range:"+allInRangeCount);
						// Execute the SQL Query. Store results in ResultSet	
						ResultSet ress= stmt.executeQuery(startOrEnd);
						 while (ress.next()){
							   startOrEndCount = ress.getInt(1);
			                }
						 System.out.println("Number of rows startOrEndCount:"+startOrEndCount);
						// Execute the SQL Query. Store results in ResultSet	
						ResultSet resl=stmt.executeQuery(lastUpdated);
						 while (resl.next()){
							   lastUpdatedcount = resl.getInt(1);
			                }
						 System.out.println("Number of rows lastUpdatedcount:"+lastUpdatedcount);*/
						// While Loop to iterate through all data and print results	
						 driver.findElement(By.partialLinkText("Projects Lis")).click();;
						 driver.findElement(By.xpath("//*[@id='collapseOne']/div/div/div[1]/div[4]/button")).click();
						 Thread.sleep(3000);
						 WebElement ulitems=driver.findElement(By.xpath("//*[@id='collapseOne']/div/div/div[1]/div[4]/div/ul"));
						 List<WebElement>licount=ulitems.findElements(By.tagName("li"));
						System.out.println(licount.size()+" li counts");
						for(int i=0;i<licount.size();i++) {
							licount.get(i).click();
							table = Utilities.returnElement(driver, mapObjProjectData.get("Table").get(0),mapObjProjectData.get("Table").get(1));
							
							rowcount = table.findElements(By.tagName("tr"));	
						if(licount.get(i).getText().equalsIgnoreCase("All in Range")) {
					String allInRange =  "select count(*) from toa_projects where end_date >= TO_DATE('2019-07-10', 'YYYY-MM-DD') AND start_date <= TO_DATE('2019-07-29', 'YYYY-MM-DD')";
					ResultSet resa= stmt.executeQuery(allInRange);
					 while (resa.next()){
						   allInRangeCount = resa.getInt(1);
		                }
					 System.out.println("Number of rows all in range:"+allInRangeCount);		
					
					
						}
						else if(licount.get(i).getText().equalsIgnoreCase("Start or End Only")) {
							String startOrEnd =  "select count(*) from toa_projects where end_date >= TO_DATE('2019-07-10', 'YYYY-MM-DD') AND start_date <= TO_DATE('2019-07-29', 'YYYY-MM-DD')";
							ResultSet ress= stmt.executeQuery(startOrEnd);
							 while (ress.next()){
								 startOrEndCount = ress.getInt(1);
				                }
							 System.out.println("Number of rows all in range:"+startOrEndCount);		
							
							
								}
						
						else if(licount.get(i).getText().equalsIgnoreCase("Last Updated")) {
							String lastUpdated =  "select count(*) from toa_projects where end_date >= TO_DATE('2019-07-10', 'YYYY-MM-DD') AND start_date <= TO_DATE('2019-07-29', 'YYYY-MM-DD')";
							ResultSet resl= stmt.executeQuery(lastUpdated);
							 while (resl.next()){
								 lastUpdatedcount = resl.getInt(1);
				                }
							 System.out.println("Number of rows all in range:"+lastUpdatedcount);		
							
							
								}
						}
						 
				
						 
						 
						 
						 
					/*	   while (resa.next()){
							   allInRangeCount = resa.getInt(1);
			                }
						   while (ress.next()){
							   startOrEndCount = ress.getInt(1);
			                }
						   while (resl.next()){
							   lastUpdatedcount = resl.getInt(1);
			                }
			                System.out.println("Number of rows all in range:"+allInRangeCount);
			                System.out.println("Number of rows startOrEndCount:"+startOrEndCount);
			                System.out.println("Number of rows lastUpdatedcount:"+lastUpdatedcount);*/
 						con.close();
				 }
				catch(Exception e) {
					 System.out.println(e);
					 }
				 }
		
		
		
		public  void Datetype() throws Exception
		{
			try
			{ 
				Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
				projList = Utilities.returnElement(driver, mapObjProjectData.get("ProjectList").get(0),
						mapObjProjectData.get("ProjectList").get(1));
				projList.click();
				Thread.sleep(3000);
				filterStartDate = Utilities.returnElement(driver, mapObjProjectData.get("FilterStartDate").get(0),
						mapObjProjectData.get("FilterStartDate").get(1));
				filterStartDate.clear();
				Thread.sleep(2000);
				filterStartDate.sendKeys("06/15/2019");
				Thread.sleep(3000);
				filterEndDate=Utilities.returnElement(driver, mapObjProjectData.get("FilterEndDate").get(0),
						mapObjProjectData.get("FilterEndDate").get(1));
				filterEndDate.clear();
				Thread.sleep(2000);
				filterEndDate.sendKeys("06/25/2019");
				Thread.sleep(2000);
				endDateLabel = Utilities.returnElement(driver, mapObjProjectData.get("Enddatelabel").get(0),
						mapObjProjectData.get("Enddatelabel").get(1));
				endDateLabel.click();
				filterEndDate.sendKeys(Keys.ENTER);
				Thread.sleep(2000);
				System.out.println("1 line");
				
				table = Utilities.returnElement(driver, mapObjProjectData.get("Table").get(0),
						mapObjProjectData.get("Table").get(1));
				
				rowcount = table.findElements(By.tagName("tr"));			  
				System.out.println("4th line");
			    System.out.println(rowcount.size());
			    Thread.sleep(2000);
			  
			    startDate = new ArrayList<String> ();
			     endDate = new ArrayList<String> ();
				for(int i=1;i<=rowcount.size()-2;i++) {
					
				    WebElement startColumn=driver.findElement(By.xpath("//*[@id='projectList']/tbody/tr["+(i)+"]/td[5]"));
                      String start=startColumn.getText(); 
                      String[] startd=start.split(" ");
                     String startlist=startd[0];
                      startDate.add(startlist);
                      WebElement endColumn=driver.findElement(By.xpath("//*[@id='projectList']/tbody/tr["+(i)+"]/td[6]")); 
                    String end= endColumn.getText(); 
                    String[] endd=end.split(" ");
                   String endlist=endd[0];
                      endDate.add(endlist);
                      //Thread.sleep(1000);
				}
					
				System.out.println(startDate);
		
			    if(compareStartDate(startDate,"06/15/2019") && compareEndDate(endDate,"06/25/2019")){
					Extent_Reports.executionLog("Pass","Date Range is working.",driver);

			    	System.out.println("------>>>>>>>>>>>>>>>  Your test case sucess");
			    	
			    } else {
			    	Extent_Reports.executionLog("Fail","Date Range is not working.",driver);
			    	System.out.println("------>>>>>>>>>>>>>>>  Your test case fail");}
			    
			   datetype=Utilities.returnElement(driver, mapObjProjectData.get("datetype").get(0),
						mapObjProjectData.get("datetype").get(1));  
					  datetype.click(); 			 
			   range=Utilities.returnElement(driver, mapObjProjectData.get("Range").get(0),
						mapObjProjectData.get("Range").get(1)); 
             range.click();
             startOrEnd=Utilities.returnElement(driver, mapObjProjectData.get("StartOREND").get(0),
						mapObjProjectData.get("StartOREND").get(1));
             startOrEnd.click();
         
             Synchronization.implicitWait(driver, 05);
            startOrEndDateTable=Utilities.returnElement(driver, mapObjProjectData.get("Table").get(0),
					mapObjProjectData.get("Table").get(1));
            
 			 startOrEndDaterowcount = startOrEndDateTable.findElements(By.tagName("tr"));
             if(compareStartDate(startDate,"06/15/2019") || compareEndDate(endDate,"06/25/2019")){
					Extent_Reports.executionLog("Pass","Start OR End Date Range is working.",driver);
	
            	 System.out.println("------>>>>>>>>>>>>>>>  Your start or end test case sucess");
			    	
			    } else {
			    	Extent_Reports.executionLog("Fail","Start OR End Date is not working.",driver);
			    	System.out.println("------>>>>>>>>>>>>>>>  Your start or end test case fail");
			    	}
			    Thread.sleep(3000);
			    
			}
			catch(Throwable ex)
			{
				Extent_Reports.executionLog("FAIL","Date type filter is not working",driver);
				System.out.println(ex.getMessage());
				
			}
			
			
		}
		

		public void projectStatus() throws Exception {
			try{Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
			//driver.findElement(By.linkText("Clear Filters")).click();
			allstatus = new HashSet<String>();
			projList = Utilities.returnElement(driver, mapObjProjectData.get("ProjectList").get(0),
					mapObjProjectData.get("ProjectList").get(1));
			projList.click();
			clearFilter=Utilities.returnElement(driver, mapObjProjectData.get("clrarfilter").get(0),
					mapObjProjectData.get("clrarfilter").get(1));
			clearFilter.click();
			Thread.sleep(2000);
			filterStartDate = Utilities.returnElement(driver, mapObjProjectData.get("FilterStartDate").get(0),mapObjProjectData.get("FilterStartDate").get(1));
			filterStartDate.clear();
			Thread.sleep(2000);
			filterStartDate.sendKeys("06/15/2019");
			Thread.sleep(3000);
			filterEndDate=Utilities.returnElement(driver, mapObjProjectData.get("FilterEndDate").get(0),
					mapObjProjectData.get("FilterEndDate").get(1));
			filterEndDate.clear();
			Thread.sleep(2000);
			filterEndDate.sendKeys("06/25/2019"); 
			projectStauts=Utilities.returnElement(driver, mapObjProjectData.get("ProjectStatus").get(0),
					mapObjProjectData.get("ProjectStatus").get(1));
			projectStauts.click();
			projstaitems=Utilities.returnElement(driver, mapObjProjectData.get("statusul").get(0),
					mapObjProjectData.get("statusul").get(1));
			List<WebElement>statusli=projstaitems.findElements(By.tagName("li"));
			System.out.println(statusli);
			for(int s=1;s<=statusli.size();s++) {
				String listatus=statusli.get(s).getText();
				Thread.sleep(3000);
				statusli.get(s).click();
				System.out.println(listatus);
				table = Utilities.returnElement(driver, mapObjProjectData.get("Table").get(0),
						mapObjProjectData.get("Table").get(1));
				rowcount = table.findElements(By.tagName("tr"));
				statusRows=rowcount.toString();
//				allstatus = new HashSet<String>();
				System.out.println(rowcount.size()+"number of rows");
				
				for(int i=1;i<=rowcount.size()-2;i++)
			    {
				 textinColumn=driver.findElement(By.xpath("//*[@id='projectList']/tbody/tr["+(i)+"]/td[7]"));
				 status=textinColumn.getText();
			allstatus.add(status);
			}
				statusli.get(s).click();
				System.out.println(allstatus.toString()+"Set data");
			}
			Thread.sleep(2000);
/*			statusItems=Utilities.returnElement(driver, mapObjProjectData.get("StatusItems").get(0),
					mapObjProjectData.get("StatusItems").get(1));
			statusItems.click();
			Thread.sleep(2000);

			statusText=Utilities.returnElement(driver, mapObjProjectData.get("StatusItems").get(0),
					mapObjProjectData.get("StatusItems").get(1)).getText();
			Thread.sleep(3000);
			 table = Utilities.returnElement(driver, mapObjProjectData.get("Table").get(0),
					mapObjProjectData.get("Table").get(1));
			rowcount = table.findElements(By.tagName("tr"));
			statusRows=rowcount.toString();
			allstatus = new HashSet<String>();
			for(int i=1;i<=rowcount.size()-2;i++)
		    {
			 textinColumn=driver.findElement(By.xpath("//*[@id='projectList']/tbody/tr["+(i)+"]/td[7]"));
			 status=textinColumn.getText();
		allstatus.add(status);
		}
			System.out.println(allstatus+"statuss");
			if (!allstatus.toString().contains(statusText)) {
				
				Extent_Reports.executionLog("Fail", Extent_Reports.logExpected + "Project Status filter is not working", driver);
			}
			else {
				Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "Project Status filter is working", driver);
			}
			driver.navigate().refresh();
			Thread.sleep(3000);*/
			}
			
			catch(Throwable ex)
			{
				Extent_Reports.executionLog("FAIL","Date type filter is not working",driver);
				System.out.println(ex.getMessage());
				
			}
			}
			
		
		public void projectType() throws InterruptedException {
			Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
			
			System.out.println(lstTestData.size());
			for(String str : lstTestData) {
			projList = Utilities.returnElement(driver, mapObjProjectData.get("ProjectList").get(0),
					mapObjProjectData.get("ProjectList").get(1));
			projList.click();
			Thread.sleep(3000);
			driver.findElement(By.id("filterStartDate")).clear();
			Thread.sleep(2000);
			driver.findElement(By.id("filterStartDate")).sendKeys("01/15/2019");
			Thread.sleep(3000);
			driver.findElement(By.id("filterEndDate")).clear();
			Thread.sleep(2000);
			driver.findElement(By.id("filterEndDate")).sendKeys("06/25/2019");
			Thread.sleep(2000);
			
			List<String> listByString2 = ProjectUtily.getListByString(str);
			System.out.println(listByString2.size());
			for(String ele : listByString2) {
				WebElement findElement3 = driver.findElement(By.xpath("//*[@id=\"datatableFilterWrapper1\"]/div/span/h2"));
				WebElement findElement2 = driver.findElement(By.xpath(mapObjProjectData.get("projTypeDropdown").get(1)));
				WebElement findElement = driver.findElement(By.xpath(mapObjProjectData.get(ele).get(1)));
				findElement2.click();
				findElement.click();
				findElement3.click();
				System.out.println(mapObjProjectData.get(ele).get(1));
			}
			
				WebElement table = driver.findElement(By.xpath("//*[@id=\"projectList\"]"));
				String statusFilterElement = driver.findElement(By.xpath("//*[@id=\"collapseOne\"]/div/div/div[3]/button")).getText();
				System.out.println(statusFilterElement);
				List<String> listByString = ProjectUtily.getListByString(statusFilterElement);

				List<WebElement> rowcount = table.findElements(By.tagName("tr"));
				Set<String> projectTypeSet = new HashSet<String>();
				boolean testStatus = true;
				System.out.println("test");
				System.out.println(rowcount.size());
				for(int i=1;i<=rowcount.size()-2;i++)
			    {
					
					System.out.println(i);	
				WebElement TextinColumn=driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr["+(i)+"]/td[6]"));
				String pType=TextinColumn.getText();
				System.out.println(pType);
				if(!pType.equals("")) {
				projectTypeSet.add(pType);
				}
				System.out.println("out of loop");
				}
				System.out.println(projectTypeSet);
				
				
				List<String> projectTypelist = new ArrayList<String>(projectTypeSet);
				
				
				listByString.sort( Comparator.comparing( String::toString ) );
				projectTypelist.sort( Comparator.comparing( String::toString ) );
				
				
				if(listByString.contains("All Types")) {
				for(String s : projectTypelist) {
					if(!s.equalsIgnoreCase("Customer Project") || !s.equalsIgnoreCase("Commercial Project") || 
							!s.equalsIgnoreCase("Capital Project") || !s.equalsIgnoreCase("Informational")) {
						
						testStatus = false;
						break;
					}

				} }
				else {
					
					if(!listByString.equals(projectTypelist)) {
						testStatus = false;
					}
					
				}
				
				System.out.println(testStatus);
				
				logo = Utilities.returnElement(driver, mapObjProjectData.get("Logo").get(0),
						mapObjProjectData.get("Logo").get(1));
				logo.click();
				Thread.sleep(2000);
				
				
			//	if(pType.equalsIgnoreCase("Customer Project")||pType.equalsIgnoreCase("Commercial Project")||pType.equalsIgnoreCase("Capital Project")||pType.equalsIgnoreCase("Informational")||pType.equalsIgnoreCase("")) {
//				System.out.println("pass");	
//				}
			//	else {
//					System.out.println("fail");
//				}
				/*driver.findElement(By.xpath("//*[@id=\"collapseOne\"]/div/div/div[3]/button")).click();
				driver.findElement(By.xpath("//*[@id=\"collapseOne\"]/div/div/div[3]/div/ul/li[2]/a")).click();
				if(pType.equalsIgnoreCase("Customer Project")) {
					System.out.println(" customer project filter pass");	
					}
					else {
						System.out.println("fail");
					}*/
				
				
				
			    }
			
		}

		public void projectNewType() {

			Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
			List<String> listUnClickString = new ArrayList<String>();
			try {

				for (String str : lstTestData) {
					
					projList = Utilities.returnElement(driver, mapObjProjectData.get("ProjectList").get(0),
							mapObjProjectData.get("ProjectList").get(1));
					projList.click();

					driver.findElement(By.id("filterStartDate")).clear();
					Thread.sleep(2000);
					driver.findElement(By.id("filterStartDate")).sendKeys("06/01/2019");
					Thread.sleep(2000);
					driver.findElement(By.id("filterEndDate")).clear();
					Thread.sleep(2000);
					driver.findElement(By.id("filterEndDate")).sendKeys("06/25/2019");
					Thread.sleep(4000);

					List<String> listByString2 = ProjectUtily.getListByString(str);
					System.out.println("check the line1"+listByString2.size());
					for (String ele : listByString2) {
						WebElement findElement3 = driver.findElement(By.xpath("//*[@id='datatableFilterWrapper1']/div/span/h2"));
						WebElement findElement2 = Utilities.returnElement(driver, mapObjProjectData.get("projTypeDropdown").get(0),mapObjProjectData.get("projTypeDropdown").get(1));
						findElement2.click();
						for (String ele1 : listUnClickString) {
							System.out.println(mapObjProjectData.get(ele1).get(1));
							driver.findElement(By.xpath(mapObjProjectData.get(ele1).get(1))).click();
						}
						WebElement findElement = driver.findElement(By.xpath(mapObjProjectData.get(ele).get(1)));
						findElement.click();
						findElement3.click();
						System.out.println(mapObjProjectData.get(ele).get(1));
					}

					listUnClickString.clear();
					listUnClickString = new ArrayList<String>(listByString2);

					String statusFilterElement = driver.findElement(By.xpath("//*[@id='collapseOne']/div/div/div[3]/button")).getText();
					List<String> listByString = ProjectUtily.getListByString(statusFilterElement);

					Thread.sleep(3500);

					WebElement table = driver.findElement(By.xpath("//*[@id=\"projectList\"]"));
					List<WebElement> rowcount = table.findElements(By.tagName("tr"));
					Set<String> projectTypeSet = new HashSet<String>();
					boolean testStatus = true;
					System.out.println("test");
					System.out.println("size : " + rowcount.size());
					for (int i = 1; i <= rowcount.size() - 2; i++) {
						System.out.println(i);
						WebElement TextinColumn = driver.findElement(By.xpath("//*[@id='projectList']/tbody/tr["+i+"]/td[4]/div"));
								//(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[" + (i)+ "]/td[6]"));
						String pType = TextinColumn.getText();
						System.out.println(pType);
						if (!pType.equals("")) {
							projectTypeSet.add(pType.trim());
						}
						System.out.println("out of loop");
					}
					System.out.println("List : " + projectTypeSet);

					/*
					 * List<String> projectTypelist = new ArrayList<String>(projectTypeSet);
					 * 
					 * listByString.sort(Comparator.comparing(String::toString));
					 * projectTypelist.sort(Comparator.comparing(String::toString));
					 */
					
					 listByString.sort(Comparator.comparing(String::toString));

					System.out.println("List1 : " + listByString);
					System.out.println("List1 : " + listByString.size());
					System.out.println("List2 : " + projectTypeSet);
					System.out.println("List2 : " + projectTypeSet.size());

					if (listByString.contains("All Types")) {
						for (String s : projectTypeSet) {
							if (!s.equalsIgnoreCase("Customer Project") || !s.equalsIgnoreCase("Commercial Project")|| !s.equalsIgnoreCase("Capital Project") || !s.equalsIgnoreCase("Informational")) 
							{

								testStatus = false;
								break;
							}

						}
					} else {
						
						System.out.println("Came String !!");
						
						for(String s : projectTypeSet) {
							
							if(!listByString.contains(s.trim())) {
								
								testStatus = false;
								break;
							}
						}

					}

					System.out.println(testStatus);

					logo = Utilities.returnElement(driver, mapObjProjectData.get("Logo").get(0),mapObjProjectData.get("Logo").get(1));
					logo.click();
					Thread.sleep(3000);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		
		
		
		
				// =======================================Assigning the values to Web Elements=======================================		


				public void exportList() throws Exception {
					Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
					subWindowHandler = null;
					exportListBox = Utilities.returnElement(driver, mapObjProjectData.get("ExportlistBox").get(0),
							mapObjProjectData.get("ExportlistBox").get(1));	
					exportListBox.click();
					Thread.sleep(2000);		
					exportListItems=Utilities.returnElement(driver, mapObjProjectData.get("ExportListItems").get(0),
							mapObjProjectData.get("ExportListItems").get(1));
				exportItems=exportListItems.findElements(By.tagName("li"));
				for(int j=2;j<exportItems.size();j++) {
					exportItems.get(j).click();
					  handles = driver.getWindowHandles(); 
				        Iterator<String> iterator = handles.iterator();
				        while (iterator.hasNext()){
				       subWindowHandler = iterator.next();
				        }
				        driver.switchTo().window(subWindowHandler);
				        Thread.sleep(3000);    
				        Robot robot = new Robot();
				        Thread.sleep(2000);    
				        robot.keyPress(KeyEvent.VK_DOWN);
				        Thread.sleep(1000);
				        robot.keyPress(KeyEvent.VK_ENTER);
				        Thread.sleep(1000);

				        exportListBox.click();
				}
					for(int i=0;i<exportItems.size()-2;i++) {
						exportItems.get(i).click();
					Thread.sleep(3000);
					currentwindow=driver.getWindowHandle();
					test= driver.getWindowHandles();
				for(String list:test) {
					if(!list.contains(currentwindow)) {
						driver.switchTo().window(list);
						String test1=driver.getCurrentUrl();
				       test1.contains("Print");
				 Extent_Reports.executionLog("PASS", Extent_Reports.logExpected + "opened the print page in browser ", driver);
				 driver.close();
					driver.switchTo().window(currentwindow);				
					exportListBox.click();			
					}
					else {
						
						
					}
					
				}
					}
					
					}

					 public void showhideColumns() throws Exception{
					 
			try {	 Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
				 projList = Utilities.returnElement(driver, mapObjProjectData.get("ProjectList").get(0),mapObjProjectData.get("ProjectList").get(1));
							 projList.click(); 
					 clearFilter=Utilities.returnElement(driver, mapObjProjectData.get("clrarfilter").get(0),
								mapObjProjectData.get("clrarfilter").get(1));
						clearFilter.click();
					    showHideColumns=Utilities.returnElement(driver, mapObjProjectData.get("showhidecol").get(0),
								mapObjProjectData.get("showhidecol").get(1));
					    showHideColumns.click(); 
					     tableColumns=Utilities.returnElement(driver, mapObjProjectData.get("allcolmns").get(0),
								mapObjProjectData.get("allcolmns").get(1));
					  
					    checkboxes=tableColumns.findElements(By.tagName("Button"));
					 
		       for(int j=0;j<=checkboxes.size();j++) { 
		    	   showHideColumns.click(); 
			     String checkname=checkboxes.get(j).getText(); 
			     System.out.println(checkname);
		        checkboxes.get(j).click(); 
		        System.out.println("line1");
		        Thread.sleep(1000);
				Extent_Reports.executionLog("Pass","show/Hide columns is working",driver);
				driver.findElement(By.xpath("//*[@id='datatableFilterWrapper1']/div/span/h2")).click();
				System.out.println("line2");
				Thread.sleep(1000);
				 driver.findElement(By.xpath("//*[@id='projectList']/tbody/tr[1]/td[1]/span")).click();
				 System.out.println("line3");
				 Thread.sleep(1000);
				String info=driver.findElement(By.xpath("//*[@id='projectList']/tbody/tr[2]/td")).getText();
				if(info.contains(checkname)) {
					Extent_Reports.executionLog("PASS", "show/hide filter and info icon is working correctly", driver);
				}
				else if(checkname.contains("Project ID")||checkname.contains("Show All")){
					Extent_Reports.executionLog("PASS", "show/hide filter and info icon is working correctly", driver);
				}
				else {
					Extent_Reports.executionLog("Fail", "show/hide filter and info icon is not working correctly", driver);
				} }	 
				 }
			catch (Exception e) {
			System.out.println(e);	
			}	} 
					 
					 
		public void pEntryList() throws Exception {
			Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			projList = Utilities.returnElement(driver, mapObjProjectData.get("ProjectList").get(0),
					mapObjProjectData.get("ProjectList").get(1));
			projList.click();
			Thread.sleep(3000);
			filterStartDate = Utilities.returnElement(driver, mapObjProjectData.get("FilterStartDate").get(0),
					mapObjProjectData.get("FilterStartDate").get(1));
			filterStartDate.clear();
			Thread.sleep(2000);
			filterStartDate.sendKeys("06/15/2019");
			Thread.sleep(3000);
			filterEndDate=Utilities.returnElement(driver, mapObjProjectData.get("FilterEndDate").get(0),
					mapObjProjectData.get("FilterEndDate").get(1));
			filterEndDate.clear();
			Thread.sleep(2000);
			filterEndDate.sendKeys("06/30/2019"); 
			Select se=new Select(driver.findElement(By.name("projectList_length")));
			List<WebElement>items=se.getOptions();
			for(int i=0;i<items.size();i++)
			{
			 String listvalue=	items.get(i).getText();
			
			 int projectentries = Integer.parseInt(listvalue);
				se.selectByIndex(i);
				Thread.sleep(3000);
				WebElement table = driver.findElement(By.xpath("//*[@id=\"projectList\"]"));
				List<WebElement> rowcount = table.findElements(By.tagName("tr"));
			
				int tablerows=rowcount.size()-2;
				
				if(tablerows<=projectentries) {
					jse.executeScript("window.scrollBy(0,550)","");
					Extent_Reports.executionLog("PASS", "Project entries in the list page is working as selected", driver);
				}
				else {
					Extent_Reports.executionLog("Fail", "Project entries in the list page is not working as selected", driver);
				}
				}
			}
		
		public void rowHeight() throws InterruptedException {
			Map<String, List<String>> mapObjProjectData = ProjectUtily.getMapObjProjectData(lstObject);
			clearFilter=Utilities.returnElement(driver, mapObjProjectData.get("clrarfilter").get(0),
					mapObjProjectData.get("clrarfilter").get(1));
			clearFilter.click();
		 driver.findElement(By.id("filterStartDate")).clear();
			
			driver.findElement(By.id("filterStartDate")).sendKeys("01/15/2019");
			
			driver.findElement(By.id("filterEndDate")).clear();
		
			driver.findElement(By.id("filterEndDate")).sendKeys("06/25/2019");
			
			driver.findElement(By.xpath("//div[@class='btn-group bootstrap-select snc-mobile-width-100 leftAlignText']//button[@class='btn dropdown-toggle selectpicker btn-default']")).click();
			Thread.sleep(2000);
			WebElement totalitems=driver.findElement(By.xpath("//div[@class='btn-group bootstrap-select snc-mobile-width-100 leftAlignText open']//ul[@class='dropdown-menu inner selectpicker']"));
			List<WebElement>ss=totalitems.findElements(By.tagName("li"));
		
			for(int i=0;i<ss.size();i++) {
				ss.get(i).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@class='btn-group bootstrap-select snc-mobile-width-100 leftAlignText']//button[@class='btn dropdown-toggle selectpicker btn-default']")).click();

			}
			
		}
		
	
	
	public static boolean compareStartDate(List<String>  startDateList ,String inputdate) {
		 boolean compareStartdate=false;
		 System.out.println("start date list"+startDateList);
		int totalcount=startDateList.size();
		List<String> equalList=new ArrayList<String>();
		List<String> afterList=new ArrayList<String>();
		for(String val:startDateList ) {
			
			 SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		     String outputDateInString = val;
		     String intputDateInString = inputdate;

		     try {

		         Date outputdate = formatter.parse(outputDateInString);
		         Date inutdate = formatter.parse(intputDateInString);
		         if (inutdate.equals(outputdate) || outputdate.after(inutdate)) {
		             System.out.println("Date1 is equal Date2");
		             equalList.add("Date1 is equal Date2");
		             
		         }
		  
		       if(totalcount==equalList.size()+afterList.size()) compareStartdate=true;

		     } catch (ParseException e) {
		         e.printStackTrace();
		     }
		}
		return compareStartdate;
	}

	public static boolean compareEndDate(List<String>  startDateList ,String inputdate) {
		 boolean compareStartdate=false;
		 System.out.println("end date list"+startDateList);
		 int totalcount=startDateList.size();
		List<String> equalList=new ArrayList<String>();
		List<String> afterList=new ArrayList<String>();
		for(String val:startDateList ) {
			
			 SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		     String outputDateInString = val;
		     String intputDateInString = inputdate;

		     try {

		         Date outputdate = formatter.parse(outputDateInString);
		         Date inutdate = formatter.parse(intputDateInString);
		         if (inutdate.equals(outputdate) || outputdate.before(inutdate)) {
		             System.out.println("Date1 is equal Date2");
		             equalList.add("Date1 is equal Date2");
		             
		         }
		  
		       if(totalcount==equalList.size()+afterList.size()) compareStartdate=true;

		     } catch (ParseException e) {
		         e.printStackTrace();
		     }
		}
		return compareStartdate;
	}
}
