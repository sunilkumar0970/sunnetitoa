package com.sunnet.Test;

import org.testng.annotations.Test;

import com.sunnet.GenericLib.GetWebDriverInstance;
import com.sunnet.Pages.PGLogin;
import com.sunnet.Pages.PGProjectEntryWithCreateRequest;
import com.sunnet.Pages.PGTransmission;

public class TC_TransmisssionRequest extends GetWebDriverInstance{

@Test	
public void transmissionType() throws Exception {
	PGLogin objLogin = new PGLogin(driver);
	objLogin.superUserLogin();
	PGTransmission re=new PGTransmission(driver);
	re.requestEntry();
}}

