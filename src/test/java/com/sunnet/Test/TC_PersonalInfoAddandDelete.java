package com.sunnet.Test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.sunnet.GenericLib.GetWebDriverInstance;
import com.sunnet.Pages.PGLogin;
import com.sunnet.Pages.PGPersonalInfo;

public class TC_PersonalInfoAddandDelete extends GetWebDriverInstance{

	@Test
	 public void projectEntrySubmit() throws Exception 
	 {
		PGLogin objLogin = new PGLogin(driver);
		objLogin.superUserLogin();
	//	driver.findElement(By.linkText("Project Entry"));
		PGPersonalInfo pinfo=new PGPersonalInfo(driver);
		pinfo.personalInfoAuto();
		PGPersonalInfo pinfodelete=new PGPersonalInfo(driver);
		pinfodelete.personalInfoDele();
		driver.quit();
	 }}
