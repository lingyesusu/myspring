/*package com.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.com.dgpost.oms_server.pojo.order.Ordernote;
import cn.com.dgpost.oms_server.pojo.order.pack.Pack;
import cn.com.dgpost.oms_server.pojo.order.pack.PackPick;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

*//**
 * <p>
 * 描述：oms工程中的Java类[PDFUtils]
 * </p>
 * 
 * 类描述：PDF操作工具类
 * 
 * @version 1.0
 **//*
public class PDFUtils {

	public static void main(String[] args) throws IOException {
		try {
			List<InputStream> pdfs = new ArrayList<InputStream>();
			pdfs.add(new FileInputStream("d:\\1.pdf"));
			pdfs.add(new FileInputStream("d:\\2.pdf"));
			OutputStream output = new FileOutputStream("d:\\merge.pdf");
			concatPDFs(pdfs, output, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static  void img2Pdf(String imagePath, String pdfPath) throws Exception{
		BufferedImage img = ImageIO.read(new File(imagePath));
		FileOutputStream fos = new FileOutputStream(pdfPath);
		Document doc = new Document(null, 0, 0, 0, 0);
		doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
		Image image = Image.getInstance(imagePath);
		PdfWriter.getInstance(doc, fos);
		doc.open();
		doc.add(image);
		doc.close();
		fos.close();
	}
	
	
	*//**
	 * 创建PDF，单独创建PDF文件，做参考
	 * 
	 * @param outputStream
	 * @param dto
	 * @throws DocumentException
	 * @throws IOException
	 *//*
	public static void createPackPickPDF(OutputStream os, Pack pack)
			throws DocumentException, IOException {
		Document document = new Document(new Rectangle(283.465F, 283.465F),
				17.007999F, 17.007999F, 17.173F, 14.173F);
		// 添加文档元数据信息
		document.addTitle("oms-label");
		document.addSubject("oms_erp");
		document.addAuthor("oms_erp");
		document.addCreator("oms");
		document.addKeywords("pdf oms");
		// 创建PDF文件
		PdfWriter writer = PdfWriter.getInstance(document, os);
		if (!document.isOpen()) {
			document.open();
		} else {
			document.newPage();
		}

		// 定义字体及字体大小
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
				"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		//Font BoldChinese = new Font(bfChinese, 12, Font.BOLD); // 粗体
		Font BoldChinese = new Font(bfChinese, 14, Font.BOLD); // 粗体
		Font ht10 = new Font(bfChinese, 10.0F);
		// Font ht11 = new Font(bfChinese, 11.0F);

		PdfContentByte pdfcb = writer.getDirectContentUnder();
		pdfcb.rectangle(2F, 2F, 278F, 278F);
		pdfcb.stroke();

		Barcode128 barcode = new Barcode128();
		barcode.setCode(pack.getPackageNumber());
		Image txm = barcode.createImageWithBarcode(pdfcb, null, null);
		txm.scalePercent(100.0F);

		// 波次号及包裹号条码
		PdfPTable table = new PdfPTable(2);
		table.setTotalWidth(270F);
		table.setLockedWidth(true);
		table.getDefaultCell().setBorderWidth(0);
		table.getDefaultCell().setPadding(0.0F);

		PdfPCell cell1 = new PdfPCell(new Phrase(pack.getTrackingNumber()
				+ "\n" + pack.getOrder().getPlat().getPlatName(), BoldChinese));
		// pack.getPicking().getPickingCode() + "\n" +
		cell1.setPadding(0.0F);
		cell1.setBorder(0);
		table.addCell(cell1);

		PdfPCell cell2 = new PdfPCell();
		cell2.setPadding(0.0F);
		cell2.setBorder(0);
		cell2.setFixedHeight(40F);
		cell2.addElement(txm);
		table.addCell(cell2);
		table.completeRow();
		document.add(table);

		// ----------------------------------
		PdfPTable table2 = new PdfPTable(4);
		table2.setTotalWidth(270F);
		table2.setLockedWidth(true);
		table2.getDefaultCell().setBorderWidth(0);
		table2.getDefaultCell().setPadding(0.0F);
		table2.setSplitLate(false);// 跨页处理
		table2.setSplitRows(true);

		PdfPCell cell3 = new PdfPCell(new Phrase("货架", ht10));
		cell3.setPaddingTop(0);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell3);

		PdfPCell cell4 = new PdfPCell(new Phrase("SKU", ht10));
		cell4.setPaddingTop(0);
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell4);

		PdfPCell cell5 = new PdfPCell(new Phrase("数量", ht10));
		cell5.setPaddingTop(0);
		cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell5);

		PdfPCell cell6 = new PdfPCell(new Phrase("商品", ht10));
		cell6.setPaddingTop(0);
		cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell6);

		for (PackPick pick : pack.getPicklist()) {

			// 该包裹的拣货信息列表
			PdfPCell pc = new PdfPCell(new Phrase(pick.getRack() != null ? pick
					.getRack().getRackName() : "", ht10));
			pc.setPaddingTop(0);
			pc.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(pc);

			PdfPCell ac = new PdfPCell(new Phrase(pick.getProduct().getSku(),
					ht10));
			ac.setPaddingTop(0);
			ac.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(ac);

			PdfPCell cc = new PdfPCell(new Phrase(
					pick.getQuantity().toString(), ht10));
			cc.setPaddingTop(0);
			cc.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cc);

			PdfPCell cf = new PdfPCell(new Phrase(pick.getProduct()
					.getProductName(), ht10));
			cf.setPaddingTop(0);
			cf.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cf);
		}
		document.add(table2);

		float[] widths = {0.25f, 0.75f};
		PdfPTable table3 = new PdfPTable(widths);// 订单留言
		table3.setPaddingTop(10F);
		table3.setTotalWidth(270F);
		table3.setLockedWidth(true);
		table3.getDefaultCell().setBorderWidth(0);
		table3.getDefaultCell().setPadding(0.0F);
		table3.setSplitLate(false);// 跨页处理
		table3.setSplitRows(true);

		PdfPCell cell7 = new PdfPCell(new Phrase("留言类型", ht10));
		cell7.setPaddingTop(0);
		
		cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.addCell(cell7);
		PdfPCell cell8 = new PdfPCell(new Phrase("留言内容", ht10));
		cell8.setPaddingTop(0);
		cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
		table3.addCell(cell8);
		if (pack.getOrder().getNotes().size() > 0) {	
			for (Ordernote note : pack.getOrder().getNotes()) {
				// 该包裹的拣货信息列表
				PdfPCell pc = new PdfPCell(new Phrase(note.getNoteType()
						.equals("Service") ? "销售" : "留言", ht10));
				pc.setPaddingTop(0);
				pc.setHorizontalAlignment(Element.ALIGN_CENTER);
				table3.addCell(pc);

				PdfPCell ac = new PdfPCell(new Phrase(note.getContent(), ht10));
				ac.setPaddingTop(0);
				ac.setHorizontalAlignment(Element.ALIGN_CENTER);
				table3.addCell(ac);

			}
		}
		document.add(table3);

		document.close();
		// os.flush();
		os.close();
	}
	
	*//**
	 * 创建总的拣货单PDF
	 * 
	 * @param outputStream
	 * @param dto
	 * @throws DocumentException
	 * @throws IOException
	 *//*
	public static void createTotalPackPickPDF(OutputStream os, List<Map<String, Object>> packPicks, String code)
			throws DocumentException, IOException {
		Document document = new Document(new Rectangle(283.465F, 283.465F),
				17.007999F, 17.007999F, 17.173F, 14.173F);
		// 添加文档元数据信息
		document.addTitle("oms-label");
		document.addSubject("oms_erp");
		document.addAuthor("oms_erp");
		document.addCreator("oms");
		document.addKeywords("pdf oms");
		// 创建PDF文件
		PdfWriter writer = PdfWriter.getInstance(document, os);
		if (!document.isOpen()) {
			document.open();
		} else {
			document.newPage();
		}
		
		// 定义字体及字体大小
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
				"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//		Font BoldChinese = new Font(bfChinese, 12, Font.BOLD); // 粗体
		Font BoldChinese = new Font(bfChinese, 14, Font.BOLD); // 粗体
		Font ht10 = new Font(bfChinese, 10.0F);
		// Font ht11 = new Font(bfChinese, 11.0F);
		
		PdfContentByte pdfcb = writer.getDirectContentUnder();
		pdfcb.rectangle(2F, 2F, 278F, 278F);
		pdfcb.stroke();
		
		// 波次号
		PdfPTable table = new PdfPTable(1);
		table.setTotalWidth(270F);
		table.setLockedWidth(true);
		table.getDefaultCell().setBorderWidth(0);
		table.getDefaultCell().setPadding(0.0F);
		
		PdfPCell cell0 = new PdfPCell(new Phrase(code+"\n\n", BoldChinese));
		cell0.setPadding(0.0F);
		cell0.setBorder(0);
		cell0.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell0);
		table.completeRow();
		document.add(table);
		
		// ----------------------------------
		PdfPTable table1 = new PdfPTable(4);
		table1.setTotalWidth(270F);
		table1.setLockedWidth(true);
		table1.getDefaultCell().setBorderWidth(0);
		table1.getDefaultCell().setPadding(0.0F);
		table1.setSplitLate(false);// 跨页处理
		table1.setSplitRows(true);
		
		PdfPCell cell1 = new PdfPCell(new Phrase("货架", ht10));
		cell1.setPaddingTop(0);
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(cell1);
		
		PdfPCell cell2 = new PdfPCell(new Phrase("SKU", ht10));
		cell2.setPaddingTop(0);
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(cell2);
		
		PdfPCell cell3 = new PdfPCell(new Phrase("数量", ht10));
		cell3.setPaddingTop(0);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(cell3);
		
		PdfPCell cell4 = new PdfPCell(new Phrase("商品", ht10));
		cell4.setPaddingTop(0);
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		table1.addCell(cell4);
		
		for(Map<String, Object> packPick : packPicks){
			
			// 该包裹的拣货信息列表
			PdfPCell pc = new PdfPCell(new Phrase(packPick.get("RACK_NAME") != null ? packPick.get("RACK_NAME").toString() : "", ht10));
			pc.setPaddingTop(0);
			pc.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(pc);
			
			PdfPCell ac = new PdfPCell(new Phrase(packPick.get("SKU").toString(), ht10));
			ac.setPaddingTop(0);
			ac.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(ac);
			
			PdfPCell cc = new PdfPCell(new Phrase(packPick.get("QUANTITY").toString(), ht10));
			cc.setPaddingTop(0);
			cc.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cc);
			
			PdfPCell cf = new PdfPCell(new Phrase(packPick.get("PRODUCT_NAME").toString(), ht10));
			cf.setPaddingTop(0);
			cf.setHorizontalAlignment(Element.ALIGN_CENTER);
			table1.addCell(cf);
		}
		document.add(table1);
		
		document.close();
		os.close();
	}

	*//**
	 * 插入拣货信息
	 * 
	 * @param document
	 * @param writer
	 * @throws DocumentException
	 * @throws IOException
	 *//*
	public static void newPageExt(Document document, PdfWriter writer, Pack pack)
			throws DocumentException, IOException {
		if (!document.isOpen()) {
			document.open();
		} else {
			document.newPage();
		}

		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
				"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//		Font BoldChinese = new Font(bfChinese, 12, Font.BOLD); // 粗体
		Font BoldChinese = new Font(bfChinese, 14, Font.BOLD); // 粗体
		Font ht10 = new Font(bfChinese, 10.0F);
		Font ht11 = new Font(bfChinese, 11.0F);

		PdfContentByte pdfcb = writer.getDirectContentUnder();
		pdfcb.rectangle(3F, 3F, 277F, 277F);
		pdfcb.stroke();

		Barcode128 barcode = new Barcode128();
		barcode.setCode(pack.getPackageNumber());
		Image txm = barcode.createImageWithBarcode(pdfcb, null, null);
		txm.scalePercent(100.0F);

		// 波次号及包裹号条码
		PdfPTable table = new PdfPTable(2);

		table.setTotalWidth(270F);
		table.setLockedWidth(true);
		table.getDefaultCell().setBorderWidth(0);
		table.getDefaultCell().setPadding(0.0F);

		PdfPCell cell1 = new PdfPCell(new Phrase(pack.getPicking()
				.getPickingCode() + "\n" + pack.getTrackingNumber(),
				BoldChinese));
		cell1.setPadding(0.0F);
		cell1.setBorder(0);
		table.addCell(cell1);

		PdfPCell cell2 = new PdfPCell();
		cell2.setPadding(0.0F);
		cell2.setBorder(0);
		cell2.setFixedHeight(40F);
		cell2.addElement(txm);
		table.addCell(cell2);
		table.completeRow();
		document.add(table);

		// ----------------------------------
		PdfPTable table2 = new PdfPTable(4);
		table2.setTotalWidth(270F);
		table2.setLockedWidth(true);
		table2.getDefaultCell().setBorderWidth(0);
		table2.getDefaultCell().setPadding(0.0F);
		table2.setSplitLate(false);// 跨页处理
		table2.setSplitRows(true);

		PdfPCell cell3 = new PdfPCell(new Phrase("货架", ht11));
		cell3.setPaddingTop(0);
		cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell3);

		PdfPCell cell4 = new PdfPCell(new Phrase("SKU", ht11));
		cell4.setPaddingTop(0);
		cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell4);

		PdfPCell cell5 = new PdfPCell(new Phrase("数量", ht11));
		cell5.setPaddingTop(0);
		cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell5);

		PdfPCell cell6 = new PdfPCell(new Phrase("商品", ht11));
		cell6.setPaddingTop(0);
		cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
		table2.addCell(cell6);

		for (PackPick pp : pack.getPicklist()) {
			// 该包裹的拣货信息列表
			PdfPCell pc = new PdfPCell(new Phrase(pp.getRack() != null ? pp
					.getRack().getRackName() : "", ht10));
			pc.setPaddingTop(0);
			pc.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(pc);

			PdfPCell ac = new PdfPCell(new Phrase(pp.getProduct().getSku(),
					ht10));
			ac.setPaddingTop(0);
			ac.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(ac);

			PdfPCell cc = new PdfPCell(new Phrase(pp.getQuantity().toString(),
					ht10));
			cc.setPaddingTop(0);
			cc.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cc);

			PdfPCell cf = new PdfPCell(new Phrase(pp.getProduct()
					.getProductName(), ht10));
			cf.setPaddingTop(0);
			cf.setHorizontalAlignment(Element.ALIGN_CENTER);
			table2.addCell(cf);
		}
		document.add(table2);
	}

	*//**
	 * PDF合并操作，实际应用
	 * 
	 * @param pdfIn
	 * @param packs
	 * @param outputStream
	 * @param paginate
	 * @param pageType
	 *//*
	public static void concatPDFs3(InputStream pdfIn, List<Pack> packs,
			OutputStream outputStream, int pageType) {
		Document document = null;
		try {
			PdfReader pdfReader = new PdfReader(pdfIn);
			document = new Document(new Rectangle(283.465F, 283.465F),
					17.007999F, 17.007999F, 17.173F, 14.173F);
			// document = new Document(pdfReader.getPageSize(1));//创建一个文档
			PdfWriter writer = PdfWriter.getInstance(document, outputStream);// 获取PdfWriter实例
			document.open();// 打开文档

			PdfContentByte cb = writer.getDirectContent(); // Holds the PDF

			PdfImportedPage page;
			int currentPageNumber = 0;
			int pageOfCurrentReaderPDF = 0;
			int packIndex = 0;
			while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
				pageOfCurrentReaderPDF++;
				if (currentPageNumber % 2 == 0) {
					// FIXME 此处面单的顺序必须与包裹packs中的顺序一致！！
					newPageExt(document, writer, packs.get(packIndex));
					packIndex++;
				}
				currentPageNumber += pageType;
				currentPageNumber++;
				document.newPage();
				page = writer
						.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
				cb.addTemplate(page, 0, 0);
			}
			pageOfCurrentReaderPDF = 0;
			outputStream.flush();
			document.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (document != null && document.isOpen())
				document.close();
			try {
				if (outputStream != null)
					outputStream.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	*//**
	 * PDF文件合并
	 * 
	 * @param streamOfPDFFiles
	 * @param outputStream
	 * @param paginate
	 *//*
	public static void concatPDFs(List<InputStream> pdfs,
			OutputStream outputStream, boolean paginate) {
		Document document = null;
		try {
			List<PdfReader> readers = new ArrayList<PdfReader>();
			int totalPages = 0;
			Iterator<InputStream> iteratorPDFs = pdfs.iterator();

			// Create Readers for the pdfs.
			while (iteratorPDFs.hasNext()) {
				InputStream pdf = iteratorPDFs.next();
				PdfReader pdfReader = new PdfReader(pdf);
				readers.add(pdfReader);
				totalPages += pdfReader.getNumberOfPages();
			}
			document = new Document(readers.get(0).getPageSize(1));// 创建一个文档
			PdfWriter writer = PdfWriter.getInstance(document, outputStream);// 获取PdfWriter实例
			document.open();// 打开文档
			BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
					BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
			// data

			PdfImportedPage page;
			int currentPageNumber = 0;
			int pageOfCurrentReaderPDF = 0;
			Iterator<PdfReader> iteratorPDFReader = readers.iterator();

			// Loop through the PDF files and add to the output.
			while (iteratorPDFReader.hasNext()) {
				PdfReader pdfReader = iteratorPDFReader.next();

				// Create a new page in the target for each source page.
				while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
					document.newPage();
					pageOfCurrentReaderPDF++;
					currentPageNumber++;
					page = writer.getImportedPage(pdfReader,
							pageOfCurrentReaderPDF);
					cb.addTemplate(page, 0, 0);

					// Code for pagination.
					if (paginate) {
						cb.beginText();
						cb.setFontAndSize(bf, 9);
						cb.showTextAligned(PdfContentByte.ALIGN_CENTER, ""
								+ currentPageNumber + " of " + totalPages, 20,
								5, 0);

						cb.endText();
					}
				}
				pageOfCurrentReaderPDF = 0;
			}
			outputStream.flush();
			document.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (document != null && document.isOpen())
				document.close();
			try {
				if (outputStream != null)
					outputStream.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	*//**
	 * PDF文件合并
	 * 
	 * @param streamOfPDFFiles
	 * @param outputStream
	 * @param paginate
	 *//*
	public static void concatPDFs2(List<InputStream> streamOfPDFFiles,
			OutputStream outputStream, boolean paginate) {

		Document document = null;
		try {
			List<InputStream> pdfs = streamOfPDFFiles;
			List<PdfReader> readers = new ArrayList<PdfReader>();
			int totalPages = 0;
			Iterator<InputStream> iteratorPDFs = pdfs.iterator();

			// Create Readers for the pdfs.
			while (iteratorPDFs.hasNext()) {
				InputStream pdf = iteratorPDFs.next();
				PdfReader pdfReader = new PdfReader(pdf);
				readers.add(pdfReader);
				totalPages += pdfReader.getNumberOfPages();
			}
			// Create a writer for the outputstream
			document = new Document(readers.get(0).getPageSize(1));
			PdfWriter writer = PdfWriter.getInstance(document, outputStream);

			document.open();
			BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
					BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			// BaseFont _baseFont = BaseFont.createFont("STSongStd-Light",
			// "UniGB-UCS2-H", true);
			PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
			// data

			PdfImportedPage page;
			int currentPageNumber = 0;
			int pageOfCurrentReaderPDF = 0;
			Iterator<PdfReader> iteratorPDFReader = readers.iterator();

			// Loop through the PDF files and add to the output.
			while (iteratorPDFReader.hasNext()) {
				PdfReader pdfReader = iteratorPDFReader.next();

				// Create a new page in the target for each source page.
				while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
					document.newPage();
					pageOfCurrentReaderPDF++;
					currentPageNumber++;
					page = writer.getImportedPage(pdfReader,
							pageOfCurrentReaderPDF);
					cb.addTemplate(page, 0, 0);

					// Code for pagination.
					if (paginate) {
						cb.beginText();
						cb.setFontAndSize(bf, 9);
						cb.showTextAligned(PdfContentByte.ALIGN_CENTER, ""
								+ currentPageNumber + " of " + totalPages, 520,
								5, 0);

						cb.endText();
					}
				}
				pageOfCurrentReaderPDF = 0;
			}
			outputStream.flush();
			document.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (document.isOpen())
				document.close();
			try {
				if (outputStream != null)
					outputStream.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	*//**
	 * 合并 ，做参考
	 * 
	 * @param files
	 * @param newfile
	 * @return
	 *//*
	public static void mergePdfFiles(Object[] files, OutputStream os) {
		Document document = null;
		try {
			document = new Document(
					new PdfReader((String) files[0]).getPageSize(1));
			PdfCopy copy = new PdfCopy(document, os);
			document.open();
			for (int i = 0; i < files.length; i++) {
				PdfReader reader = new PdfReader((String) files[i]);
				int n = reader.getNumberOfPages();
				for (int j = 1; j <= n; j++) {
					document.newPage();
					PdfImportedPage page = copy.getImportedPage(reader, j);
					copy.addPage(page);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
	}
}
*/