package com.man.execise;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BrowserEngine;
import listener.TestListener;
import pageObjects.HomePage;

public class setCookie {
	WebDriver driver;
	String test_url = "http://photo.163.com/";
	HomePage homePage;
	
	@Test(dataProvider="crendential")
  public void setCookie(String username, String password) {
	  driver = new BrowserEngine().setFirefox();
	  homePage = new HomePage(driver);
	  TestListener.startTest(TestListener.getTestCaseName(this.getClass()));
	  homePage.open(test_url);
	  homePage.writeCookie(username, password);
  }
	
	  @DataProvider(name="crendential")
	  public Object[][] loginInfo(){
		  return  new Object[][] {{"autotest1", "qa1234"}};
	  }
	  
	  @AfterClass(alwaysRun=true)
	  public void tearDown() {
		  homePage.close();
	  }
}
