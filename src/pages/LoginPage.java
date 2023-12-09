package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.ControlActions;

public class LoginPage extends ControlActions {

	public void login(String email, String password) {
		System.out.println("Input the Login credentials");
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys(password);
		
		System.out.println("Click on Login");
		driver.findElement(By.xpath("//input[@id='login']")).click();
	}
	
	public boolean isLoginSuccessfullyDisplayed () {
		WebElement loginSuccessfulElement = getElement("xpath", "//div[@aria-label='Login Successfully']", true);
		return loginSuccessfulElement.isDisplayed();
	}
}
