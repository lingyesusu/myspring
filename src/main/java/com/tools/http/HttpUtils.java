package com.tools.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


/**
 * <p>描述 :【oms】工程中的Java类【HttpUtil.java】</p>

 * <p>功能概述 :http 协议，参考：http://acooly.iteye.com/blog/1513145</p>
 * @version V1.0
 */

public class HttpUtils {
	
	/**
	 * get方式
	 * @param httpUrl 请求地址
	 * @return
	 */
	public static String httpGet(String httpUrl) {
		String result = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        try {  
            // 创建httpget
            HttpGet httpget = new HttpGet(httpUrl);  
            //System.out.println("请求地址：" + httpget.getURI());  
            // 执行get请求.    
            CloseableHttpResponse response = httpclient.execute(httpget);  
            try {
                response.getStatusLine().getStatusCode();// 得到http的状态返回值
                result = EntityUtils.toString(response.getEntity());// 得到具体的返回值，一般是xml文件
                //System.out.println("请求结果：" + result);  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
        return result;
    }
	
    /**
     * post方式
     * @param httpUrl
     * @param data
     * @return
     */
    public static String httpPost(String httpUrl, List<NameValuePair> params) {  
    	String result = "";
        //创建默认的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        //创建httppost    
        HttpPost httppost = new HttpPost(httpUrl);  
        //创建参数队列    
        if(params == null){
        	params = new ArrayList<NameValuePair>();
        }
        //List<NameValuePair> params1 = new ArrayList<NameValuePair>();
        //params1.add(new BasicNameValuePair("exchange","CNY|USD"));
        //params1.add(new BasicNameValuePair("&count","100"));  
        UrlEncodedFormEntity uefEntity;  
        try {  
            uefEntity = new UrlEncodedFormEntity(params, "UTF-8");  
        	//uefEntity = new UrlEncodedFormEntity(null, "UTF-8");  
            httppost.setEntity(uefEntity);  
            System.out.println("executing request " + httppost.getURI());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                //HttpEntity entity = response.getEntity();  
                response.getStatusLine().getStatusCode();// 得到http的状态返回值
				result = EntityUtils.toString(response.getEntity());// 得到具体的返回值，一般是xml文件
				//System.out.println(result);
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return result;
    }      
    
    /**
	 * 发起https请求并获取结果
	 * 
	 * 1）解决https请求的问题，很多人问题就出在这里； 2）兼容GET、POST两种方式；
	 * 3）兼容有数据提交、无数据提交两种情况，也有相当一部分人不知道如何POST提交数据；
	 * 
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	public static String httpsRequest(String requestUrl, String requestMethod,
			String outputStr) throws NoSuchAlgorithmException,
			NoSuchProviderException, KeyManagementException, IOException {
		// JSONObject jsonObj = null;
		StringBuffer buffer = new StringBuffer();
		// 创建SSLContext对象，并使用我们指定的信任管理器初始化
		TrustManager[] tm = { new HttpsX509TrustManager() };
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		// 从上述SSLContext对象中得到SSLSocketFactory对象
		SSLSocketFactory sslSF = sslContext.getSocketFactory();

		URL url = new URL(requestUrl);
		HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
				.openConnection();
		httpUrlConn.setSSLSocketFactory(sslSF);

		httpUrlConn.setDoOutput(true);
		httpUrlConn.setDoInput(true);
		httpUrlConn.setUseCaches(false);
		httpUrlConn.setConnectTimeout(10000);
		httpUrlConn.setReadTimeout(10000);

		// 设置请求方式GET or POST
		httpUrlConn.setRequestMethod(requestMethod);

		if ("GET".equalsIgnoreCase(requestMethod)) {
			httpUrlConn.connect();
		}
		// 当有数据要提交时
		if (outputStr != null) {
			OutputStream outputStream = httpUrlConn.getOutputStream();
			// 注意编码格式，防止中文乱码
			outputStream.write(outputStr.getBytes("UTF-8"));
			outputStream.close();
		}

		// 将返回的输入流转换成字符串
		InputStream inputStream = httpUrlConn.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(
				inputStream, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String str = null;
		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
		}
		bufferedReader.close();
		inputStreamReader.close();
		// 释放资源
		inputStream.close();
		inputStream = null;
		httpUrlConn.disconnect();

		return buffer.toString();
	}
	
}
