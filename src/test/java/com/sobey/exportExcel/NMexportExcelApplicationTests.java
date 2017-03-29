package com.sobey.exportExcel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sobey.exportExcel.model.ExcelModel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NMexportExcelApplicationTests {

	@Test
	public void contextLoads() {
//		/Users/lijunhong/Desktop/NMExportExcel
		ExcelModel excelModel = new ExcelModel("1.xls","/Users/lijunhong/Desktop/NMExportExcel");
		excelModel.createExcelTableHeader();
	}

}
