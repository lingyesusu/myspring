package com.tools.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.tools.DataRuntimeException;

/**
 * <p>描述：oms工程中的Java类[XSSFExport]</p>
 *
 * 类描述：POI处理EXCEL
 *
 * @version 1.0
 **/
public class XSSFExport {

	// 定制日期格式
	private static String DATE_FORMAT = " m/d/yy "; // "m/d/yy h:mm"

	private String xlsFileName;
	
	private SXSSFWorkbook workbook;

	private Sheet sheet;

	private Row row;
	
	CellStyle cellStyle; 
	DataFormat format;

	/**
	 * 初始化Excel
	 * @param fileName 导出文件名
	 */
	public XSSFExport (String fileName) {
		this.xlsFileName = fileName;
		this.workbook = new SXSSFWorkbook();
		this.sheet = workbook.createSheet();
		cellStyle = workbook.createCellStyle();
		format = workbook.createDataFormat();
	}
	
	public XSSFExport() {
		this.workbook = new SXSSFWorkbook();
		this.sheet = workbook.createSheet();
		cellStyle = workbook.createCellStyle();
		format = workbook.createDataFormat();
	}
	
	/**
	 * 导出Excel文件
	 * @throws XLSException
	 */
	public void exportXLS() throws DataRuntimeException {
		try {
			FileOutputStream fOut = new FileOutputStream(xlsFileName);
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
		} catch (FileNotFoundException e) {
			throw new DataRuntimeException(" 生成导出Excel文件出错! ");
		} catch (IOException e) {
			throw new DataRuntimeException(" 写入Excel文件出错! ");
		}

	}
	
	/** 增加一个工作表
	 * @return Sheet
	 */
	public Sheet createSheet() {
		this.sheet = this.workbook.createSheet();
		return this.sheet;
	}
	
	/** 获取工作薄
	 * @param index
	 * @return Sheet
	 */
	public Sheet getSheet(int index) {
		this.sheet = this.workbook.getSheetAt(index);
		return this.sheet;
	}
	
	/**
	 * 增加一行
	 * @param index  行号
	 */
	public void createRow(int index) {
		this.row = this.sheet.createRow(index);
		if(index == 0){
			this.row.setHeightInPoints((short) 20);
		}
	}
	
	/**
	 * 设置单元格
	 * @param index 列号
	 * @param value 单元格填充值
	 */
	public Cell setCell(int index, String value) {
		Cell cell = this.row.createCell((short) index);
		cell.setCellValue(value);
		return cell;
	}
	
	/**
	 * 建立公式
	 * @param index
	 * @return
	 */
	public Cell createFormulaCell(int index,String formula) {
		Cell cell = this.row.createCell((short) index);
		cell.setCellType(Cell.CELL_TYPE_FORMULA);
		cell.setCellFormula(formula);
		return cell;
	}
	
	/**
	 * 设置单元格
	 * @param index  列号
	 * @param value  单元格填充值
	 */
	public void setCell(int index, Calendar value) {
		Cell cell = this.row.createCell((short) index);
		cell.setCellValue(value.getTime());
		cellStyle.setDataFormat(format.getFormat(DATE_FORMAT)); // 设置cell样式为定制的日期格式
		cell.setCellStyle(cellStyle); // 设置该cell日期的显示格式
	}
	
	/**
	 * 设置单元格
	 * @param index 列号
	 * @param value 单元格填充值
	 */
	public void setCell(int index, int value) {
		Cell cell = this.row.createCell((short) index);
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
	}

	/**
	 * 设置单元格
	 * @param index 列号
	 * @param value 单元格填充值
	 */
	public void setCell(int index, double value) {
		Cell cell = this.row.createCell((short) index);
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
		//cellStyle.setDataFormat(format.getFormat(NUMBER_FORMAT)); // 设置cell样式为定制的浮点数格式
		cellStyle.setDataFormat(format.getFormat("@")); // 设置cell样式为定制的浮点数格式
		cell.setCellStyle(cellStyle); // 设置该cell浮点数的显示格式
	}
	
	/**
	 * 表头栏信息添加
	 * @param args
	 */
	public void setTableHeader(String[] args){
		int index = 0;
		for(int i = 0;i < args.length;i++){
			this.setCell(index++, args[i]);
		}
	}
	
	public Row getRow() {
		return row;
	}

	public void setRow(Row row) {
		this.row = row;
	}
	
	public SXSSFWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(SXSSFWorkbook workbook) {
		this.workbook = workbook;
	}
	public Sheet getSheet() {
		return sheet;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}
}
