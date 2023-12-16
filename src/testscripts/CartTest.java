package testscripts;

import org.testng.annotations.Test;

import pages.CartPage;

public class CartTest extends TestBase{

	@Test
	public void verifyAddToCartFunctionality() {
		login();
		dashboardPage.addElementToCart("ZARA COAT 3");
		CartPage cartPage = dashboardPage.clickOncart();
	}
}
