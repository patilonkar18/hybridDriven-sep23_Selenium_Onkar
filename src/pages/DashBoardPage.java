package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ControlActions;

public class DashBoardPage extends ControlActions{

	@FindBy(xpath = "//section//h6[text()='Categories']/following-sibling::div[not(@id)]/label")
	List<WebElement> categoriesElementList;
	
	@FindBy (xpath = "//section//h6[text()='Sub Categories']/following-sibling::div[not(@id)]/label")
	List<WebElement> subCategoriesElementList;
	
	@FindBy(xpath = "//section//h6[text()='Search For']/following-sibling::div[not(@id)]/label")
	List<WebElement> searchForElementList;
	
	@FindBy(xpath = "//div[@class='card']")
	List<WebElement> listOfCarts;
	
	@FindBy(xpath = "//button[@class=\"btn btn-custom\" and contains(text(),'Cart')]")
	WebElement cartMenu;
	
	private CartPage cartPage;
	
	public DashBoardPage() {
		PageFactory.initElements(driver, this);
	}
	
	public int getTotalNumberOfItemsInCategories() {
		return categoriesElementList.size();
	}
	
	public List<String> getCategoriesItemTextList(){
		return getElementTextList(categoriesElementList);
	}
	
	public int getTotalNumberOfItemsInSubCategories() {
		return subCategoriesElementList.size();
	}
	
	public List<String> getSubCategoriesItemTextList(){
		return getElementTextList(subCategoriesElementList);
	}
	
	public int getTotalNumberOfItemsInSearchFor() {
		return searchForElementList.size();
	}
	
	public List<String> getSearchForItemTextList(){
		return getElementTextList(searchForElementList);
	}
	
	public void selectOptionItem(String itemName) {
		String locator = String.format("//section//label[text()='%s']/preceding-sibling::input", itemName);
		getElement("XPATH", locator, true).click();
	}
	
	public boolean isOptionItemSelected(String itemName) {
		String locator = String.format("//section//label[text()='%s']/preceding-sibling::input", itemName);
		return getElement("XPATH", locator, false).isSelected();
	}
	
	public int getNumberOfElementsAvailaleInCart(){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listOfCarts.size();
	}
	
	public void addElementToCart(String productName) {
		productName = productName.toLowerCase();
		String locator = String.format("//b[text()='%s']/parent::h5/following-sibling::button[2]", productName);
		clickOnElement("XPATH", locator, false);
	}
	
	public CartPage clickOncart() {
		clickOnElement(cartMenu, false);
		return cartPage;
	}
}
