package com.sesxh.hoschange.biz.sys.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.sesxh.hoschange.biz.sys.entity.AuthUserDetail;

public class ReadExcel {

	public List<AuthUserDetail> readXls(InputStream file) throws IOException {
		POIFSFileSystem fs = new POIFSFileSystem(file);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fs);

		List<AuthUserDetail> list = new ArrayList<AuthUserDetail>();
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 循环行Row
			for (int rowNum = 2; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					AuthUserDetail authUserDetail = new AuthUserDetail();
					HSSFCell department = hssfRow.getCell(1);
					HSSFCell jobnumber = hssfRow.getCell(2);
					HSSFCell userName = hssfRow.getCell(3);
					HSSFCell sex = hssfRow.getCell(4);
					HSSFCell clothesSize = hssfRow.getCell(5);
					HSSFCell shoesSize = hssfRow.getCell(6);
					authUserDetail.setDepartment(getValue(department));
					authUserDetail.setJobnumber(getValue(jobnumber));
					authUserDetail.setUserName(getValue(userName));
					authUserDetail.setSex(getValue(sex));
					authUserDetail.setClothesType(getValue(clothesSize));
					authUserDetail.setShoesType(getValue(shoesSize));
					list.add(authUserDetail);
				}
			}
		}
		return list;
	}

	@SuppressWarnings("static-access")
	private String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			// 返回布尔类型的值
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			// 返回string类型的值
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			// 返回int类型的值
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
}
