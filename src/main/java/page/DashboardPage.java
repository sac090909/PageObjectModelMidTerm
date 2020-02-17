package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DashboardPage extends BasePage {

	WebDriver driver;

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.ID, using = "title")
	WebElement titleInput;
	@FindBy(how = How.ID, using = "content")
	WebElement contentInput;
	@FindBy(how = How.ID, using = "save-post")
	WebElement saveButton;

	@FindBy(how = How.XPATH, using = "//div[@class='drafts']")
	WebElement actualDraftPath;

	@FindBy(how = How.XPATH, using = "//div[text()='Posts']")
	WebElement postsMenu;
	@FindBy(how = How.LINK_TEXT, using = "All Posts")
	WebElement allPostsSubMenu;
	@FindBy(how = How.XPATH, using = "//h1[@class='wp-heading-inline']")
	WebElement postsPageHeader;

	@FindBy(how = How.XPATH, using = "//tbody[@id='the-list']")
	WebElement actualDraftPostPath;

	public void createDraftInput(String title, String content) {
		titleInput.sendKeys(title);
		contentInput.sendKeys(content);
		saveButton.click();
	}

//	public String actualDraftTitle() {
//		return actualDraftTitlePath.getText();
//	}

//	public String actualDraftContent() {
//		return actualDraftContentPath.getText();
//	}

	public String getActualDraftTitle(String rndDraftTitle) {

		return (actualDraftPath
				.findElement(By.xpath("//div[@class='drafts']/descendant::a[text()='" + rndDraftTitle + "']")))
						.getText();

	}

	public String getActualDraftContent(String rndDraftContent) {

		return (actualDraftPath
				.findElement(By.xpath("//div[@class='drafts']/descendant::p[text()='" + rndDraftContent + "']")))
						.getText();

	}

	public void getDraftTitleUnderPostsMenu() {
		postsMenu.click();
		allPostsSubMenu.click();
	}
	
	public void waitForPostsPage() {
		waitForElement(driver,postsPageHeader,10);
	}

	public boolean getActualDraftPostTitle(String rndDraftTitle) {

		actualDraftPostPath
				.findElement(By.xpath("//tbody[@id='the-list']/descendant::a[text()='" + rndDraftTitle + "']"))
				.isDisplayed();
		return true;

	}

	public void postedDraftSendToTrash(String rndDraftTitle) {

		Actions action = new Actions(driver);
		action.moveToElement(actualDraftPostPath
				.findElement(By.xpath("//tbody[@id='the-list']/descendant::a[text()='" + rndDraftTitle + "']"))).build()
				.perform();

		driver.findElement(
				By.xpath("//span[@class='trash']//child::a[@aria-label='Move “" + rndDraftTitle + "” to the Trash']"))
				.click();
	}

}
