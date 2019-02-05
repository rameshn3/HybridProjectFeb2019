package com.lnkd.qa.testcases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lnkd.qa.base.TestBase;
import com.lnkd.qa.pages.LinkedinHomePage;
import com.lnkd.qa.pages.LinkedinLoggedInPage;
import com.lnkd.qa.pages.SearchResultsPage;
import com.lnkd.qa.util.TestUtil;

public class PeopleSearchDDT extends TestBase{
	LinkedinLoggedInPage llPg;
	LinkedinHomePage lHmPg;
	SearchResultsPage srchPg;
	public PeopleSearchDDT() throws IOException {
		super();
		
	}

	@BeforeClass
	public void setup() throws IOException{
		initialization();
		llPg=new LinkedinLoggedInPage();
		lHmPg=new LinkedinHomePage();
		srchPg=new SearchResultsPage();
		llPg=lHmPg.loginTest(prop.getProperty("username"), prop.getProperty("password"));
		llPg.verifyprofileCard();
	}
	@Test(dataProvider="searchData")
	public void peopleSearchTest(String srchKeyword) throws IOException, InterruptedException{
		srchPg=llPg.searchPeople(srchKeyword);
		int resultsCount=srchPg.getResultsCount();
		System.out.println("results count is==>"+resultsCount);
	}
	
	@DataProvider
	public Object[][] searchData() throws InvalidFormatException, IOException{
	
		Object[][] data=TestUtil.getTestData(TestUtil.TESTDATA_SHEET_PATH1,"Sheet1");
		return data;
	}

	@AfterClass
	public void logOut(){
		llPg.logOut();
		driver.close();
	}
}
