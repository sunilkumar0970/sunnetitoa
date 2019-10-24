package com.sunnet.Test;

import org.testng.annotations.Test;

import com.sunnet.GenericLib.GetWebDriverInstance;
import com.sunnet.Pages.PGLogin;
import com.sunnet.Pages.PGOutagePlanSection;

public class OutageAddandDelete extends GetWebDriverInstance{

	@Test
	 public void projectOutage() throws Exception 
	 {
		PGLogin objLogin = new PGLogin(driver);
		objLogin.superUserLogin();
		PGOutagePlanSection addoutage=new PGOutagePlanSection(driver);
		addoutage.addOutage();
		
		
	 }
}
