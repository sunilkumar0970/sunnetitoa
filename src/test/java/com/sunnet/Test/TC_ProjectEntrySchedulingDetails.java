package com.sunnet.Test;

import org.testng.annotations.Test;

import com.sunnet.GenericLib.GetWebDriverInstance;
import com.sunnet.Pages.PGLogin;
import com.sunnet.Pages.SchedulingDetails;

public class TC_ProjectEntrySchedulingDetails extends GetWebDriverInstance{

	@Test
	 public void verifyprojectEntrySchdetails() throws Exception 
	 {
		PGLogin objLogin = new PGLogin(driver);
		objLogin.superUserLogin();
		SchedulingDetails sd=new SchedulingDetails(driver);
		sd.schedDtls();
		 
	 }
}
