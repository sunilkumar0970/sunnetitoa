package com.sunnet.Test;

import org.testng.annotations.Test;

import com.sunnet.GenericLib.GetWebDriverInstance;
import com.sunnet.Pages.PGFiltersSection;
import com.sunnet.Pages.PGLogin;

public class TC_FiltersVerification extends GetWebDriverInstance{

		@Test
		 public void projectEntrySubmit() throws Exception 
		 {
			PGLogin objLogin = new PGLogin(driver);
			objLogin.superUserLogin();
			PGFiltersSection fs=new PGFiltersSection(driver);
//			fs.pEntryList(); 
//			fs.rowHeight();
//			fs.exportList();
		 fs.projectNewType();
		 
		 }
	}

