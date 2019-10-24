package com.sunnet.Test;

import org.testng.annotations.Test;

import com.sunnet.GenericLib.GetWebDriverInstance;
import com.sunnet.Pages.PGLogin;
import com.sunnet.Pages.PGTabs;

public class TC_TabsVerification extends GetWebDriverInstance{
		@Test
		 public void projectEntryValidations() throws Exception 
		 {
			PGLogin objLogin = new PGLogin(driver);
			objLogin.superUserLogin();
			PGTabs pt=new PGTabs(driver);
			pt.verifyProjecttabs();
			pt.verifyRemarksTab();
		    pt.verifyattachTabs();
		    pt.verifyEmailTabs();
		 }

}
