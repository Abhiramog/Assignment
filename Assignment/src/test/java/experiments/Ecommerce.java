package experiments;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ecommerce {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://e-quarz.com/product/the-blue-umbrella-dxGIJG");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		WebElement pinterest = driver.findElement(By.xpath("//div[@data-network='pinterest']"));
		String parentwindow=driver.getWindowHandle();
		pinterest.click();
		Set<String>childwindow=driver.getWindowHandles();
		
		for(String windows:childwindow) {
			
			if(!parentwindow.equals(windows)) {
				driver.switchTo().window(windows);
				
				break;
			}
		}
		driver.manage().window().maximize();
		Thread.sleep(3000);
		
		driver.switchTo().window(parentwindow);
		
		//driver.close();

	}

}
