package TestCases_POM;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Excel_Util_Reader.ExcelDataReader;
import UI_Actions_Pages.Contact_Page;
import UI_Actions_Pages.Home_Page;
import UI_Actions_Pages.Login_Page;
import UI_Actions_Pages.Task_page;
import basePOM.Base_Test_POM;

public class Task_Test extends Base_Test_POM {
	
	String TestSheetName="data";
    String TestCase_Name="Login";
	public Login_Page login;
	public Home_Page homepage;
	public Contact_Page contactPage;
	public Task_page taskPage;
	@Test(dataProvider="getData")
    public void page_Titel_Test(Hashtable<String,String> TC){
		test=ext.startTest("Login Test start to Execute...");
        if(TC.get("Runmode").equals("N")){
    		test.log(LogStatus.SKIP,"Browser has not been opened..Because Runmode is NO");
    		throw new SkipException("Skip the Test because - Run mode is No");
    	}
    	start_Browser_Activation();
    	login=new Login_Page(dr, test);
     	
    	// Now Doing Login with Method Do Login... It will Return Home Page Object.
    	homepage = login.doLogin(TC.get("user"), TC.get("Password"));
    	test.log(LogStatus.PASS, "Login Done After Logo verification");
    	
        taskPage=homepage.Task_tab_OpenPage();
        contactPage=homepage.Contact_Page_Open_Test();
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

