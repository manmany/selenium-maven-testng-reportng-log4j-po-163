package pageObjects;

import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import base.Base;

public class HomePage extends Base {
	//属性
	WebElement learn_more_link;
	WebElement login_email ;
	WebElement login_password;
	WebElement login_button;
	WebElement un_login ;
	WebElement adv_iframe;	
	
	/**
	 * 
	 * loctor
	 */
	//了解更多
	public static By learn_more_link_locator = By.xpath("//span[@class='name']");

	
	//登录信息
	public static By login_iframe = By.xpath("//iframe[@src='http://blog.163.com/newpage/ursweb/tmpl2/loginurs.html?004']");
	public static By real_login_iframe = By.xpath("//iframe[contains(@id, 'x-URS-iframe')]");
	public static By login_email_locator = By.name("email");
	public static By login_password_locator = By.name("password");
	public static By login_button_locator = By.id("dologin");
	

	//	构造函数
	public HomePage(WebDriver driver) {
		super(driver);
	}

	/**
	 * 页面操作
	 */
	
	//打开“了解更多”页面
	public CloudPhotosPage openMoreLink() {
		maximize();
		learn_more_link = findElement(learn_more_link_locator);
		assertEquals(learn_more_link.getText(), "了解更多", "没有‘了解更多‘");
		click(learn_more_link);
		//切换窗口
		switchWindow();
		return new CloudPhotosPage(driver);
	}
	
	//切换广告浮框
	///html/body/div[5]/div/div[2]/div/div/div/a[2]
	//div[@class="m-advc"]//a[2]
	public  void closeAdvs() {
		WebElement closeAdvs = findElement(By.xpath("//div[@class='m-advc']//a[2]"));
		click(closeAdvs);
	}
	
	/*
	 * 获取cookie，并写入文件
	 */
	public void writeCookie(String username, String password) {
		//打开页面
		driver.manage().window().maximize();		
		//登录前的高级操作
		IntelligentWait(5);
		driver.switchTo().frame(findElement(login_iframe));
		driver.switchTo().frame(findElement(real_login_iframe));
		
		login_email = findElement(login_email_locator);
		login_password = findElement(login_password_locator);
		login_button = findElement(By.id("dologin"));
		un_login = findElement(By.id("un-login"));
		
		input(login_email, username);
		input(login_password, password);
		click(un_login);
		click(login_button);
		//如何需要输入验证码，等待时间
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		driver.switchTo().defaultContent();
		WebElement exit = findElement(By.id("photo_index_logout"));
		assertEquals(exit.getText(), "退出", "登录失败！" );
		File file = new File("src/main/resources/TestCookies.txt");
		
//		Set<Cookie> cookies = driver.manage().getCookies();
//
//		if(file.exists()) {
//			file.delete();
//			try {
//				file.createNewFile();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		try {
//			FileWriter fWriter = new FileWriter(file);
//			BufferedWriter bWriter = new BufferedWriter(fWriter);
//			for (Cookie c: cookies) {
//				bWriter.write(c.getDomain() + ";" + c.getName() + ";" + c.getValue() + ";" + c.getPath() + ";" + c.getExpiry() + ";" + c.isSecure());
//				bWriter.newLine();
//			}
//			bWriter.flush();
//			bWriter.close();
//			fWriter.close();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
		writeCookie(file);
	}
	
	/*
	 * 使用cookie登录
	 */
	public AfterLoginPage loginByCookie() {
		File file = new File("src/main/resources/TestCookies.txt");
		String userName = getCookie(file);
		WebElement exit = findElement(By.id("photo_index_logout"));
		assertEquals(exit.getText(), "退出", "登录失败！" );
		return new AfterLoginPage(driver, userName);
	}
	
	/*
	 * 使用用户名和密码登录
	 */
	public AfterLoginPage login(String username, String password) {
		//打开页面
		driver.manage().window().maximize();		
		//登录前的高级操作
		IntelligentWait(5);
		driver.switchTo().frame(findElement(login_iframe));
		driver.switchTo().frame(findElement(real_login_iframe));
		
		login_email = findElement(login_email_locator);
		login_password = findElement(login_password_locator);
		login_button = findElement(By.id("dologin"));
		
		input(login_email, username);
		input(login_password, password);
		click(login_button);
		//如何需要输入验证码，等待时间
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
//		IntelligentWait(60);
		driver.switchTo().defaultContent();
		WebElement exit = findElement(By.id("photo_index_logout"));
		assertEquals(exit.getText(), "退出", "登录失败！" );
		return new AfterLoginPage(driver);
	}
	

}
