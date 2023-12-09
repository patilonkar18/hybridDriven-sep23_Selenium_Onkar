/*1. complete the TC1 & TC2 from below sheet
https://docs.google.com/spreadsheets/d/1XAsOC5KFoI_AOnB9Zwn4AvSSU0pHXyGgoCexc8Pt-HE/edit#gid=0
*/

package testscripts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.ControlActions;
import pages.LoginPage;

public class LoginTests {
	
	@BeforeMethod
	void setup() {
		ControlActions.launchBrowser();
	}
	
	@Test
	void verifyLogin(){
		LoginPage loginPage = new LoginPage();
		loginPage.login("patilonkar18@gmail.com", "Ishani@1");
		
		boolean loginFlag = loginPage.isLoginSuccessfullyDisplayed();
		Assert.assertTrue(loginFlag); 
	}
	
	@AfterTest
	public void tearDown() {
		ControlActions.closeBrowser();
	}
	
	void validateCategories() {
		System.setProperty("webdriver.chrome.driver", ".\\chromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://rahulshettyacademy.com/client/");
		
		System.out.println("Input the Login credentials");
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("patilonkar18@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Ishani@1");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		
		List<WebElement> categoryList = driver.findElements(By.xpath("//section//h6[text()='Categories']/parent::div/div[@class='form-group ng-star-inserted']"));
		int count1 = categoryList.size();
		
		List<WebElement> subCategoryList = driver.findElements(By.xpath("//section//h6[text()='Sub Categories']/parent::div/div[@class='form-group ng-star-inserted']"));
		int count2 = subCategoryList.size();
		
		List<WebElement> searchForList = driver.findElements(By.xpath("//section//h6[text()='Search For']/parent::div/div[@class='form-group ng-star-inserted']"));
		int count3 = searchForList.size();
		
		if(count1>0 && count2>0 && count3>0)
			System.out.println("Sections are containing some values");
		else
			System.out.println("Sections are empty");
		
		driver.close();
	}
}
