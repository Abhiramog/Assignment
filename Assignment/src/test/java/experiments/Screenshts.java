package experiments;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class Screenshts {
	
	WebDriver driver;

	@Test
	public void login() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://katalon-demo-cura.herokuapp.com/profile.php#login");
		driver.findElement(By.id("txt-username")).sendKeys("John Doe");
		driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
		driver.findElement(By.id("btn-login")).click();

		String url = driver.getCurrentUrl();
		Assert.assertEquals("https://katalon-demo-cura.herokuapp.com/#appointmen", url);
		
		//driver.switchTo().newWindow(WindowType.TAB);
	}
	
	@Test
	public void loginvalid() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://katalon-demo-cura.herokuapp.com/profile.php#login");
		driver.findElement(By.id("txt-username")).sendKeys("John Doe");
		driver.findElement(By.id("txt-password")).sendKeys("ThisIsNotAPassword");
		driver.findElement(By.id("btn-login")).click();

		String url = driver.getCurrentUrl();
		Assert.assertEquals("https://katalon-demo-cura.herokuapp.com/#appointment", url);
	}


	@AfterMethod

	public void teardown(ITestResult result) throws Throwable {
		
		if (result.getStatus() == ITestResult.FAILURE) {
            capture(result.getMethod().getMethodName());
        }
	}

	public void capture(String methodname) throws Throwable {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File tgt = new File("./shots/" + methodname + ".png");
		FileUtils.copyFile(src, tgt);
		
	}

}
