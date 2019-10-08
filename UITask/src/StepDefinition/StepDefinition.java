package StepDefinition;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.omg.CORBA.COMM_FAILURE;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.UITask.steps.CommonElements;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class StepDefinition {

	/********************************************
	 *  WebDriver reference and web url's
	 *****************************************/
	 WebDriver driver;
	static String sArticleName	= null;	
	String articleUrl = "https://www.theguardian.com/tone/news";
	String webPageurl = "https://www.theguardian.com";
	String googleUrl = "https://www.google.com";
	
	/*************************************************************
	 *   Cucumber step definitions - Scenario - 1
	 *************************************************************/
	
	@Given("user is on News Article webpage")
	public void user_is_on_News_Article_webpage() throws Throwable {
	   driver = CommonElements.launchBrowser(articleUrl);
	}

	@When("user click on News Article")
	public void user_click_on_News_Article() throws Throwable {
		driver.findElement(CommonElements.okPopup).click();
		driver.findElement(CommonElements.firstArticle).click();
	}

	@And("Navigate to Article page and store the Article title")
	public void navigate_to_Article_page_and_store_the_Article_title() throws Throwable {
	    sArticleName = driver.findElement(CommonElements.articleHeader).getText();
	    System.out.println(sArticleName);
	    driver.close();
	}

	/*************************************************************
	 *   Cucumber step definitions - Scenario - 2
	 *************************************************************/
	
	@Given("user is on google page")
	public void user_is_on_google_page() throws Throwable {
	   driver=CommonElements.launchBrowser(googleUrl);
	}

	@When("user search with Article name")
	public void user_search_with_Article_name()  throws Throwable {
	   driver.findElement(CommonElements.txtGoogleSearch).sendKeys(sArticleName);
	   Thread.sleep(5000);
	   driver.findElement(CommonElements.txtGoogleSearch).sendKeys(Keys.ENTER);
	   
	}

	@Then("verify article is present in different source")
	public void verify_article_is_present_in_different_source() throws Throwable  {
	    List<WebElement> results = driver.findElements(CommonElements.searchresults);
	    boolean status = false;
	    for (WebElement webElement : results) {
	    	String linktext  = webElement.getAttribute("href");
	    	if(linktext.contains(webPageurl)) {
	    		continue;
	    	}else {
	    		status =true;
	    		break;
	    	}
		}
	    Assert.assertEquals("Verify Article", true, status);
	    
	    driver.close();
	}


	
}