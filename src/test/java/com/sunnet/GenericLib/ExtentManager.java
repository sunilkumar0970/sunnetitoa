package com.sunnet.GenericLib;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports instance;
	static String screenshotExtension,reportFolder,destination,screenshotPath,screenshotExtensions;
	static File folder;
	static String browserType,ImagePath,appVersion;
	static WebDriver drivers;
	static int iFlag=0;

	public static synchronized ExtentReports getInstance() {
		if (instance == null) {
			System.out.println(System.getProperty("user.dir"));
			//instance = new ExtentReports(System.getProperty("user.dir") + "/Extent.html");
			SimpleDateFormat sdfDateReport = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			Date now = new Date();
			screenshotExtension=sdfDateReport.format(now) +"_"+System.currentTimeMillis();
			if(iFlag == 0)
			{
				folder = new File(System.getProperty("user.dir") +"/ReportGenerator/SUNNET_IOTA_Automation_"+screenshotExtension);
				
				screenshotPath="SUNNET_IOTA_Automation__"+screenshotExtension;
				if(!folder.exists())
				{
					folder.mkdir();
					iFlag=1;
					
				}
			}
			instance=new ExtentReports(folder+"/SUNNET_IOTA_Automation_"+ sdfDateReport.format(now) +".html",true);
			//instance.addSystemInfo("Execution Result", "https://s3.amazonaws.com//qa-automation-reports/"+screenshotPath+"/NBCAutomation_"+ sdfDateReport.format(now) +".html");
		//	instance.addSystemInfo("Execution Result", "https://generetic-cd-qa.nbc.co/"+screenshotPath+"/NBCAutomation_"+ sdfDateReport.format(now) +".html");
			instance.assignProject("SUNNET");
		
			
		}
		
		return instance;
	}
}
