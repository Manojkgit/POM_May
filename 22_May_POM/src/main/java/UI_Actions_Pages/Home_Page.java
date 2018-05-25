package UI_Actions_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import basePOM.Base_Test_POM;

public class Home_Page extends Base_Test_POM{
	
	@FindBy(xpath="//*[@class='logo_text']")
	WebElement logoText;
	
	@FindBy(xpath="//*[@title='Tasks']")
	WebElement task_link_text;
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement contact_link_text;
	
	public Home_Page(WebDriver dr,ExtentTest test){
		super(dr,test);
		PageFactory.initElements(dr, this);
	}
	
	public String HomePageTitle(){
		String pageTitle=dr.getTitle();
		test.log(LogStatus.PASS,"Page Title Capture successfully....");
		return pageTitle;
	}
	
	public boolean Is_Logo_available(){
		if(logoText.isDisplayed()){
			log.info("Application logo is getting displayed.....");
			test.log(LogStatus.PASS, "Application logo varification....");
			return true;
		}else{
			log.info("Application logo is not getting displayed.....");
			test.log(LogStatus.FAIL, "Application logo not varify. ");
		    return false;	
		}
	}
	
	public Task_page Task_tab_OpenPage(){
		task_link_text.click();
		return new Task_page();
	}
	
	public Contact_Page Contact_Page_Open_Test(){
		swithFrame();
		contact_link_text.click();
		return new Contact_Page();
	}
}
