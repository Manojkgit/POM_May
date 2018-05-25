package TestCases_POM;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Excel_Util_Reader.ExcelDataReader;
import UI_Actions_Pages.Contact_Page;
import UI_Actions_Pages.Home_Page;
import UI_Actions_Pages.Login_Page;
import UI_Actions_Pages.Task_page;
import basePOM.Base_Test_POM;

public class Home_Test extends Base_Test_POM {
	String TestSheetName="data";
    String TestCase_Name="Login";
	public Login_Page login;
	public Home_Page homepage;
	public Task_page taskPage;
	public Contact_Page contactPage;
	@Test(dataProvider="getData", priority=1)
    public void Home_page_Titel_Test(Hashtable<String,String> TC){
		test=ext.startTest("Login Test start to Execute...");
        if(TC.get("Runmode").equals("N")){
    		test.log(LogStatus.SKIP,"Browser has not been opened..Because Runmode is NO");
    		throw new SkipException("Skip the Test because - Run mode is No");
    	}
    	start_Browser_Activation();
    	login=new Login_Page(dr, test);
        homepage=new Home_Page(dr, test);
        
    	// Now Doing Login with Method Do Login... It will Return Home Page Object.
    	homepage = login.doLogin(TC.get("user"), TC.get("Password"));
    	test.log(LogStatus.PASS, "Login Done After Logo verification");
    	
    	String actualTitle = homepage.HomePageTitle();
    	Assert.assertEquals(actualTitle, "CRMPRO");
    	test.log(LogStatus.PASS, "Login Done Page title verification done");
    	screenshot();
	}  
	
	@Test(dataProvider="getData", priority=2)
    public void Task_page_Titel_Test(Hashtable<String,String> TC){
		test=ext.startTest("Login Test start to Execute...");
        if(TC.get("Runmode").equals("N")){
    		test.log(LogStatus.SKIP,"Browser has not been opened..Because Runmode is NO");
    		throw new SkipException("Skip the Test because - Run mode is No");
    	}
    	start_Browser_Activation();
    	login=new Login_Page(dr, test);
        homepage=new Home_Page(dr, test);
    	// Now Doing Login with Method Do Login... It will Return Home Page Object.
    	homepage = login.doLogin(TC.get("user"), TC.get("Password"));
    	test.log(LogStatus.PASS, "Login Done After Logo verification");
    	swithFrame();
    	wait(2);
    	taskPage=homepage.Task_tab_OpenPage();
    	screenshot();
	}    

	@Test(dataProvider="getData", priority=3)
    public void Contact_page_Titel_Test(Hashtable<String,String> TC){
		test=ext.startTest("Login Test start to Execute...");
        if(TC.get("Runmode").equals("N")){
    		test.log(LogStatus.SKIP,"Browser has not been opened..Because Runmode is NO");
    		throw new SkipException("Skip the Test because - Run mode is No");
    	}
    	start_Browser_Activation();
    	login=new Login_Page(dr, test);
        homepage=new Home_Page(dr, test);
        
    	// Now Doing Login with Method Do Login... It will Return Home Page Object.
    	homepage = login.doLogin(TC.get("user"), TC.get("Password"));
    	test.log(LogStatus.PASS, "Login Done After Logo verification");
    	
    	contactPage=homepage.Contact_Page_Open_Test();
    	test.log(LogStatus.PASS, "Contact Page open title verify");
    	screenshot();
	}
	
	
	@DataProvider
    public Object[][] getData(){
    	return ExcelDataReader.getDataReader(xls, TestSheetName, TestCase_Name);
    }
    
    @AfterMethod
    public void tearDown(){
    	ext.endTest(test);
    	ext.flush();
    	quit_Browser(5);
    }
}
