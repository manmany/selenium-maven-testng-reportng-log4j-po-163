package com.man.execise;

import org.testng.annotations.Test;

import Utils.logging;
import base.BrowserEngine;
import listener.TestListener;
import pageObjects.AfterLoginPage;
import pageObjects.HomePage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class TestLoginParameter {
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
	
	@Parameters({"username", "password"})
	  @Test()
	  public void TestLogin(String username, String password) {
		  driver = new BrowserEngine().setFirefox();
		  homePage = new HomePage(driver);
		  TestListener.startTest(TestListener.getTestCaseName(this.getClass()));
		  homePage.open(test_url);
		  afterLoginPage = homePage.loginByCookie();
	  }
	  
	  @AfterClass(alwaysRun=true)
	  public void AfterClass() {
		  homePage.close();
		  logging.info("================= 结束测试 ====================");
	  }

}
