package com.sunnet.Test;
import org.testng.annotations.Test;

import com.sunnet.GenericLib.GetWebDriverInstance;
import com.sunnet.Pages.PGActions;
import com.sunnet.Pages.PGLogin;
import com.sunnet.Pages.PGOutagePlanSection;
public class TC_Actions extends GetWebDriverInstance{

	@Test
	 public void actions() throws Exception 
	 {
		PGLogin objLogin = new PGLogin(driver);
		objLogin.superUserLogin();
		PGActions act=new PGActions(driver);
		act.verifyActions();;
		
		
	 }
}