package com.man.execise;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utils.logging;
import base.BrowserEngine;
import listener.TestListener;
import pageObjects.AfterLoginPage;
import pageObjects.HomePage;

public class TestBrowserName {
	WebDriver driver;
	String test_url = "http://photo.163.com/";
	HomePage homePage;
	AfterLoginPage afterLoginPage;

	@Parameters({"browserName"})
	@BeforeClass
	public void BeforeClass(String browserName) {
		  logging.info("================= 开始执行测试 ====================");
		  driver = new BrowserEngine().setDriver(browserName);
		  homePage = new HomePage(driver);	
	}
	
	  @Test()
	  public void TestLogin() {
		  TestListener.startTest(TestListener.getTestCaseName(this.getClass()));
		  homePage.open(test_url);
		  afterLoginPage =  homePage.loginByCookie();
	  }
	  
	  @AfterClass(alwaysRun=true)
	  public void AfterClass() {
		  homePage.close();
		  logging.info("================= 结束测试 ====================");
	  }

}
