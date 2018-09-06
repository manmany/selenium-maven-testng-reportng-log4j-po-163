package com.man.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BrowserEngine;
import listener.TestListener;
import pageObjects.CloudPhotosPage;
import pageObjects.HomePage;

public class TestDownload {
	WebDriver driver;
	String test_url = "http://photo.163.com/";
	HomePage homePage;
	
  @Test(dataProvider="downloadType")
  public void TestDownload(String type) throws InterruptedException {
	  driver = new BrowserEngine().setFirefox();
	  homePage = new HomePage(driver);
	  TestListener.startTest(TestListener.getTestCaseName(this.getClass()));
	  homePage.open(test_url);
	  homePage.closeAdvs();
	  CloudPhotosPage cloudPhotosPage = homePage.openMoreLink();
	  cloudPhotosPage.download(type);
	  cloudPhotosPage.close();
  }
  
  @DataProvider(name="downloadType")
  public static Object[][] downloadType(){
	  return new Object[][] {{"Android"},  {"iPhone"},  {"iPad"}};
  }
}
