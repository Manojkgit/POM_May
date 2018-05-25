package basePOM;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Constat_Util.Constant_Util_Class;
import Excel_Util_Reader.Xls_Reader;
import Extent_Report.ExtentReporting_May;

public class Base_Test_POM {
	
	public WebDriver dr;
	public Properties pro;
	public ExtentReports ext=ExtentReporting_May.getReporting();
	public ExtentTest test;
	public Xls_Reader xls;
	public Logger log=Logger.getLogger(Base_Test_POM.class.getName());
	
	public Base_Test_POM(){
		init();
		PropertyConfigurator.configure(pro.getProperty("LogFile_Path"));
		xls=new Xls_Reader(pro.getProperty("ExcelPath"));
	}
	
	public Base_Test_POM(WebDriver dr,ExtentTest test){
		this.dr=dr;
		this.test=test;
	}
	
	public void init(){
		if(pro==null){
			pro=new Properties();
			try{
				FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\property_Pack\\or.properties");
				pro.load(fis);
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
				System.out.println(pro.getProperty("appurl"));
		}
	}
	
	public void wait(int s){
		try{
			Thread.sleep(s*1000);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public void swithFrame(){
		dr.switchTo().frame(1);
	}
	
	public void start_Browser_Activation(){
		openBro(pro.getProperty("Browser"));
    	test.log(LogStatus.INFO,"Browser has been opened..");
    	navigate(pro.getProperty("appurl"));
    	screenshot();
	}
	
	public void openBro(String b){
		if(b.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "D://Browser_exe//chromedriver.exe");
			dr=new ChromeDriver();
		}else{
			dr=new FirefoxDriver();
		}
		dr.manage().window().maximize();
		dr.manage().deleteAllCookies();
		dr.manage().timeouts().implicitlyWait(Constant_Util_Class.Implicitily_Time, TimeUnit.SECONDS);
		//dr.manage().timeouts().pageLoadTimeout(Constant_Util_Class.PageLoad_TimeOut, TimeUnit.SECONDS);
	}
	
	public void screenshot(){
		Date d=new Date();
		String FN=d.toString().replace(" ", "_").replace(":", "_")+".jpg";
		File srcFile = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		try{
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"//report//"+FN));
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		log.info("Screenshot capture by Screenshot Method Log Enter in Report File.");
		test.log(LogStatus.INFO,"Take Screenshot - > " + test.addScreenCapture(System.getProperty("user.dir")+"//report//"+FN));
	}
	
	public void navigate(String url){
		dr.get(url);
	}
 
    public void quit_Browser(int s){
    	try{
    		Thread.sleep(s*1000);
    		dr.quit();
    	}catch(Exception ex){
    		System.out.println(ex.getMessage());
    	}
    }

}
