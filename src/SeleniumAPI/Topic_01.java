package SeleniumAPI;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01 {
	WebDriver driver;
  @Test
  	public void f() {
  	}
  @BeforeClass
  	public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
	  driver = new ChromeDriver();
	  
	  //driver = new FirefoxDriver();
	  driver.get("https://lingio.co");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  	}
  @Test
  	public void TC_01_CheckTitle()
  	{
	  String homePageTitle = driver.getTitle();
	  Assert.assertEquals(homePageTitle, "lingio.co");
  	}
  @AfterClass
  	public void afterClass() {
	  //driver.close();
  	}

}
