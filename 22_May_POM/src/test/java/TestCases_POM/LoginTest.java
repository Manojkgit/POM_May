package TestCases_POM;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import Constat_Util.Constant_Util_Class;
import Excel_Util_Reader.ExcelDataReader;
import UI_Actions_Pages.Home_Page;
import UI_Actions_Pages.Login_Page;
import basePOM.Base_Test_POM;

public class LoginTest extends Base_Test_POM {
    String TestSheetName="data";
    String TestCase_Name="Login";
	public Login_Page login;
	public Home_Page homepage;
	@Test(dataProvider="getData")
    public void page_Titel_Test(Hashtable<String,String> TC){
		test=ext.startTest("Login Test start to Execute...");
        if(TC.get("Runmode").equals("N")){
    		test.log(LogStatus.SKIP,"Browser has not been opened..Because Runmode is NO");
    		throw new SkipException("Skip the Test because - Run mode is No");
    	}
    	start_Browser_Activation();
    	login=new Login_Page(dr, test);
        
    	String LoginPage_Titel=login.getPageTitel();
        Assert.assertEquals(LoginPage_Titel, Constant_Util_Class.Expected_Page_Titel);
        
        test.log(LogStatus.PASS, "Varification of Page Titel Pass");
        log.info("Page titel verification Done.... " + '"'+LoginPage_Titel+'"');
        System.out.println("********************************************************************");
        System.out.println("********************************************************************");
        
        login.Is_Page_LOGO_Displayed();
    	log.info("Page LOGO verification Done.... ");
    	test.log(LogStatus.PASS, "Varification of Page Titel Pass");
    	
    	// Now Doing Login with Method Do Login... It will Return Home Page Object.
    	homepage = login.doLogin(TC.get("user"), TC.get("Password"));
    	test.log(LogStatus.PASS, "Login Done After Logo verification");
     }    
	
	@DataProvider
    public Object[][] getData(){
    	return ExcelDataReader.getDataReader(xls, TestSheetName, TestCase_Name);
    }
    
    @AfterTest
    public void tearDown(){
    	ext.endTest(test);
    	ext.flush();
    	quit_Browser(5);
    }
}
