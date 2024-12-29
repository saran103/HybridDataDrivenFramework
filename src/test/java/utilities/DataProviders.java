package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path = ".//testData//Opencart_LoginData.xlsx";
		ExcelUtils xlUtils = new ExcelUtils(path);
		
		int totalRows = xlUtils.getRowCount("Sheet1");
		int totalCols = xlUtils.getCellCount("Sheet1", 1);
		
		String loginData[][] = new String[totalRows][totalCols];
		
		for(int i=1; i<=totalRows; i++)
		{
			for(int j=0; j<totalCols; j++)
			{
				loginData[i-1][j] = xlUtils.getCellData("Sheet1", i, j);
			}
		}
		return loginData;
		
	}
	
	//DataProvider2
	
	//DataProvider3
	

}
