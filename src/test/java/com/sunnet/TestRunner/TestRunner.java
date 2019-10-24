package com.sunnet.TestRunner;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.TestNG;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.sunnet.GenericLib.DatabaseFunction;

public class TestRunner
{
	public Hashtable<String, String> htTestRunner;
	public List<String> lstBrowser,lstExecutionType,lstBrowserVersion,lstOS,lstOSVersion;
	public int poolSize=0,executionCnt=0;
	public String browsers;
	
	@Parameters(value={"aut"})
	@Test
   public void tRun(String aut)
	{
		
		DatabaseFunction objDBF= new DatabaseFunction();
		try
    	{
			switch (aut.toUpperCase()) 
			{
			     
				case "IPHONE":
				
						htTestRunner=objDBF.getHashTestData("Select * from TestSuite Where Execution ='Yes' AND ExecutionType='" +aut+"'","TestRunner");
						poolSize=2;
						executionCnt=htTestRunner.size();
						System.out.println("IPHONE:"+htTestRunner.size());
						break;
				case "IPAD":
						htTestRunner=objDBF.getHashTestData("Select * from TestSuite Where Execution ='Yes' AND ExecutionType='" +aut+"'","TestRunner");
						poolSize=2;
						executionCnt=htTestRunner.size();
						System.out.println("IPAD:"+htTestRunner.size());
						break;
				case "ANDROIDPHONE":
					htTestRunner=objDBF.getHashTestData("Select * from TestSuite Where Execution ='Yes' AND ExecutionType='" +aut+"'","TestRunner");
					poolSize=2;
					executionCnt=htTestRunner.size();
					System.out.println("ANDROIDPHONE:"+htTestRunner.size());
					break;
				case "ANDROIDTAB":
					htTestRunner=objDBF.getHashTestData("Select * from TestSuite Where Execution ='Yes' AND ExecutionType='" +aut+"'","TestRunner");
					poolSize=2;
					executionCnt=htTestRunner.size();
					System.out.println("ANDROIDTAB:"+htTestRunner.size());
					break;
				case "CHROME":
						lstExecutionType = objDBF.getTestDataObject("Select ExecutionType from TestSuite Where Execution ='Yes' and Browser='Chrome'","TestRunner");
						if(!lstExecutionType.isEmpty())
						{
							if(lstExecutionType.get(0).compareToIgnoreCase("Remote")== 0)
							{
								poolSize=2;
								htTestRunner=objDBF.getHashTestData("Select * from TestSuite Where Execution ='Yes' AND Browser='Chrome'","TestRunner");
								aut="REMOTE";
								executionCnt=htTestRunner.size();
								System.out.println("Chrome:"+htTestRunner.size());
							}
							else if(lstExecutionType.get(0).compareToIgnoreCase("Grid")== 0)
							{
								htTestRunner=objDBF.getHashTestData("Select * from TestSuite Where Execution ='Yes' AND Browser='Chrome'","TestRunner");
								aut="Grid";
								poolSize=6;
								executionCnt=htTestRunner.size();
								System.out.println("Chrome:"+htTestRunner.size());
						
							}
							else
							{
								htTestRunner=objDBF.getHashTestData("Select * from TestSuite Where Execution ='Yes' AND ExecutionType='" +aut+"'","TestRunner");
								poolSize=1;
								executionCnt=htTestRunner.size();
								System.out.println("Chrome:"+htTestRunner.size());
						
							}
						}
						
						break;
				case "FIREFOX":
						lstExecutionType = objDBF.getTestDataObject("Select Distinct(ExecutionType) from TestSuite Where Execution ='Yes' and Browser='Firefox'","TestRunner");
						if(lstExecutionType.get(0).compareToIgnoreCase("Remote")== 0)
						{
							System.out.println("**********************************");
							poolSize=2;
							htTestRunner=objDBF.getHashTestData("Select * from TestSuite Where Execution ='Yes' AND Browser='Firefox'","TestRunner");
							aut="REMOTE";
							executionCnt=htTestRunner.size();
							System.out.println("FF:"+htTestRunner.size());
						}
						else if(lstExecutionType.get(0).compareToIgnoreCase("Grid")== 0)
						{
							htTestRunner=objDBF.getHashTestData("Select * from TestSuite Where Execution ='Yes' AND Browser='Firefox'","TestRunner");
							aut="Grid";
							poolSize=6;
							executionCnt=htTestRunner.size();
							System.out.println("Firefox:"+htTestRunner.size());
					
						}
						else
						{
							htTestRunner=objDBF.getHashTestData("Select * from TestSuite Where Execution ='Yes' AND Browser='Firefox'","TestRunner");
							poolSize=1;
							executionCnt=htTestRunner.size();
							System.out.println("FF:"+htTestRunner.size());
						}
						break;
				case "IE":

					lstExecutionType = objDBF.getTestDataObject(
					"Select Distinct(ExecutionType) from TestSuite Where Execution ='Yes' and Browser='IE'","TestRunner");
					if (lstExecutionType.get(0).compareToIgnoreCase("Remote") == 0) {
					poolSize = 2;
					htTestRunner = objDBF.getHashTestData(
					"Select * from TestSuite Where Execution ='Yes' AND Browser='IE'", "TestRunner");
					aut = "REMOTE";
					executionCnt = htTestRunner.size();
					System.out.println("IE:" + htTestRunner.size());
					} else if (lstExecutionType.get(0).compareToIgnoreCase("Grid") == 0) {
					htTestRunner = objDBF.getHashTestData(
					"Select * from TestSuite Where Execution ='Yes' AND Browser='IE'", "TestRunner");
					aut = "Grid";
					poolSize = 6;
					executionCnt = htTestRunner.size();
					System.out.println("IE:" + htTestRunner.size());

					} else {
					htTestRunner = objDBF.getHashTestData(
					"Select * from TestSuite Where Execution ='Yes' AND Browser='IE'", "TestRunner");
					poolSize = 1;
					executionCnt = htTestRunner.size();
					System.out.println("IE:" + htTestRunner.size());
					}

					break;
					default:
					     System.out.println("Please Specify the Environment to Execute");
			}
		
		
    	}
		catch(Exception exc)
		{
			System.out.println("Exception in TestRunner:"+ exc.getMessage());
		}
			if (executionCnt>0)
			{
				/*if(htTestRunner.size()>0)		
				{*/
					TestNG myTestNG = new TestNG();  //Create an instance on TestNG
				
					XmlSuite mySuite = new XmlSuite(); //Create an instance of XML Suite and assign a name for it.
					mySuite.setName("SUNNET_IOTA_Automation");
					
					XmlTest myTest = new XmlTest(mySuite); //Create an instance of XmlTest and assign a name for it.
					myTest.setName("SUNNET_IOTA_Automation");			
					List<XmlClass> myClasses = new ArrayList<XmlClass> ();
				 	Set set = htTestRunner.entrySet();
				    Iterator intIterator = set.iterator();
				    while (intIterator.hasNext()) 
				    {
				    	
					     Map.Entry entry = (Map.Entry) intIterator.next();
					   
					      myClasses.add(new XmlClass((String) entry.getKey()));
					      
					      	myTest.setXmlClasses(myClasses); //Assign that to the XmlTest Object created earlier.
					     	lstBrowserVersion = objDBF.getTestDataObject("Select Distinct(BrowserVersion) from TestSuite Where Execution ='Yes' and  ExecutionType='" +aut+"'","TestRunner");
							lstOS = objDBF.getTestDataObject("Select Distinct(OS) from TestSuite Where Execution ='Yes' and  ExecutionType='" +aut+"'","TestRunner");
							lstOSVersion = objDBF.getTestDataObject("Select Distinct(OSVersion) from TestSuite Where Execution ='Yes' and  ExecutionType='" +aut+"'","TestRunner");
						//	myTestNG.setParallel("tests");
							myTestNG.setSuiteThreadPoolSize(poolSize);
							mySuite.setParallel(XmlSuite.ParallelMode.CLASSES);
							mySuite.setThreadCount(poolSize);
					    /* myTest.addParameter("environment", (String) lstExecutionType.get(0))*/; //Execution Type
					     myTest.addParameter("environment", (String) aut);
					   System.out.println(aut);
					     myTest.addParameter("browser", (String) entry.getValue()); //Add any parameters that you want to set to the Test.
					     myTest.addParameter("version", (String)lstBrowserVersion.get(0)); //Add any parameters that you want to set to the Test.
					     myTest.addParameter("os", (String)lstOS.get(0)); //Add any parameters that you want to set to the Test.
					     myTest.addParameter("osversion", (String) lstOSVersion.get(0)); //Add any parameters that you want to set to the Test.
				
				    } 
				    List<XmlTest> myTests = new ArrayList<XmlTest>();//Create a list of XmlTests and add the Xmltest you created earlier to it.
					myTests.add(myTest);
					mySuite.setTests(myTests); //add the list of tests to your Suite.
					List<XmlSuite> mySuites = new ArrayList<XmlSuite>();//Add the suite to the list of suites.
					mySuites.add(mySuite);
					myTestNG.setXmlSuites(mySuites);

					System.out.println(mySuites);
					myTestNG.run(); //invoke run() - this will run your class.
		
		
				//}
			}
			else
			{
				System.out.println("Execution Flag Set as False in TestRunner");
			}
	}

}