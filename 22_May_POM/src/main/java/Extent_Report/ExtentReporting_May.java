package Extent_Report;

import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReporting_May {
	public static ExtentReports ext;
	public static ExtentReports getReporting(){
		if(ext==null){
			Date d=new Date();
			String FN=d.toString().replace(" ", "_").replace(":", "_")+".html";
	        ext=new ExtentReports("D://report//"+FN,true,DisplayOrder.NEWEST_FIRST);
	        ext.addSystemInfo("QA Testing", "Manoj Kushwaha").
	        addSystemInfo("Environment ", "172.19.70.194");
	}
		return ext;
  }
	

}
