package UI_Actions_Pages;

import basePOM.Base_Test_POM;

public class Contact_Page extends Base_Test_POM{
	public String getPageTile(){
		String pageTitle = dr.getTitle();
		return pageTitle;
	}
	
}
