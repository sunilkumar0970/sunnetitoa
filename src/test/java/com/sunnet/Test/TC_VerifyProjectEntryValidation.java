package com.sunnet.Test;

import org.testng.annotations.Test;

import com.sunnet.GenericLib.GetWebDriverInstance;
import com.sunnet.Pages.PGLogin;
import com.sunnet.Pages.validations;

public class TC_VerifyProjectEntryValidation extends GetWebDriverInstance
{
	@Test
	 public void projectEntryValidations() throws Exception 
	 {
		PGLogin objLogin = new PGLogin(driver);
		objLogin.superUserLogin();
		validations mf=new validations(driver);
		mf.verifyMandatoryFields();
	 }

}
