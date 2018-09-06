package pageObjects;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import base.Base;

public class CloudPhotosPage extends Base {
	//属性
	@FindBy(linkText="iphone")
	@CacheLookup
	WebElement iphone;
	
	@FindBy(linkText="android")
	@CacheLookup
	WebElement android;
	
	@FindBy(linkText="ipad")
	@CacheLookup
	WebElement ipad;

//	WebElement android;
//	WebElement ipad;
	
	@FindBy(id="J-xbox-title")
	@CacheLookup
	WebElement j_xbox_title;
	
	public CloudPhotosPage(WebDriver driver) {
		super(driver);
	}

	/*
	 * 下载客户端
	 */
	
	public void download(String type) throws InterruptedException {	
		if(type.equalsIgnoreCase("iphone")) {
			click(iphone);
		}
		if(type.equalsIgnoreCase("android")) {
//			android = findElement(By.linkText("android"));
			click(android);
		}
		if(type.equalsIgnoreCase("ipad")) {
//			ipad = findElement(By.linkText("ipad"));
			click(ipad);
		}
		Thread.sleep(2000);

		assertEquals(j_xbox_title.getText(), type +"版下载",  "没有打开"+ type+"下载页面");			
	}
}
