package com.ddim.happygo.util;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.ddim.happygo.web.admin.specialDealer.SpecialDealerForm;

public class NewExcelUtils {
	
	public static void export(HttpServletResponse response, String fileName, List<List<Object>> sheetDataList) {
		try {
		    // 宣告XSSFWorkbook
			Workbook wb = new XSSFWorkbook();
		    // 建立分頁
		    Sheet sheet = wb.createSheet("工作表");
		    // 宣告列物件
		    Row row = null;
		    // 宣告表格物件
		    Cell cell = null;
		    
		    
		    for (int rowIndex = 0; rowIndex<sheetDataList.size(); rowIndex++) {
		    	
		        // 建立新的一列
		        row = sheet.createRow(rowIndex);
		            for (int i = 0; i < sheetDataList.get(rowIndex).size(); i++) {
		                cell = row.createCell(i);
		                // 塞值到表格內
		                cell.setCellValue(sheetDataList.get(rowIndex).get(i).toString());
		            }
		    }
		    // 設定檔案輸出串流到指定位置
		    FileOutputStream fileOut = new  FileOutputStream("/Users/user1/Downloads/" + fileName  +".xlsx");
		    wb.write(fileOut);
		    fileOut.close();

		} catch (Exception e) {
		    e.printStackTrace();
		}
    }
	
	public static <T> List<T> readMultipartFile(MultipartFile mFile, Class<T> clazz) throws Exception {
        
        return new ArrayList<T>();
    }

}
