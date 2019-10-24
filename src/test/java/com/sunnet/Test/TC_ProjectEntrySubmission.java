package com.sunnet.Test;

import org.testng.annotations.Test;

import com.sunnet.GenericLib.GetWebDriverInstance;
import com.sunnet.Pages.PGLogin;
import com.sunnet.Pages.PGProjectentry;

public class TC_ProjectEntrySubmission extends GetWebDriverInstance{
	@Test
	 public void projectEntrySubmit() throws Exception 
	 {
		PGLogin objLogin = new PGLogin(driver);
		objLogin.superUserLogin();
		PGProjectentry pe=new PGProjectentry(driver);
		pe.verifyEditProjectEntry();
		//pe.verifyProjectEntry();
		
		 
	 }
}


