package SeleniumAPI;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic2_Xpath_Css {

	WebDriver driver;
	  @Test
	  	public void f() {
	  	}
	  @BeforeClass
	  	public void beforeClass() {
		  System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		  driver = new ChromeDriver();
		  
		  //driver = new FirefoxDriver();
		  driver.get("http://live.guru99.com");
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  
	  	}
	  @Test
	  	public void TC_01_CheckTitle()
	  	{
		  String homePageTitle = driver.getTitle();
		  Assert.assertEquals(homePageTitle, "Home page");
		  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		  driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		  driver.navigate().back();
		  String loginUrl = driver.getCurrentUrl();
		  Assert.assertEquals(loginUrl, "http://live.guru99.com/index.php/customer/account/login/");
		  driver.navigate().forward();
		  String CreateAnAccountUrl = driver.getCurrentUrl();
		  Assert.assertEquals(CreateAnAccountUrl, "http://live.guru99.com/index.php/customer/account/create/");
		  
	  	}
	  @Test
	  public void TC_02_LoginEmpty()
	  {
		  driver.get("http://live.guru99.com");
		  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		  driver.findElement(By.id("send2")).click();
		  String emailErrorMessage = driver.findElement(By.id("advice-required-entry-email")).getText();
		  Assert.assertEquals(emailErrorMessage, "This is a required field.");
		  String PassErrorMessage = driver.findElement(By.id("advice-required-entry-pass")).getText();
		  Assert.assertEquals(PassErrorMessage, "This is a required field.");
		   
	  }
	  
	  @Test
	  public void TC_03_LoginWithInvalidEmail()
	  {
		  driver.get("http://live.guru99.com");
		  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("thangnh10@abcd");
		  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456123");
		  driver.findElement(By.id("send2")).click();
		  String emailErrorMessage = driver.findElement(By.id("advice-validate-email-email")).getText();
		  Assert.assertEquals(emailErrorMessage, "Please enter a valid email address. For example johndoe@domain.com.");
		  
	  }
	  @Test
	  public void TC_04_LoginWithPassWordLessThan6Characters()
	  {
		  driver.get("http://live.guru99.com");
		  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		  driver.findElement(By.id("send2")).click();
		  String PassErrorMessage = driver.findElement(By.id("advice-validate-password-pass")).getText();
		  Assert.assertEquals(PassErrorMessage, "Please enter 6 or more characters without leading or trailing spaces."); 
		  
	  }
	  @Test
	  public void TC_05_LoginWithIncorrectPass()
	  {
		  driver.get("http://live.guru99.com");
		  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		  driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		  driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
		  driver.findElement(By.id("send2")).click();
		  String InvalidEmailPass = driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText();
		  Assert.assertEquals(InvalidEmailPass, "Invalid login or password.");
				  
	  }
	  @Test
	  public void TC_06_RandomEmail()
	  {	
		  String firstname = "Selenium", lastname = "Online 07", email = "seleniumonline" + randomEmail() + "@gmail.com", password = "123123";
		  System.out.println("Email random= " + email);
		  driver.get("http://live.guru99.com");
		  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		  driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		  driver.findElement(By.id("firstname")).sendKeys(firstname);
		  driver.findElement(By.id("lastname")).sendKeys(lastname);
		  driver.findElement(By.name("email")).sendKeys(email);
		  driver.findElement(By.id("password")).sendKeys(password);
		  driver.findElement(By.className("validate-cpassword")).sendKeys(password);
		  driver.findElement(By.xpath("//button[@title='Register']")).click();
		  Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		  driver.findElement(By.xpath("//div[@class='page-header-container']//span[text()='Account']")).click();
		  driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		  Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='This is demo site for']")).isDisplayed());
		  
	  }
	  public int randomEmail()
	  {
		  Random random =  new Random();
		  int number = random.nextInt(99999);
		  System.out.println("Random number = " + number);
		  return number;
	  }
	  
	  @AfterClass
	  	public void afterClass() {
		  driver.close();
	  	}
}
