package com.tools.string;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * <p>描述：oms_server工程中的Java类[IOUtils]</p>
 *
 * 类描述：IO流工具类
 *
 * @version 1.0
 **/
public class IOUtils {

	/**
	 * 全部读取
	 * 
	 * @param is
	 *            输入流
	 * @param charset
	 *            字符编码
	 * @return 输入流中的所有内容
	 * @throws IOException
	 */
	public static String dump(InputStream is, String charset)
			throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(is, charset));
			return org.apache.commons.io.IOUtils.toString(br);
		} finally {
			is.close();
			br.close();
		}
	}

	/**
	 * 计算总页数
	 * 
	 * @param totalCount
	 * @param pageSize
	 * @return
	 */
	public static Long getPageCount(Long totalCount, Long pageSize) {
		return (totalCount + pageSize - 1) / pageSize;
	}

	/**
	 * 借助字节输出流ByteArrayOutputStream来实现字节数组的合并
	 * 
	 * @param srcArrays
	 * @return
	 */
	public static byte[] streamCopy(List<byte[]> srcArrays) {
		byte[] destAray = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			for (byte[] srcArray : srcArrays) {
				bos.write(srcArray);
			}
			bos.flush();
			destAray = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
			}
		}
		return destAray;
	}

	/**
	 * byte数组转输入流
	 * 
	 * @param buf
	 * @return
	 */
	public static final InputStream byte2Input(byte[] buf) {
		return new ByteArrayInputStream(buf);
	}

	/**
	 * InputStream 转为byte数组
	 * 
	 * @param inStream
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	public static byte[] toByteArray(InputStream in) throws Exception {
		byte[] in2b = null;
		ByteArrayOutputStream os = null;
		try {
			os = new ByteArrayOutputStream();
			byte[] buff = new byte[1024];
			int rc = 0;
			while ((rc = in.read(buff, 0, 100)) > 0) {
				os.write(buff, 0, rc);
			}
			os.flush();
			in2b = os.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			try {
				in.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return in2b;
	}

	/**
	 * 文件转byte[]
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static byte[] getBytes(String filePath) throws Exception {
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		return toByteArray(fis);
	}

}
