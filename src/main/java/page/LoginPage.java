package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.ID, using = "user_login")
	WebElement username;
	@FindBy(how = How.ID, using = "user_pass")
	WebElement password;
	@FindBy(how = How.ID, using = "wp-submit")
	WebElement loginButton;

	@FindBy(how = How.XPATH, using = "//h1[text()='Dashboard']")
	WebElement dashboardPageHeader;

	public String getLoginPageTitel() {
		return driver.getTitle();
	}

	public void login(String uname, String pwd) {

		username.sendKeys(uname);
		password.sendKeys(pwd);
		loginButton.click();

	}

	public void waitForDashboardPage() {

		waitForElement(driver, dashboardPageHeader, 10);
	}



}
