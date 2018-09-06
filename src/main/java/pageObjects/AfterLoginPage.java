package pageObjects;

import static org.testng.Assert.assertEquals;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import base.Base;

public class AfterLoginPage extends Base{

	WebElement to_my_album;
	String userName;
	
	public AfterLoginPage(WebDriver driver) {
		super(driver);
	}
	
	public AfterLoginPage(WebDriver driver, String userName) {
		super(driver);
		this.userName = userName;
	}

	//进入我的相册
	public MyAlbumHomePage accessToMyAlbum() {
		to_my_album = findElement(By.xpath("//a[@class='login-btn album clearfix']"));
		//输出元素文案
		Assert.assertEquals(to_my_album.getText(), "进入我的相册", "登陆后，未显示进入我的相册");
		String pageLink = to_my_album.getAttribute("href");
//		System.out.println(pageLink);
		//点击
		click(to_my_album);
		//得到当前页面地址
		assertEquals(getPageUrl(), pageLink, "登陆后，进入我的相册链接错误");
		//得到当前页面标题
		assertEquals(getPageTile(),  getUserName()+ "的网易相册_" +  getUserName()+ "个人相册相片存储_网易相册", "登陆后，进入我的相册错误");
		return new MyAlbumHomePage(driver);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
