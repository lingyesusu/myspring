package com.tools.encrypt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 顺丰俄罗斯 verifyCode 生成方法
 *
 */
public class MD5Util {

	public final static String MD5(String pwd) {
		return MD5(pwd, "UTF-8");
	}
	
    public final static String MD5(String pwd, String encoding) {
        //用于加密的字�?
        char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };

        try {
            //使用平台的默认字符集将此 String 编码�?byte序列，并将结果存储到�?��新的 byte数组�?
            byte[] btInput = pwd.getBytes(encoding);

            // 获得指定摘要算法�?MessageDigest对象，此处为MD5
            //MessageDigest类为应用程序提供信息摘要算法的功能，�?MD5 �?SHA 算法�?
            //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值�?
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            //System.out.println(mdInst);

            //MD5 Message Digest from SUN, <initialized>
            //MessageDigest对象通过使用 update方法处理数据�?使用指定的byte数组更新摘要
            mdInst.update(btInput);

            //System.out.println(mdInst);

            //MD5 Message Digest from SUN, <in progress>
            // 摘要更新之后，�?过调用digest（）执行哈希计算，获得密�?
            byte[] md = mdInst.digest();

            //System.out.println(md);

            // 把密文转换成十六进制的字符串形式
            int j = md.length;

            //System.out.println(j);

            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {   //  i = 0
                byte byte0 = md[i];  //95
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
                str[k++] = md5String[byte0 & 0xf];   //   F
            }
            
            //返回经过加密后的字符�?
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String digest(String toVerifyText) {
    	return digest(toVerifyText, "UTF-8");
    }
    
    public static String digest(String toVerifyText, String encode) {
        String base64Str = null;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(toVerifyText.getBytes(encode));
            byte[] md = md5.digest();
            
            base64Str = org.apache.commons.codec.binary.Base64.encodeBase64String(md);
//          base64Str=getURLEncoder(base64Str,encode);
            //base64Str = new String(new BASE64Encoder().encode(md));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return base64Str;
    }

    // url编码
    public static String getURLEncoder(String urlCode, String encode) {
        try {
            urlCode = URLEncoder.encode(urlCode, encode);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return urlCode;
    }

    // url解码
    public static String getURLDecoder(String urlCode, String encode) {
        try {
//          String a=new String(org.apache.commons.codec.binary.Base64.decodeBase64(urlCode.getBytes(encode)),encode);
//          System.out.println("a=="+a);
            urlCode = URLDecoder.decode(urlCode, encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return urlCode;
    }



    public static void main(String[] args) {
        System.out.println(MD5Util.MD5("EO000002@SF@aaaaaa", "UTF-8"));// E4B581AE09F8E4F4200F5EBD69CFBEDE
        
        System.out.println(MD5Util.MD5("E1234@SF@abcd", "UTF-8"));
        
        System.out.println(MD5Util.MD5("13082902317TM" + "@SF@aaaaaa", "UTF-8"));
        
        System.out.println(MD5Util.MD5("755DCA" + "@" + "20131011" + "@SF@aaaaaa", "UTF-8"));
        
        System.out.println(MD5Util.MD5("select * from t_depot" + "@SF@aaaaaa", "UTF-8"));
        
        try {
            System.out.println(URLEncoder.encode("select * from t_depot", "UTF-8"));
            System.out.println(URLEncoder.encode("select top 1 * from t_cp_bill where billno='131024002'", "UTF-8"));
            System.out.println(URLEncoder.encode("select * from T_CP_bill b where b.transtype = 'I' and DepotID='SystemNO00000006' and clientid='SystemNO00000001' and b.INTERFACE_FLAG = '0'", "UTF-8"));
            System.out.println(URLEncoder.encode("select * from t_cp_bill b inner join t_cp_item_log c on b.billid=c.billid where b.billno='131025001' and b.transtype='I'", "UTF-8"));
            System.out.println(URLEncoder.encode("select * from t_cp_item_log where billid='SystemNO00104128'", "UTF-8"));
            
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {
            Date now = new Date();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String today = sdf1.format(now);
            Date d1 = sdf2.parse(today + " 1:00:00");
            Date d2 = sdf2.parse(today + " 6:00:00");
            
            if(now.after(d1) && now.before(d2)) {
                System.out.println("=====================");
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {
            String xml = "<wmsSailOrderPushInfo> <header> <company>70765309-9</company> <warehouse>020DCE</warehouse> <erp_order>SC14010200532</erp_order> <shipment_id>L-GDY020DCE140102996157</shipment_id> <waybill_no>688558407941</waybill_no> <actual_ship_date_time>2014-01-03 01:35:08</actual_ship_date_time> <carrier>顺丰速运</carrier> <carrier_service>标准快�?</carrier_service> <user_def3>000000000000</user_def3> <user_def4>0000000000562949953421312</user_def4> <user_def5>1690579-001</user_def5> <user_def6>18</user_def6> <user_def7>13.0</user_def7> <user_def20>0.0000</user_def20> <user_stamp>135425</user_stamp> <status_time>2014-01-03 01:35:08</status_time> <status_code>900</status_code> <status_remark>您的订单己出库，正发�?��拣中�?/status_remark> </header> <detailList> <item> <item>0220100139</item> <quantity>1.0</quantity> <quantity_um>�?/quantity_um> <user_def1>小米-红米</user_def1> <user_def4>手机商城</user_def4> <user_def7>1</user_def7> </item> </detailList> <containerList> <item> <container_id>688558407941</container_id> <item>0220100139</item> <quantity>1.0</quantity> <weight>0.0</weight> <serialNumberList> <serial_number>863388026072749</serial_number> </serialNumberList> </item> <item> <container_id>688558407941</container_id> <quantity>1.0</quantity> <weight>1.0</weight> <weight_um>KG</weight_um> </item> </containerList> </wmsSailOrderPushInfo>";
            String checkword = "6b5303636ff742c69fae32d556098163";
            String dig = MD5Util.digest(xml + checkword, "UTF-8");
            
            String ss = "logistics_interface="+URLEncoder.encode(xml, "UTF-8")+"&data_digest="+URLEncoder.encode(dig, "UTF-8");
            
            System.out.println(ss);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        try {
            //File file = new File("E:/SF/LP_send_xml_example.txt");
            File file = new File("E:/SF/111.txt");
            //File file = new File("E:/SF/SF_express_events.xml");
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8") );
            
            StringBuffer sb = new StringBuffer();
            String str = null;
            while((str = in.readLine()) != null) {
                if(str != null) {
                    sb.append(str.trim());
                    //System.out.println(str);
                }
            }
            in.close();
            
            String xml = sb.toString();
            String checkword = "123456";
            String dig = MD5Util.digest(xml + checkword, "UTF-8");
            
            String ss = "logistics_interface="+URLEncoder.encode(xml, "UTF-8")+"&data_digest="+URLEncoder.encode(dig, "UTF-8");
            
            System.out.println(ss);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            String xml = "select * from lt_order_send";
            String checkword = "DBTest123";
            String dig = MD5Util.MD5(xml + "@SF@" + checkword, "UTF-8");
            
            String ss = "sql="+URLEncoder.encode(xml, "UTF-8")+"&checkword="+URLEncoder.encode(dig, "UTF-8");
            
            System.out.println(ss);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        String user = "admin";
        String password = "admin@123456";
        String md5 = MD5Util.MD5(password + "@SF@" + user, "UTF-8");
        
        System.out.println(md5);
    }

}

