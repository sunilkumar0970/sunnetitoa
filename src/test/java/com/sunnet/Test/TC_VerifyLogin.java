package com.sunnet.Test;

import org.testng.annotations.Test;

import com.sunnet.GenericLib.GetWebDriverInstance;
import com.sunnet.Pages.PGLogin;


public class TC_VerifyLogin extends GetWebDriverInstance
{

	@Test
	 public void verifyLoginPage() throws Exception 
	 {
		PGLogin objLogin = new PGLogin(driver);
		objLogin.verifyLogin();
	 }
	
	
}
