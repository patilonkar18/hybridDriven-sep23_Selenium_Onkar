package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.ConstantPath;
import utility.PropOperations;

public abstract class ControlActions {

	protected static WebDriver driver;
	
	static public void launchBrowser() {
		PropOperations propOperations = new PropOperations(ConstantPath.ENV_FILEPATH);
		System.setProperty(ConstantPath.CHROME_DRIVER_KEY, ConstantPath.CHROME_DRIVER_VALUE);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(propOperations.getValue("url"));
	}
	
	public static void closeBrowser() {
		driver.close();
	}
	
	protected WebElement getElement(String locatorType, String locaorValue, boolean isWaitRequired) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement e = null;
		
		switch(locatorType.toUpperCase()) {
			case"XPATH":
				if(isWaitRequired)
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locaorValue)));
				else
					e = driver.findElement(By.xpath(locaorValue));
				break;
				
			case"CSS":
				if(isWaitRequired)
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locaorValue)));
				else
					e = driver.findElement(By.cssSelector(locaorValue));
				break;
				
			case"ID":
				if(isWaitRequired)
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locaorValue)));
				else
					e = driver.findElement(By.id(locaorValue));
				break;
				
			case"NAME":
				if(isWaitRequired)
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locaorValue)));
				else
					e = driver.findElement(By.name(locaorValue));
				break;
				
			case"LINKTEXT":
				if(isWaitRequired)
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locaorValue)));
				else
					e = driver.findElement(By.linkText(locaorValue));
				break;
				
			case"PARTIALLINKTEXT":
				if(isWaitRequired)
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locaorValue)));
				else
					e = driver.findElement(By.partialLinkText(locaorValue));
				break;
				
			case"CLASSNAME":
				if(isWaitRequired)
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locaorValue)));
				else
					e = driver.findElement(By.className(locaorValue));
				break;
				
			case"TAGNAME":
				if(isWaitRequired)
					e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locaorValue)));
				else
					e = driver.findElement(By.tagName(locaorValue));
				break;
				
			default:
				System.out.println("Locator is INVALID");
		}
		return e;
	}
}
