package com.man.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BrowserEngine;
import listener.TestListener;
import pageObjects.AfterLoginPage;
import pageObjects.AlbumDetailPage;
import pageObjects.HomePage;
import pageObjects.MyAlbumHomePage;

public class TestUsingCookie {
	
	WebDriver driver;
	String test_url = "http://photo.163.com/";
	HomePage homePage;
	String userName;
	AfterLoginPage afterLoginPage;
	MyAlbumHomePage myAlbumHomePage;
	AlbumDetailPage albumDetailPage;
	String albumName = "0820";
	
	@Parameters({"driverName"})
	  @Test()
	  public void TestLogin(String driverName) {
		  driver = new BrowserEngine().setDriver(driverName);
		  homePage = new HomePage(driver);
		  TestListener.startTest(TestListener.getTestCaseName(this.getClass()));
		  homePage.open(test_url);
		  afterLoginPage =  homePage.loginByCookie();
	  }
	  
	  @Test(dependsOnMethods= {"TestLogin"})
	  public void TestAccessToMyAlbum() {
		  TestListener.startTest(TestListener.getTestCaseName(this.getClass()));
		  myAlbumHomePage = afterLoginPage.accessToMyAlbum();
	  }

	  @Test(dependsOnMethods= {"TestAccessToMyAlbum"})
	  public void TestCreateAlbum() {
		  TestListener.startTest(TestListener.getTestCaseName(this.getClass()));
		  albumDetailPage = myAlbumHomePage.createAlbum(albumName);
		  myAlbumHomePage = albumDetailPage.toMyAlbumHome();
	  } 
		 
	  @Test(dependsOnMethods= {"TestCreateAlbum"})
	  public void TestDeleteAlbum() {
		  TestListener.startTest(TestListener.getTestCaseName(this.getClass()));
		  myAlbumHomePage.deleteAlbum(albumName);
	  }

	  /*
	  @Test(dependsOnMethods= {"TestDeleteAlbum"})
	  public void TestComment() {
		  myAlbumHomePage.startTest(myAlbumHomePage.getTestCaseName(this.getClass()));
		  albumDetailPage = myAlbumHomePage.toAlbumDetail("test0816");
		  albumDetailPage.addComment("test0820");
	  }
	  */
	  
	  @AfterClass(alwaysRun=true)
	  public void tearDown() {
		  homePage.close();
	  }
}
