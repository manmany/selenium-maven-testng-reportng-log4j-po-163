package base;

import java.io.File;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import Utils.logging;

public class BrowserEngine extends logging{
	WebDriver driver = null;
	
	public WebDriver setFirefox() {
		String osType = System.getProperty("os.name");
		System.out.println("Test on " + osType);
		info("Test on " + osType);
		
		if(osType.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
		}
//		
//		FirefoxProfile fp = new FirefoxProfile();
//		ProfilesIni allProfiles = new ProfilesIni();
//		fp = allProfiles.getProfile("default");
//		FirefoxOptions options = new FirefoxOptions();
//		options.setProfile(fp);
//		
		info("打开火狐浏览器");
		return new FirefoxDriver();
	}
	public WebDriver setChrome() {
		String osType = System.getProperty("os.name");
		System.out.println("Test on " + osType);
		info("Test on " + osType);
		ChromeOptions options = new ChromeOptions();

		if(osType.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			options.addArguments("user-data-dir=C:/Users/Administrator/AppData/Local/Google/Chrome/User Data");
			return new ChromeDriver(options);
		}
		
		if(osType.contains("Mac")) {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
		}
		info("打开谷歌浏览器");
		return new ChromeDriver();
	}

	public WebDriver setDriver(String driverName) {
		String osType = System.getProperty("os.name");
		info("Test on " + osType);
		
		if (driverName.equalsIgnoreCase("firefox")) {
			if(osType.contains("Windows")) {
				System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
			}
			info("打开火狐浏览器");
			driver = new FirefoxDriver();		
		}
		
		if (driverName.equalsIgnoreCase("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			if(osType.contains("Windows")) {
				System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
				options.addArguments("user-data-dir=C:/Users/Administrator/AppData/Local/Google/Chrome/User Data");
				driver = new ChromeDriver(options);
			}
			if(osType.contains("Mac")) {
				System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
			}
			info("打开谷歌浏览器");
			driver = new ChromeDriver();
		}
		
		return driver;
	}
}
