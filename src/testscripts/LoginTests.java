/*1. complete the TC1 & TC2 from below sheet
https://docs.google.com/spreadsheets/d/1XAsOC5KFoI_AOnB9Zwn4AvSSU0pHXyGgoCexc8Pt-HE/edit#gid=0
*/

package testscripts;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.ControlActions;
import pages.LoginPage;
import utility.ExcelOperations;

public class LoginTests {
	
	LoginPage loginPage;
	
	@BeforeMethod
	void setup() {
		ControlActions.launchBrowser();
		loginPage = new LoginPage();
	}
	
	@Test
	public void verifyLogin(){
		loginPage.login("patilonkar18@gmail.com", "Ishani@1");
		boolean loginFlag = loginPage.isLoginSuccessfullyDisplayed();
		Assert.assertTrue(loginFlag); 
	}
	
	@Test
	public void verifyErrorMassage() {
		System.out.println("STEP- Click on Login Button");
		loginPage.clickOnLoginButton();
		
		System.out.println("VERIFY- Error message for email is visible");
		boolean emailErrorFlag = loginPage.isEmailRequiredElementDisplayed();
		Assert.assertTrue(emailErrorFlag);
		
		System.out.println("VERIFY- Error message for password is visible");
		Assert.assertTrue(loginPage.isPasswordRequiredElementDisplayed());
	}
	
	@Test
	public void verifyPasswordErrorMassage() {
		System.out.println("STEP- Enter valid user email");
		loginPage.enterUserEmail("patilonkar18@gmail.com");
		
		System.out.println("STEP- Click on Login Button");
		loginPage.clickOnLoginButton();
		
		System.out.println("VERIFY- Error message for email is visible");
		boolean emailErrorFlag = loginPage.isEmailRequiredElementDisplayed();
		Assert.assertFalse(emailErrorFlag);
		
		System.out.println("VERIFY- Error message for password is visible");
		Assert.assertTrue(loginPage.isPasswordRequiredElementDisplayed());
	}
	
	@Test
	public void verifyEmailErrorMassage() {
		System.out.println("STEP- Enter valid Password");
		loginPage.enterPassword("Ishani@1");
		
		System.out.println("STEP- Click on Login Button");
		loginPage.clickOnLoginButton();
		
		System.out.println("VERIFY- Error message for email is visible");
		boolean emailErrorFlag = loginPage.isEmailRequiredElementDisplayed();
		Assert.assertTrue(emailErrorFlag);
		
		System.out.println("VERIFY- Error message for password is visible");
		Assert.assertFalse(loginPage.isPasswordRequiredElementDisplayed());
	}
	
	@Test(dataProvider = "LoginDataProvider")
	public void verifyLoginUsingDataProvider(String userName, String password, String loginStatus) {
		System.out.println("STEP- Login with given credentials");
		loginPage.login(userName, password);
		String currentURL = "";
		
		boolean loginFlag;
		if(loginStatus.equalsIgnoreCase("pass")) {
			System.out.println("VERIFY- Login successful message displayed");
			loginFlag = loginPage.isLoginSuccessfullyDisplayed();
			Assert.assertTrue(loginFlag, "Login Successful message is not displayed");
			
			System.out.println("VERIFY- Incorrect email or password message is not displayed");
			loginFlag = loginPage.isLoginUnsuccessfulElementDisplayed();
			Assert.assertFalse(loginFlag, "Incorrect email or password message is displayed");
			
			currentURL = loginPage.getCurrentUrl();
			System.out.println("VERIFY- Application should redirect to the dashboard page");
			Assert.assertTrue(currentURL.endsWith("dashboard/dash"), "Current URL :"+currentURL);
		}else {
			loginFlag = loginPage.isLoginUnsuccessfulElementDisplayed();
			Assert.assertTrue(loginFlag, "Incorrect email or password message is not displayed");
			loginFlag = loginPage.isLoginSuccessfullyDisplayed();
			
			currentURL = loginPage.getCurrentUrl();
			Assert.assertFalse(loginFlag, "Login Successful message is displayed");
			Assert.assertTrue(currentURL.endsWith("auth/login"));
		}
	}
	
	@DataProvider(name="LoginDataProvider")
	public Object[][] getLoginData() throws IOException{
		return ExcelOperations.getAllRows(".//testdata/LoginData.xlsx", "Login");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		int count = 1;
		if (ITestResult.FAILURE == result.getStatus())
			ControlActions.takeScreenshot(result.getName()+count++);
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
