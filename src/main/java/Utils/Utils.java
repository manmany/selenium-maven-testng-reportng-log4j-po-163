package Utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utils {
	public static void shotscreen(WebDriver driver, String filename) {
		//截图
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//拷贝到相应路径下
		try {
			FileUtils.copyFile(screenshotFile, new File("screenshots/" + filename +".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
