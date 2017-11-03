package com.tools.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class OmsRequest {
	
	public static final String WS_INNER_API = "http://cn.wizerspark.com/oms/register/innerApi";
	
	/**
	 * 核心请求方法
	 * @param request  请求中需要的参数对象
	 * @return 返回请求相应的字符串
	 * @throws IOException 
	 */
	public static String doRequest(OmsRequest request){
		String responseStr = "";
		HttpURLConnection connection =null;
		OutputStreamWriter out = null;
		BufferedReader reader  = null;
		try {
			// Post请求的url，与get不同的是不需要带参数
			URL url = new URL(request.getUrl());
			// 打开连接
			connection = (HttpURLConnection)url.openConnection();
			//设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在  http正文内，因此需要设为true, 默认情况下是false;  
			connection.setDoOutput(true);
			//设置是否从httpUrlConnection读入，默认情况下是true;  
			connection.setDoInput(true);
			connection.setConnectTimeout(50000);
			connection.setReadTimeout(90000);
			connection.setRequestMethod(request.getMethod());
			// Post 请求不能使用缓存  
			connection.setUseCaches(false);
			
			if(StringUtils.isNotBlank(request.getContentType())){
				connection.setRequestProperty("Content-type",request.getContentType());
			}
			
			// 设定传送的内容类型是可序列化的java对象  
			// (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)  
			//connection.setRequestProperty("Content-type","application/x-java-serialized-object");  
	
			// 以下这条如果不加会发现无论你设置Accept-Charset为gbk还是utf-8，他都会默认返回gb2312（本例针对google.cn来说）
			//connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");
			
			//keep-Alive，close (这里保持连接，因为后面还要发送数据到服务器)
			//connection.setRequestProperty("Connection", "keep-Alive");  
			//connection.setRequestProperty("accept", "*/*");
			
			// 设置头信息
			Map<String, String> requestHeadMap = request.getRequestHeadMap();
			if(requestHeadMap != null){
				Set<String> mapSet = requestHeadMap.keySet();
				for (String set : mapSet) {
					connection.setRequestProperty(set,(String) requestHeadMap.get(set));
				}
			}
			
			// 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，要注意的是connection.getOutputStream会隐含的进行connect。
			if (request.getMethod().equals(OmsRequest.METHOD_POST)) {
				out = new OutputStreamWriter(connection.getOutputStream(), request.getEncode());
				out.write(request.getContent());
				out.flush();
				//out.close();
			}
			
			// 调用HttpURLConnection连接对象的getInputStream()函数,将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。 
			
			// 获取相应
			String lineTemp = "";
			//设置ENCODE
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),request.getEncode())); //<===注意，实际发送请求的代码段就在这里  
			while ((lineTemp = reader.readLine()) != null) {
				if (!"".equals(lineTemp)) {
					responseStr += lineTemp + "\r\n";
				}
			}
			
		} catch (IOException e) {
			//e.printStackTrace();
			responseStr = e.getMessage();
		}finally{
			if (connection != null) {
				connection.disconnect(); //中断连接
			}
			try {
				if(out !=null){
					out.close();
				}
				if(reader!=null){
					reader.close();
				}
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
		return responseStr;
	}
	
	/**
	 * 核心请求方法
	 * @param request  请求中需要的参数对象
	 * @return 返回请求相应的字符串
	 * @throws IOException 
	 */
	public static InputStream doRequest2InputStream(OmsRequest request) throws IOException {
		// Post请求的url，与get不同的是不需要带参数
		URL url = new URL(request.getUrl());
		// 打开连接
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		//设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在  http正文内，因此需要设为true, 默认情况下是false;  
		connection.setDoOutput(true);
		//设置是否从httpUrlConnection读入，默认情况下是true;  
		connection.setDoInput(true);
		connection.setConnectTimeout(50000);
		connection.setReadTimeout(50000);
		connection.setRequestMethod(request.getMethod());
		// Post 请求不能使用缓存  
		connection.setUseCaches(false);
		
		// 设定传送的内容类型是可序列化的java对象  
		// (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)  
		//connection.setRequestProperty("Content-type","application/x-java-serialized-object");  

		// 设置头信息
		Map<String, String> requestHeadMap = request.getRequestHeadMap();
		if(requestHeadMap != null){
			Set<String> mapSet = requestHeadMap.keySet();
			for (String set : mapSet) {
				connection.setRequestProperty(set,(String) requestHeadMap.get(set));
			}
		}
		
		// 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，要注意的是connection.getOutputStream会隐含的进行connect。
		if (request.getMethod().equals(OmsRequest.METHOD_POST)) {
			OutputStreamWriter out 
				= new OutputStreamWriter(connection.getOutputStream(), request.getEncode());
			out.write(request.getContent());
			out.flush();
			out.close();
		}
		
		// 调用HttpURLConnection连接对象的getInputStream()函数,将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。 
		return connection.getInputStream();
	}
	
	/**
	 * 核心请求方法
	 * @param request  请求中需要的参数对象
	 * @return 返回请求相应的字符串
	 * @throws Exception 
	 * @throws IOException 
	 */
	public static InputStream doRequest2InputStream(String labelUrl) throws Exception{
		System.out.println("--"+labelUrl+"--");
		HttpURLConnection connection = null; //连接对象  
		// Post请求的url，与get不同的是不需要带参数
		URL url = new URL(labelUrl.toString());
		// 打开连接
		connection = (HttpURLConnection)url.openConnection();
		//设置是否从httpUrlConnection读入，默认情况下是true;  
		connection.setDoInput(true);
		connection.setConnectTimeout(20000);
		connection.setReadTimeout(20000);
		connection.setRequestMethod("GET");
		// Post 请求不能使用缓存  
		connection.setUseCaches(false);
		
		//-------------
		connection.setRequestProperty(  
				"Accept",
				"image/gif, image/jpeg, image/pjpeg, image/pjpeg, " +
				"application/x-shockwave-flash, application/xaml+xml, " +
				"application/vnd.ms-xpsdocument, application/x-ms-xbap, " +
				"application/x-ms-application, application/vnd.ms-excel, " +
				"application/vnd.ms-powerpoint, application/msword, */*");
		connection.setRequestProperty("Accept-Language", "zh-CN");
		connection.setRequestProperty("Charset", "UTF-8");
		//设置浏览器类型和版本、操作系统，使用语言等信息  
		connection.setRequestProperty(
					"User-Agent",
					"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.2; Trident/4.0; " +  
					".NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; " +  
					".NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");  
		//设置为长连接  
		connection.setRequestProperty("Connection", "Keep-Alive");
		
		//-------------
		if(connection.getResponseCode()!=200){
			throw new Exception("打印地址标签失败，第三方物流网络异常,请检查API授权情况.");
		}
		
		// 调用HttpURLConnection连接对象的getInputStream()函数,将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。 
		return connection.getInputStream();
	}
	
	/**
	 * 打印日志
	 * @param str
	 */
	public static void log(String str) {
		System.out.println(str);
	}

	/**
	 * @param url   请求的url
	 * @param method  请求的方法
	 */
	public OmsRequest(String url, String method) {
		this(url, method, "");
	}

	/**
	 * @param url  请求的url
	 * @param method  请求的方法
	 * @param content  请求体
	 */
	public OmsRequest(String url, String method, String content) {
		this(url, method, content, null);
	}
	
	/**
	 * 构造方法
	 * @param url  请求的url
	 * @param method  请求的方法
	 * @param content  请求体
	 * @param encode  请求使用的编码
	 */
	public OmsRequest(String url, String method, String content, Map<String,String> requestHeadMap) {
		this(url, method, content, requestHeadMap, "UTF-8");
	}

	/**
	 * @param url
	 * @param method
	 * @param requestHeadMap
	 */
	public OmsRequest(String url, String method,  Map<String,String> requestHeadMap) {
		this.url = url;
		this.method = method;
		this.encode = "UTF-8";
		this.requestHeadMap = requestHeadMap;
	}
	/**
	 * 构造方法
	 * @param url
	 * @param method
	 * @param content
	 * @param encode
	 * @param requestHeadMap setRequestProperty
	 */
	public OmsRequest(String url, String method, String content, Map<String,String> requestHeadMap, String encode) {
		this.url = url;
		this.method = method;
		this.content = content;
		this.encode = encode;
		this.requestHeadMap = requestHeadMap;
	}
	
	/**
     * POST方法
     */
	public static String METHOD_POST = "POST";

	/**
	 * get方法
	 */
	public static String METHOD_GET = "GET";

	/**
	 * delete方法
	 */
	public static String METHOD_DELETE = "DELETE";

	/**
	 * 请求的各个服务地址
	 */
	private String url = "";

	/**
	 * 请求的方法
	 */
	private String method = "";

	/**
	 * POST请求的请求体
	 */
	private String content = "";

	/**
	 * 编码
	 */
	private String encode = "";
	
	/**
	 * 请求头类型
	 */
	private String contentType="";

	/**
	 * 保存请求的头信息
	 */
	private Map<String,String> requestHeadMap = null;

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url  the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method   the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content  the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the encode
	 */
	public String getEncode() {
		return encode;
	}

	/**
	 * @param encode   the encode to set
	 */
	public void setEncode(String encode) {
		this.encode = encode;
	}

	/**
	 * @return the requestHeadMap
	 */
	public Map<String, String> getRequestHeadMap() {
		return requestHeadMap;
	}

	/**
	 * @param requestHeadMap  the requestHeadMap to set
	 */
	public void setRequestHeadMap(Map<String, String> requestHeadMap) {
		this.requestHeadMap = requestHeadMap;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	   /* 
		* 总结： 
		* HttpURLConnection的connect()函数，实际上只是建立了一个与服务器的tcp连接，并没有实际发送http请求。 
		* 无论是post还是get，http请求实际上直到HttpURLConnection的getInputStream()这个函数里面才正式发送出去。 
		*  
		* 对HttpURLConnection对象的一切配置都必须要在connect()函数执行之前完成。 
		* 而对outputStream的写操作，又必须要在inputStream的读操作之前。 
		* 这些顺序实际上是由http请求的格式决定的。 
		*  
		* 在http头后面紧跟着的是http请求的正文，正文的内容是通过outputStream流写入的， 
		* 实际上outputStream不是一个网络流，充其量是个字符串流，往里面写入的东西不会立即发送到网络， 
		* 而是存在于内存缓冲区中，待outputStream流关闭时，根据输入的内容生成http正文。 
		* 至此，http请求的东西已经全部准备就绪。在getInputStream()函数调用的时候，就会把准备好的http请求 
		* 正式发送到服务器了，然后返回一个输入流，用于读取服务器对于此次http请求的返回信息。由于http 
		* 请求在getInputStream的时候已经发送出去了（包括http头和正文），因此在getInputStream()函数 
		* 之后对connection对象进行设置（对http头的信息进行修改）或者写入outputStream（对正文进行修改） 
		* 都是没有意义的了，执行这些操作会导致异常的发生。 
		*  
	*/  
	
	@SuppressWarnings("rawtypes")
	public static String getServerIp() {
		String SERVER_IP = "";
		try {
			Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) netInterfaces
						.nextElement();
				ip = (InetAddress) ni.getInetAddresses().nextElement();
				SERVER_IP = ip.getHostAddress();
				if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
						&& ip.getHostAddress().indexOf(":") == -1) {
					SERVER_IP = ip.getHostAddress();
					break;
				} else {
					ip = null;
				}
			}
		} catch (SocketException e) {
		}
		return SERVER_IP;
	}

	public static String getLocalIP() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		byte[] ipAddr = addr.getAddress();
		String ipAddrStr = "";
		for (int i = 0; i < ipAddr.length; i++) {
			if (i > 0) {
				ipAddrStr += ".";
			}
			ipAddrStr += ipAddr[i] & 0xFF;
		}
		// System.out.println(ipAddrStr);
		return ipAddrStr;
	} 
}
