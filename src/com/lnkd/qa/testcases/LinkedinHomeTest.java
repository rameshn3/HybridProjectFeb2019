package com.lnkd.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lnkd.qa.base.TestBase;
import com.lnkd.qa.pages.LinkedinHomePage;
import com.lnkd.qa.pages.LinkedinLoggedInPage;

public class LinkedinHomeTest extends TestBase{
	LinkedinLoggedInPage llPg;
	LinkedinHomePage lHmPg;
	public LinkedinHomeTest() throws IOException {
		super();
		
	}

@BeforeClass
public void setup() throws IOException{
	initialization();
	llPg=new LinkedinLoggedInPage();
	lHmPg=new LinkedinHomePage();
}

@Test(priority=1)
public void verifyLogo(){
	lHmPg.verifylinkedinLogo();
}

@Test(priority=2)
public void verifyHomePageTitle(){
	Assert.assertEquals(lHmPg.homePageTitle(), "LinkedIn: Log In or Sign Up");
}

@Test(priority=3)
public void loginTest() throws IOException{
	llPg=lHmPg.loginTest(prop.getProperty("username"), prop.getProperty("password"));
	llPg.verifyprofileCard();
	llPg.logOut();
}

@AfterClass
public void tearDown(){
	driver.close();
}
}
