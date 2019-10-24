package com.sunnet.Test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.sunnet.GenericLib.GetWebDriverInstance;
import com.sunnet.GenericLib.Utilities;
import com.sunnet.Pages.PGLogin;
import com.sunnet.Pages.PGOutagePlanSection;
import com.sunnet.Pages.PGPersonalinfosection;

public class TC_OutageAddandDelete extends GetWebDriverInstance{

	@Test
	 public void projectOutage() throws Exception 
	 {
		PGLogin objLogin = new PGLogin(driver);
		objLogin.superUserLogin();
		
		PGOutagePlanSection outage1=new PGOutagePlanSection(driver);
		outage1.outageAdd();
		
		
	 }
}
