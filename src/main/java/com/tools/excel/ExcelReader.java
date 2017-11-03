package com.tools.excel;

import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;

public class ExcelReader {

	/**
	 * 获取单元格值
	 * @param cell
	 * @return
	 */
	public static Object getStringCellValue(HSSFCell cell) {
		if (cell == null) {
			return "";
		}
		Object strCell = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			// 去掉前后空格
			strCell = cell.getStringCellValue() == null ? cell
					.getStringCellValue() : cell.getStringCellValue().trim();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = new DecimalFormat("#.##").format(cell
					.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = cell.getBooleanCellValue();
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			try {
				strCell = cell.getNumericCellValue();
				if (strCell.toString().equals("0.0")) {
					strCell = "";
				}
			} catch (IllegalStateException e) {
				strCell = cell.getRichStringCellValue();
			}
			break;
		default:
			strCell = cell.getStringCellValue();
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		return strCell;
	}

	/**
	 * 格式化字符串为整形格式
	 * @param number
	 * @return
	 */
	public static String getNumerValueCell(HSSFCell cell) {
		try {
			return new DecimalFormat("#0").format(Double.parseDouble(cell.toString().trim()));
		} catch (Exception e) {
			return "";
		}
	}
}