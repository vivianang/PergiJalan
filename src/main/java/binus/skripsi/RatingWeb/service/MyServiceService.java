//package binus.skripsi.RatingWeb.service;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import javax.validation.ValidationException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import binus.skripsi.RatingWeb.model.Category;
//import binus.skripsi.RatingWeb.model.MyServiceRequest;
//import binus.skripsi.RatingWeb.repository.CategoryRepository;
//
//@Service
//public class MyServiceService {
//
//	@Autowired
//	CategoryRepository categoryRepository;
//	
//	public String exec(MyServiceRequest myServiceRequest) throws InterruptedException {
//		String changeId = myServiceRequest.getChangeId();
//		
//		String[] arrayChangeId = changeId.split(";");
//		
//		
//		
//		
//		String username = "u068815";
//		String password = "vivian55";
//
//
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\u068815\\Documents\\Work\\Automasi\\chromedriver-win64\\chromedriver.exe");
//
//		WebDriver driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//
//		driver.manage().window().maximize();
//
////		buka web myservice
//		driver.get("https://myservice/HEAT/Account/Login");
//		
////		step login
//		driver.findElement(By.xpath("//input[@id='UserName']")).sendKeys(myServiceRequest.getUdomain());
//		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(myServiceRequest.getPassword());
//		driver.findElement(By.xpath("//button[text()='Login']")).click();
//
////		milih role
//		driver.findElement(By.xpath("//div[@frs-id='Service Desk Support GSIT']")).click();
//		driver.findElement(By.xpath("//button[text()='Submit']")).click();
//
////		buka submenu change
//		driver.findElement(By.xpath("//button[text()='Change']")).click();
//		
//
//		Thread.sleep(1000);
//
//		driver.switchTo().defaultContent();
//		driver.switchTo().frame("ext-gen89");
//		driver.findElement(By.xpath("(//input[@type='text'])[9]")).sendKeys(arrayChangeId[0]);
//		driver.findElement(By.xpath("(//img[@src='./lib/ext-js-31/resources/images/default/s.gif'])[27]")).click();
//		
////		Change logged to requested
//		Thread.sleep(1000);
////		driver.findElement(By.xpath("//input[@frsqa_fname='Status']")).clear();
////		driver.findElement(By.xpath("//input[@frsqa_fname='Status']")).sendKeys("Cancelled");
//
//
//	    driver.findElement(By.xpath("(//button[@type='button'][contains(.,'Cancel Change')])")).click();
//	  
//	    driver.findElement(By.xpath("//td[@class='x-btn-mc'][contains(.,'Yes')]")).click();
//	    
//	    driver.findElement(By.xpath("(//textarea[@autocomplete='off'])[5]")).sendKeys("Update DB");
//	    driver.findElement(By.xpath("(//td[contains(.,'OK')])[7]")).click();
//	  
//		
//		for(int i =1; i < arrayChangeId.length ; i ++) {
//			Thread.sleep(1000);
//			System.out.println(arrayChangeId[i]);
//			driver.findElement(By.xpath("(//input[@type='text'])[58]")).sendKeys(arrayChangeId[i]);
//			Thread.sleep(1000);
//			driver.findElement(By.xpath("(//img[@src='./lib/ext-js-31/resources/images/default/s.gif'])[75]")).click();
//			
////			Change logged to requested
//			Thread.sleep(1000);
////			driver.findElement(By.xpath("//input[@frsqa_fname='Status']")).clear();
////			driver.findElement(By.xpath("//input[@frsqa_fname='Status']")).sendKeys("Cancelled");
//
//
//		    driver.findElement(By.xpath("(//button[@type='button'][contains(.,'Cancel Change')])")).click();
//		    Thread.sleep(1000);
//		    driver.findElement(By.xpath("//td[@class='x-btn-mc'][contains(.,'Yes')]")).click();
//		    Thread.sleep(1000);
//		    driver.findElement(By.xpath("(//textarea[@autocomplete='off'])[5]")).sendKeys("Update DB");
//		    driver.findElement(By.xpath("(//td[contains(.,'OK')])[7]")).click();
//		  
//		}
//		
//		return "success";
//	}
//	
//	
//}
