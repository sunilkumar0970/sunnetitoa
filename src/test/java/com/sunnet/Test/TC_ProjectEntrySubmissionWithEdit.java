package com.sunnet.Test;

import org.testng.annotations.Test;

import com.sunnet.GenericLib.GetWebDriverInstance;
import com.sunnet.Pages.PGLogin;
import com.sunnet.Pages.PGProjectentryCreateAndEdit;

public class TC_ProjectEntrySubmissionWithEdit extends GetWebDriverInstance{
	@Test
	 public void projectEntrySubmit() throws Exception 
	 {
		PGLogin objLogin = new PGLogin(driver);
		objLogin.superUserLogin();
		PGProjectentryCreateAndEdit pe=new PGProjectentryCreateAndEdit(driver);
		//pe.verifyProjectEntry();
		pe.verifyEditProjectEntry();
		
		 
	 }
}


