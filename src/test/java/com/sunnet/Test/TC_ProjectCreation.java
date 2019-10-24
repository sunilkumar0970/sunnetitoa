package com.sunnet.Test;

import org.testng.annotations.Test;

import com.sunnet.GenericLib.GetWebDriverInstance;
import com.sunnet.Pages.PGLogin;
import com.sunnet.Pages.PGProjectentryCreateAndEdit;

public class TC_ProjectCreation extends GetWebDriverInstance{

	@Test
	 public void verifyCreateprojectEntry() throws Exception 
	 {
		PGLogin objLogin = new PGLogin(driver);
		objLogin.superUserLogin();
		PGProjectentryCreateAndEdit createproject=new PGProjectentryCreateAndEdit(driver);
		createproject.verifyProjectEntry();
		
		 
	 }
}
