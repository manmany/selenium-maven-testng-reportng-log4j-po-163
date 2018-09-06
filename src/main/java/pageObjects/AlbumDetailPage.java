package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import base.Base;

public class AlbumDetailPage extends Base {

	public AlbumDetailPage(WebDriver driver) {
		super(driver);
	}

	//上传图片
	

	//添加评论
	public void addComment(String comment) {	
		//切换frame到底部评论区
		switchFrame(findElement(By.xpath("//div[@class='j-main']//iframe")));
		//找到评论区，输入评论内容
		WebElement commentAera = findElement(By.xpath("//body"));
		commentAera.sendKeys(comment);
		//切换回主页面
		driver.switchTo().defaultContent();
		//点击发表评论按钮
		WebElement submitCommentButton = driver.findElement(By.xpath("//input[@value='发表']"));
		submitCommentButton.click();
		//验证发表的评论内容就是刚输入的
		String commentText = driver.findElement(By.xpath("//div[@class='cnt fc98 js-cnt pre c-tag']")).getText();
		Assert.assertEquals(commentText, comment, "评论内容错误");
	}
	
	//删除评论
	
	
	//返回我的相册首页
	public  MyAlbumHomePage toMyAlbumHome() {
		WebElement album = findElement(By.linkText("相册"));
		click(album);
		info("回到相册首页");
		return new MyAlbumHomePage(driver);
	}
	
	//切换广告浮框
	///html/body/div[5]/div/div[2]/div/div/div/a[2]
	//div[@class="m-advc"]//a[2]
	public  void closeAdvs() {
		WebElement closeAdvs = findElement(By.xpath("//a[@title=‘关闭’]"));
		click(closeAdvs);
		info("关闭广告浮框" + closeAdvs.toString());
	}
}
