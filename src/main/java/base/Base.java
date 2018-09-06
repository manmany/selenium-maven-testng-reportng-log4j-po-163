package base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.Utils;
import Utils.logging;

import org.openqa.selenium.interactions.Action;


public class Base extends logging {
	//属性
	public static WebDriver driver;
	public int globaltimeout = 5;
	public static String pageTitle;
	public static String pageUrl;
	
	//构造函数
	public Base(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, globaltimeout), this);
	}
	

	/**
	 * 封装公共方法
	 */
	//封装打开网址
	public void open(String url) {
		driver.get(url);
		info("打开网址：" + url);
	}
	//关闭浏览器
	public void close() {
		driver.quit();
		info("关闭浏览器");	
	}
	//浏览器最大化
	public void maximize() {
		driver.manage().window().maximize();
		info("最大化浏览器");
	}
	
	//浏览器窗口切换
	public void switchWindow() {
		String currentWindow = driver.getWindowHandle();	//	获取当前窗口
		Set<String> handles = driver.getWindowHandles();	//获取所有窗口句柄
		info("当前窗口数：" + handles.size());
		Iterator<String> iterator = handles.iterator();
		while(iterator.hasNext()) {
			if(currentWindow == iterator.next()) {
				continue;
			}
			try {
				driver = driver.switchTo().window(iterator.next());
				info("新窗口的标题是：" + getPageTile());
			} catch (Exception e) {
				info("无法切换至新打开的窗口");
				info(e.getMessage());
			}
		}
	}
	
	//切换frame
	public void switchFrame(WebElement element) {
		driver.switchTo().frame(element);
		info("切换frame到" + element.toString());
	}
	
	//等待
	public void IntelligentWait(long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		info("等待 " + timeout + "秒");
	}
	
	/**
	 * 页面操作方法
	 */
	//在文本框中输入
	public void input(WebElement element, String  text) {
		element.sendKeys(text);
		info("输入值：" + text);
	}
	
	//点击按钮（鼠标左键）
	public void click(WebElement element) {
		info("点击" + element.toString() + element.getText());
		element.click();
	}
	
	public void clear(WebElement element) {
		element.clear();
		info("清空输入框" + element.toString());
	}
	
	public void DragAndDrop(WebElement toDrag ,WebElement toDrop){
        try{
             Actions builder = new Actions(driver);
                builder.keyDown(Keys.CONTROL)
                    .click(toDrag)
                    .dragAndDrop(toDrag, toDrop)
                    .keyUp(Keys.CONTROL);
     
                    Action selected = builder.build();
                    selected.perform();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
	
	//获取页面url
	public String getPageUrl() {
		pageUrl = driver.getCurrentUrl();
		info("当前页面的网址为：" + pageUrl);
		return pageUrl;
	}
	//获取页面title
	public String  getPageTile() {
		pageTitle = driver.getTitle();
		info("当前页面的标题为：" + pageTitle);
		return pageTitle;
	}
	
	/**
	 * 元素操作: 查找元素
	 */
	public WebElement findElement(By locator) {
		WebElement element = null;
		try {
			element = new WebDriverWait(driver, globaltimeout).until(ExpectedConditions.presenceOfElementLocated(locator));
			info("根据 " + this.getClass().getName() + locator.toString() + "查找元素，" + this.getClass().getName() + element.toString() + "对象被访问");
			return element;
		} catch (Exception e) {
			error("根据 " + this.getClass().getName() + locator.toString() + "查找元素不存在");
			String filename = this.getClass().getName() + locator.toString();
			Utils.shotscreen(driver, filename);
			return element;
		}
	}
	
	
	/**
	 * 从文件中，读取cookie信息
	 */
	public String getCookie(File file) {
		String userName = null;
//		File file = new File("src/main/resources/TestCookies.txt");
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = null;
			int count = 0;
			while((line = bufferedReader.readLine()) != null) {
				String[] sz = line.split(";");
				String name = sz[1].trim();
				String value = sz[2].trim();
				
				Cookie cookie = new Cookie(name, value);
				if (count == 0) {
					userName = value;
//					System.out.println(userName);
				}
				
				driver.manage().addCookie(cookie);
				count ++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		open(open_url);
		driver.navigate().refresh();
		info("使用cookie登陆");
		return userName;	
	}
	
	/**
	 * 
	 */
	
	public void writeCookie(File file) {
		Set<Cookie> cookies = driver.manage().getCookies();

//		File file = new File("src/main/resources/TestCookies.txt");
		if(file.exists()) {
			file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			FileWriter fWriter = new FileWriter(file);
			BufferedWriter bWriter = new BufferedWriter(fWriter);
			for (Cookie c: cookies) {
				bWriter.write(c.getDomain() + ";" + c.getName() + ";" + c.getValue() + ";" + c.getPath() + ";" + c.getExpiry() + ";" + c.isSecure());
				bWriter.newLine();
			}
			bWriter.flush();
			bWriter.close();
			fWriter.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
