package test;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import page.DashboardPage;
import page.LoginPage;
import util.BrowserFactory;

public class DashboardTest {

	WebDriver driver;

	@Test
	public void createDraft() throws InterruptedException {

		Random rnd = new Random();
		String rndExpectedDraftTitle = "AH" + String.valueOf(rnd.nextInt(99));
		String rndexpectedDraftContent = "MidTerm POM" + String.valueOf(rnd.nextInt(99));

		driver = BrowserFactory.getBrowser();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login("opensourcecms", "opensourcecms");
		loginPage.waitForDashboardPage();
		
		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		
		dashboardPage.createDraftInput(rndExpectedDraftTitle, rndexpectedDraftContent);
		Thread.sleep(1000);
		String actualDraftTitle = dashboardPage.getActualDraftTitle(rndExpectedDraftTitle);
		Thread.sleep(1000);
		String actualDraftContent = dashboardPage.getActualDraftContent(rndexpectedDraftContent);

		Assert.assertEquals(actualDraftTitle, rndExpectedDraftTitle, "Draft Title Did NOT Match");
		Assert.assertEquals(actualDraftContent, rndexpectedDraftContent, "Draft Content Did NOT Match");
		
		dashboardPage.getDraftTitleUnderPostsMenu();
		dashboardPage.waitForPostsPage();
		Assert.assertTrue(dashboardPage.getActualDraftPostTitle(rndExpectedDraftTitle), "Draft Title NOT Displayed at Posts");
		dashboardPage.postedDraftSendToTrash(rndExpectedDraftTitle);
		Thread.sleep(2000);
		driver.close();
		driver.quit();

	}

}
