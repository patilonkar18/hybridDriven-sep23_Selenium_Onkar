package testscripts;

import org.openqa.selenium.support.PageFactory;

import base.ControlActions;

public class DashBoardPage extends ControlActions{

	public DashBoardPage() {
		PageFactory.initElements(driver, this);
	}
}
