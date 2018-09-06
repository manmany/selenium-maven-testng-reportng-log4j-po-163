package com.man.execise;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Utils.logging;
import base.BrowserEngine;
import listener.TestListener;
import pageObjects.AfterLoginPage;
import pageObjects.HomePage;


public class TestChrome {
	WebDriver driver;
	String test_url = "http://photo.163.com/";
	HomePage homePage;
//	String userName;
	AfterLoginPage afterLoginPage;
//	MyAlbumHomePage myAlbumHomePage;
//	AlbumDetailPage albumDetailPage;
//	String albumName = "0820";
	
	@BeforeClass
	public void Setup() {
		  logging.info("================= 开始执行测试 ====================");
		  driver = new BrowserEngine().setChrome();
		  homePage = new HomePage(driver);	
	}
	
	  @Test()
	  public void TestLogin() {
		  TestListener.startTest(TestListener.getTestCaseName(this.getClass()));
		  homePage.open(test_url);
		  afterLoginPage =  homePage.loginByCookie();
	  }
	  
	  @AfterClass(alwaysRun=true)
	  public void tearDown() {
		  homePage.close();
		  logging.info("================= 结束测试 ====================");
	  }
}
