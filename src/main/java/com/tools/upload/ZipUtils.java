package com.tools.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

/**
 * <p>描述：oms工程中的Java类[ZipUtil]</p>
 * 类描述：文件压缩
 *
 * @version 1.0
 **/
public class ZipUtils {
	
	private static final String CHINESE_CHARSET = "GBK";
	private static final int CACHE_SIZE = 1024;

    public static void unzip(File zipFile, String unzipFilePath) throws Exception
    {
        ZipFile zipfile = new ZipFile(zipFile);
        Enumeration<?> enu = zipfile.getEntries();
        while(enu.hasMoreElements())
        {
            ZipEntry entry = (ZipEntry)enu.nextElement();
            writeToDir(zipfile, entry, new File(unzipFilePath + File.separator + entry.getName()));

        }
    }

    public static void unzip(String zipFilePath,String unzipFilePath) throws Exception
    {
        ZipFile zipfile = new ZipFile(zipFilePath, "GB2312");
        Enumeration<?> enu = zipfile.getEntries();
        while(enu.hasMoreElements())
        {
            ZipEntry entry = (ZipEntry)enu.nextElement();
            writeToDir(zipfile, entry, new File(unzipFilePath + File.separator + entry.getName()));

        }
    }

    private static void writeToDir(ZipFile zip, ZipEntry entry, File toFile) throws Exception
    {
        if (!entry.isDirectory()) {
            File file = toFile.getParentFile();
            file.mkdirs();
            FileOutputStream fos = new FileOutputStream(toFile);
            byte[] buffer = new byte[1024];
            InputStream is = zip.getInputStream(entry);
            int len;
            while((len = is.read(buffer,0,buffer.length)) != -1)
            {
                fos.write(buffer,0,len);
            }
            fos.close();
        }
    }
    
    /**
     * <p>
     * 压缩文件
     * </p>
     * 
     * @param sourceFolder 压缩文件夹
     * @param zipFilePath 压缩文件输出路径
     * @throws Exception
     */
    public static void zip(String sourceFolder, String zipFilePath) throws Exception {
        OutputStream out = new FileOutputStream(zipFilePath);
        BufferedOutputStream bos = new BufferedOutputStream(out);
        ZipOutputStream zos = new ZipOutputStream(bos);
        zos.setEncoding(CHINESE_CHARSET);
        File file = new File(sourceFolder);
        String basePath = null;
        if (file.isDirectory()) {
            basePath = file.getPath();
        } else {
            basePath = file.getParent();
        }
        zipFile(file, basePath, zos);
        zos.closeEntry();
        zos.close();
        bos.close();
        out.close();
    }
    
    /**
     * <p>
     * 递归压缩文件
     * </p>
     * 
     * @param parentFile
     * @param basePath
     * @param zos
     * @throws Exception
     */
    private static void zipFile(File parentFile, String basePath, ZipOutputStream zos) throws Exception {
        File[] files = new File[0];
        if (parentFile.isDirectory()) {
            files = parentFile.listFiles();
        } else {
            files = new File[1];
            files[0] = parentFile;
        }
        String pathName;
        InputStream is;
        BufferedInputStream bis;
        byte[] cache = new byte[CACHE_SIZE];
        for (File file : files) {
            if (file.isDirectory()) {
                pathName = file.getPath().substring(basePath.length() + 1) + File.separator;
                zos.putNextEntry(new ZipEntry(pathName));
                zipFile(file, basePath, zos);
            } else {
                pathName = file.getPath().substring(basePath.length() + 1);
                is = new FileInputStream(file);
                bis = new BufferedInputStream(is);
                zos.putNextEntry(new ZipEntry(pathName));
                int nRead = 0;
                while ((nRead = bis.read(cache, 0, CACHE_SIZE)) != -1) {
                    zos.write(cache, 0, nRead);
                }
                bis.close();
                is.close();
            }
        }
    }

}
