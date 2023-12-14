package base;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.ConstantPath;
import utility.PropOperations;

public abstract class ControlActions {

	protected static WebDriver driver;
	private static PropOperations propOperations;
	private static WebDriverWait wait;
	
	static public void launchBrowser() {
		propOperations = new PropOperations(ConstantPath.ENV_FILEPATH);
		System.setProperty(ConstantPath.CHROME_DRIVER_KEY, ConstantPath.CHROME_DRIVER_VALUE);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(propOperations.getValue("url"));
		wait = new WebDriverWait(driver, ConstantPath.WAIT);
	}
	
	public static void closeBrowser() {
		driver.close();
	}
	
	protected WebElement getElement(String locatorType, String locatorValue, boolean isWaitRequired) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement e = null;
		
		switch(locatorType.toUpperCase()) {
			case"XPATH":
				if(isWaitRequired)
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
				else
					e = driver.findElement(By.xpath(locatorValue));
				break;
				
			case"CSS":
				if(isWaitRequired)
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
				else
					e = driver.findElement(By.cssSelector(locatorValue));
				break;
				
			case"ID":
				if(isWaitRequired)
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
				else
					e = driver.findElement(By.id(locatorValue));
				break;
				
			case"NAME":
				if(isWaitRequired)
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
				else
					e = driver.findElement(By.name(locatorValue));
				break;
				
			case"LINKTEXT":
				if(isWaitRequired)
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
				else
					e = driver.findElement(By.linkText(locatorValue));
				break;
				
			case"PARTIALLINKTEXT":
				if(isWaitRequired)
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
				else
					e = driver.findElement(By.partialLinkText(locatorValue));
				break;
				
			case"CLASSNAME":
				if(isWaitRequired)
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
				else
					e = driver.findElement(By.className(locatorValue));
				break;
				
			case"TAGNAME":
				if(isWaitRequired)
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
				else
					e = driver.findElement(By.tagName(locatorValue));
				break;
				
			default:
				System.out.println("Locator is INVALID");
		}
		return e;
	}
	
	protected void waitForElementToBeVisible(WebElement e) {
		wait.until(ExpectedConditions.visibilityOf(e));
	}
	
	protected void waitForElementToBeClickable(WebElement e) {
		wait.until(ExpectedConditions.elementToBeClickable(e));
	}
	
	protected void waitForElementToBeInvisible(WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, ConstantPath.FAST_WAIT);
		wait.until(ExpectedConditions.invisibilityOf(e));
	}
	
	protected boolean isElementDisplayed(WebElement e) {
		try {
			return e.isDisplayed();
		}catch(NoSuchElementException ne){
			return false;
		}
	}
	
	protected boolean isElementDisplayedWithWait(WebElement e) {
		try {
			wait.until(ExpectedConditions.visibilityOf(e));
			return true;
		}catch(Exception ne){
			return false;
		}
	}
	
	protected String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	public static void takeScreenshot (String fileName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(".//Screenshots/"+fileName+".png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
