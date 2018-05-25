package Excel_Util_Reader;

import java.util.Hashtable;

public class ExcelDataReader {
	public static Object[][] getDataReader(Xls_Reader xls,String sName,String TC){
		String sheetName=sName;
		int testStart=1;
		while(!xls.getCellData(sheetName, 0, testStart).equals(TC)){
			testStart++;
		}System.out.println(testStart);
		
		int row=0;
		int rowStart=testStart+2;
		int colStart=testStart+1;
		while(!xls.getCellData(sheetName, 0, rowStart+row).equals("")){
			row++;
		}System.out.println(row);
		
		int col=0;
		while(!xls.getCellData(sheetName, col, colStart).equals("")){
			col++;
		}System.out.println(col);
		
		Object[][] data=new Object[row][1];
		int RD=0;
		Hashtable<String,String> tb=null;
		for(int r=rowStart;r<rowStart+row;r++){
			tb=new Hashtable<String,String>();
			for(int c=0;c<col;c++){
				String key=xls.getCellData(sheetName, c, colStart);
				String value = xls.getCellData(sheetName, c, r);
				tb.put(key, value);
			}
			data[RD][0]=tb;
			RD++;
		}
		return data;
	}

}
