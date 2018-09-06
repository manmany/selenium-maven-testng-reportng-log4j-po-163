package pageObjects;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import base.Base;

public class MyAlbumHomePage extends Base {
	//创建相册的属性
	WebElement newAlbum;
	WebElement albumName;
	WebElement albumDescription;
	WebElement createConfirmButton;
	
	//删除相册的属性
	WebElement deleteAlbum;
	WebElement album;
	WebElement confirmDelete;
	
	
	public MyAlbumHomePage(WebDriver driver) {
		super(driver);
	}

	//创建相册
	public AlbumDetailPage createAlbum(String aName) {
		newAlbum = findElement(By.linkText("创建相册"));
		click(newAlbum);
		albumName = findElement(By.tagName("input"));
		createConfirmButton = findElement(By.name("fm-a"));
		input(albumName, aName);
		click(createConfirmButton);		
		//验证当前的相册就是新创建的相册
		String albumRealName = findElement(By.xpath("//div[contains(@class, 'extra-menu')]/span")).getText();
		Assert.assertEquals(aName, albumRealName);
		return new AlbumDetailPage(driver);
	}
	
	//判断某相册是否存在
	public boolean isAlbumExists(String name) {
		try {
			findElement(By.xpath("//img[@title='" + name +"']"));
			return true;
		}catch(NoSuchElementException e) {
			return false;
		}
	}
	
	//删除相册
	public void deleteAlbum(String name) {
		//点击删除
		Actions action = new Actions(driver);
		album = findElement(By.xpath("//img[@title='" + name +"']"));
		deleteAlbum = findElement(By.xpath("//*[@id=\"photo-163-com-container\"]//div[@class='ln ln3 clearfix']/a[@title='删除']"));
		action.moveToElement(album).click(deleteAlbum).build().perform();
		
		//判断提示信息
		String confirmText = findElement(By.className("lay-cncase")).getText();
		assertEquals(confirmText, "删除该相册，你的博客相册和摄影组图也将会相应的删除。确定删除？", "提示信息错误");
		//确认或者取消
		confirmDelete = findElement(By.xpath("//button[@class='ui-btn ui-btn-sub0']"));
		click(confirmDelete);		
		assertEquals(isAlbumExists(name), true, "没有删除成功");
		
	}
	
	
	//进入某相册
	public AlbumDetailPage toAlbumDetail(String name) {
		driver.navigate().refresh();
		album = findElement(By.xpath("//img[@title='" + name +"']"));
		click(album);
		info("进入相册" + name);
		//验证当前的相册就是打开的相册
		String albumRealName = findElement(By.xpath("//div[contains(@class, 'extra-menu')]/span")).getText();
		Assert.assertEquals(albumRealName, name );
		return new AlbumDetailPage(driver);
	}
	
	//排序 - 自定义
	public void reOrderAlbum() {
		
	}

	//切换广告浮框
	///html/body/div[5]/div/div[2]/div/div/div/a[2]
	//div[@class="m-advc"]//a[2]
	public  void closeAdvs() {
		WebElement closeAdvs = findElement(By.xpath("//div[@class='m-advc']//a[2]"));
		click(closeAdvs);
	}
}
