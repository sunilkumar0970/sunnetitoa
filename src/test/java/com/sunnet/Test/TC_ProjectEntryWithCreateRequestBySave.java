package com.sunnet.Test;

import org.testng.annotations.Test;

import com.sunnet.GenericLib.GetWebDriverInstance;
import com.sunnet.Pages.PGLogin;
import com.sunnet.Pages.PGProjectEntryWithCreateRequest;

public class TC_ProjectEntryWithCreateRequestBySave extends  GetWebDriverInstance{

@Test	
public void createRequestWithSave() throws Exception {
	PGLogin objLogin = new PGLogin(driver);
	objLogin.superUserLogin();
	PGProjectEntryWithCreateRequest save= new PGProjectEntryWithCreateRequest(driver);
	save.verifyProjectEntryBySave();
}}
