package com.man.execise;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BrowserEngine;
import listener.TestListener;
import pageObjects.AfterLoginPage;
import pageObjects.AlbumDetailPage;
import pageObjects.HomePage;
import pageObjects.MyAlbumHomePage;

public class TestLogin {
	WebDriver driver;
	String test_url = "http://photo.163.com/";
	HomePage homePage;
	String userName;
	AfterLoginPage afterLoginPage;
	MyAlbumHomePage myAlbumHomePage;
	AlbumDetailPage albumDetailPage;
	String albumName = "0820";
	
  @Test(dataProvider="crendential")
  public void TestLogin(String username, String password) {
	  driver = new BrowserEngine().setFirefox();
	  homePage = new HomePage(driver);
	  TestListener.startTest(TestListener.getTestCaseName(this.getClass()));
	  homePage.open(test_url);
	  afterLoginPage = homePage.loginByCookie();
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
  
  @Test(dependsOnMethods= {"TestDeleteAlbum"})
  public void TestComment() {
	  albumDetailPage = myAlbumHomePage.toAlbumDetail("test0816");
	  albumDetailPage.addComment("test0820");
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
