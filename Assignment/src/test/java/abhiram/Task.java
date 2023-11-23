package abhiram;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

class Tabledata {

	public String HospitalName;
	public int Patientnumber;

	public String getHospitalName() {
		return HospitalName;
	}

	public void setHospitalName(String hospitalName) {
		HospitalName = hospitalName;
	}

	public int getPatientnumber() {
		return Patientnumber;
	}

	public void setPatientnumber(int patientnumber) {
		Patientnumber = patientnumber;
	}

	@Override
	public String toString() {
		return "Tabledata [HospitalName=" + HospitalName + ", Patientnumber=" + Patientnumber + "]";
	}

}

public class Task {

	WebDriver driver;

	@BeforeMethod
	public void Setup() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://dsdedicare.com/uat/admin/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	@Test
	public void Patientcount() throws InterruptedException {

		driver.findElement(By.name("emp_email")).sendKeys("bharath@dedicatedsleep.net");
		driver.findElement(By.name("emp_password")).sendKeys("kjYYUdc$#@78782F");
		driver.findElement(By.name("loginSubmit")).click();

		List<WebElement> tabledata = driver.findElements(By.xpath("//tr[@id='link']"));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		List<Tabledata> dt = new ArrayList<Tabledata>();

		for (int i = 1; i <= tabledata.size(); i++) {

			try {

				WebElement row = driver.findElement(By.xpath("(//tr[@id='link'])[" + i + "]"));
				try {
					row.click();
				} catch (ElementClickInterceptedException e) {
					WebElement row1 = driver.findElement(By.xpath("(//tr[@id='link'])[" + i + "]"));
					Point p = row1.getLocation();
					((JavascriptExecutor) driver).executeScript("window.scrollTo(0, " + (p.getY() - 100) + ");");
					row1.click();
				}

				WebElement mouse = driver.findElement(By.xpath("//i[@class='fa fa-user-md']"));
				Actions ac = new Actions(driver);
				ac.moveToElement(mouse).build().perform();
				// System.out.println("click at after mouse");
				driver.findElement(By.xpath("//a[@href='https://dsdedicare.com/uat/admin/patient-lists']")).click();
				// System.out.println("click at drop");
				driver.findElement(By.xpath("//button[@class='dt-button buttons-collection buttons-page-length']"))
						.click();
				// System.out.println("click at showall");
				driver.findElement(By.xpath("//span[text()='Show all']")).click();
				// System.out.println("click at afetr showall");

				String hospname = driver.findElement(By.xpath("//p[@onclick='openMyOffice()']")).getText();
				List<WebElement> patientcount = driver.findElements(By.xpath("//td[@class='sorting_1']"));

				int patientnumber = patientcount.size();

				Tabledata t = new Tabledata();
				t.setHospitalName(hospname);
				t.setPatientnumber(patientnumber);
				dt.add(t);

				driver.navigate().to("https://dsdedicare.com/uat/admin/practice");
			} catch (Exception e) {
				e.printStackTrace();
				driver.navigate().to("https://dsdedicare.com/uat/admin/practice");
				continue;
			}
		}
		ExcelCode ex = new ExcelCode();
		ex.createsheet(dt);

		Thread.sleep(3000);

	}

}
