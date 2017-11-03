package com.tools.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tools.GlobalConstants;
import com.tools.PathUtils;
import com.tools.UploadDTO;
import com.tools.time.DateUtils;

/**
 * <p>描述：oms_server工程中的Java类[UploadUtils]</p>
 * 类描述：文件上传工具类
 *
 * @version 1.0
 **/
public class UploadUtils {

	/**
	 * 上传文件
	 * @param fileMap
	 * @param isTemp 是否是临时保存
	 * @return
	 * @throws IOException
	 */
	public static String uploadTempFile(Map<String, MultipartFile> fileMap) throws IOException{
		
		String date = DateUtils.formatDate(new Date(),"yyyyMMdd");
		File target = new File(PathUtils.UPLOAD_PATH + "/upload/temp/" + date);
		
		//检查该目录是否存在
		if(!target.exists()){
			target.mkdirs();
		}
		
		String fileName = "";
		String imgpath = "";//图片文件所在目录
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {    
			String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");// 返回一个随机UUID用于重命名文件
			MultipartFile mf = entity.getValue(); //获取当前文件
			//以下是生成文件名和缩略图文件名
			fileName = mf.getOriginalFilename(); //获取文件名
			String suffix = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf("."), fileName.length()) : "";
			String newFileName = uuid + suffix;// 构成新文件名。
			File uploadFile = new File(target.getPath()+File.separator+newFileName);//保存源文件
		  
			//保存原图片
			FileCopyUtils.copy(mf.getBytes(), uploadFile);
			//
			imgpath = PathUtils.UPLOAD_ROOT + getWebPath(target.getPath()+File.separator+uploadFile.getName());
		}
		//System.out.println(imgpath +" --" +getWebPath(target.getPath()));
		return imgpath;
	}
	
	/**
	 * 根据临时文件生成图片
	 * @param srcPath
	 * @param productId
	 * @throws IOException
	 */
	public static UploadDTO transFile(String srcPath,Long productId) throws IOException{
		
		File srcFile = new File(PathUtils.UPLOAD_PATH + srcPath.replace(PathUtils.UPLOAD_ROOT, ""));
		
		//图片目的路径
		File target= new File(PathUtils.UPLOAD_PATH + "/upload/product/"+productId);
		if(!target.exists()){
			target.mkdirs();
		}
		
		String fileName = "", tbFileName = "";
		if (srcFile.isFile() && srcFile.exists()) {
			fileName = srcFile.getName();
			tbFileName = "tb_"+fileName;
			//目的图片文件
			File targetFile = new File(target.getPath()+File.separator+fileName);//保存源文件
			File smallFile = new File(target.getPath()+File.separator+tbFileName);//缩略图文件
			FileCopyUtils.copy(srcFile, targetFile);//将temp文件夹的文件复制到SPU_IMG文件夹下
			//生成文件缩略图
			PicUtils.compress(srcFile, smallFile,true,
					GlobalConstants.THUMBS_WIDTH, GlobalConstants.THUMBS_HEIGHT);//等比缩放生成缩略图
			
			srcFile.delete();//删除temp文件
			
			String imgUrl = PathUtils.UPLOAD_ROOT + getWebPath(target.getPath()+File.separator+targetFile.getName());
			String thumbUrl = PathUtils.UPLOAD_ROOT + getWebPath(target.getPath()+File.separator+tbFileName);
			
			return new UploadDTO(imgUrl, thumbUrl);
		}else{
			return null;
		}
	}

	/**
	 * web 地址
	 * @param realPath
	 * @return
	 */
	public static String getWebPath(String realPath) {
		// 进行转换 使得\ 转换为/
		String path = realPath.replaceAll("\\\\", "/");
		// 去掉实际地址目录 ,加上web地址目录
		return path.replace(PathUtils.UPLOAD_PATH, "");
	}

	/**
	 * 根据图片保存路径删除图片文件
	 * @param imgUrl
	 * @param thumUrl
	 */
	public static void deleteImg(String imgUrl,String thumUrl) {
		//删除原图
		File srcfile = new File(PathUtils.UPLOAD_PATH + imgUrl.replace(PathUtils.UPLOAD_ROOT, ""));
		if (srcfile.isFile() && srcfile.exists()) {
			srcfile.delete();
		}
  		//删除缩略图
  		if(StringUtils.isNotBlank(thumUrl)){
  			File thumbnail = new File(PathUtils.UPLOAD_PATH + thumUrl.replace(PathUtils.UPLOAD_ROOT, ""));
  	  		if (thumbnail.isFile() && thumbnail.exists()) {
  	  			thumbnail.delete();
  	  		}
  		}
	}
	
	/**
	 * 创建文件目录
	 * @param fileName
	 * @return
	 */
	public static File makeFile(String fileName){
		//String date = DateUtils.formatDate(new Date(),"yyyyMMdd");
		File file = new File(PathUtils.UPLOAD_PATH + "/upload/temp/" + fileName);
		if(!file.exists()){
			file.mkdirs();
		}
		return file;
	}
	
	/**
	 * 根据 url 下载图片并保存到本地
	 * @param urlString 图片链接地址
	 * @param filename 图片文件名
	 * @param savePath 保存路径
	 * @throws Exception
	 */
    public static void download(String urlString, String filename,String savePath) throws Exception {  
        // 构造URL  
        URL url = new URL(urlString);  
        // 打开连接  
        URLConnection con = url.openConnection();  
        //设置请求超时为5s  
        con.setConnectTimeout(5*1000);  
        // 输入流  
        InputStream is = con.getInputStream();  
      
        // 1K的数据缓冲   为什么要设置数据缓冲？设置多大为佳？
        byte[] bs = new byte[1024];  
        // 读取到的数据长度  
        int len;  
        // 输出的文件流  
       File sf=new File(savePath);  
       if(!sf.exists()){  
           sf.mkdirs();  
       }  
       OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);  
        // 开始读取  
        while ((len = is.read(bs)) != -1) {  
          os.write(bs, 0, len);  
        }  
        // 完毕，关闭所有链接  
        os.close();  
        is.close();  
    }  
}