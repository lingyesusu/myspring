package com.tools.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

public class ExcelUtils {

	/**
	 * 基于模版的Excel导出
	 * @param exportName
	 * @param template
	 * @param params
	 * @param response
	 * @throws Exception
	 */
	public static void export(String exportName, String template, Map<String, Object> params, HttpServletResponse response) throws Exception {
		OutputStream os = null;
		InputStream is = null;
		try {
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(exportName.getBytes("UTF-8"), "iso8859-1"));;
			os = response.getOutputStream();
			is = new FileInputStream(new File(template));
            Context context = new Context();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
            	context.putVar(entry.getKey(), entry.getValue());
    		}
            JxlsHelper.getInstance().processTemplate(is, os, context);
			os.flush();
		} catch (Exception e) {
			throw new Exception("导出出错：" + e.getMessage());
		} finally {
			os.close();
			is.close();
		}
	}
	
	/**
	 * 基于SXSSFWorkbook的Excel导出
	 * @param fileName
	 * @param data 表格数据，封装成SXSSFWorkbook
	 * @param response
	 * @throws Exception 
	 */
	public static void export(String fileName, SXSSFWorkbook data, HttpServletResponse response) throws Exception{
		try {
			response.setContentType("application/vnd.ms-excel");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gbk"), "iso8859-1"));		
			OutputStream os = response.getOutputStream();
			SXSSFWorkbook workBook = new SXSSFWorkbook(100);
			try {
				workBook = data;
			} catch (Exception e) {
				//e.printStackTrace();
				throw new Exception("导出出错：" + e.getMessage());
			}
			workBook.write(os);
			os.flush();
			os.close();
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new Exception("导出出错：" + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("导出出错：" + e.getMessage());
		}
	}
	
}