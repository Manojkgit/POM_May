package UI_Actions_Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import basePOM.Base_Test_POM;

public class Login_Page extends Base_Test_POM{
    @FindBy(name="username")
    WebElement userName;
    
    @FindBy(name="password")
    WebElement userPassword;

    @FindBy(xpath="//input[@value='Login']")
    WebElement Login_btn;
    
    @FindBy(xpath="//*[text()='Sign Up'][@class='btn']")
    WebElement SignUP_btn;
    
    @FindBy(xpath="//img[@alt='free crm logo']")
    WebElement LOGO;
    
    
    public Login_Page(WebDriver dr,ExtentTest test){
    	super(dr,test);
    	PageFactory.initElements(dr, this);
    }
    
    public boolean Is_Page_LOGO_Displayed(){
    	if(LOGO.isDisplayed()){
    		log.info("Application Logo displayed");
    		test.log(LogStatus.PASS, "Logo Displayed");
    	return true;
    }
    	else{
    		log.info("Application LOGO is not getting displayed....");
    		test.log(LogStatus.FAIL, "Logo NOT Displayed");
    		return false;
    	}
    }
    
    public String getPageTitel(){
    	System.out.println(SignUP_btn.getText());
    	return dr.getTitle();
    }
    
    public Home_Page doLogin(String u,String p){
    	userName.sendKeys(u);
    	test.log(LogStatus.INFO,"Enter user Name in user name text box..." + u);
    	userPassword.sendKeys(p);
    	test.log(LogStatus.INFO,"Enter user Password in user name text box..." + p);
    	userPassword.sendKeys(Keys.ENTER);
    	screenshot();
    	return new Home_Page(dr, test);
    }
    
}
