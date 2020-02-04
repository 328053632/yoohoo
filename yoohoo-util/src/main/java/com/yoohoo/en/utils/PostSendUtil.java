package com.yoohoo.en.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * HTTP发送工具类
 * 
 * @author Administrator
 * 
 */
public class PostSendUtil {

	/**
	 * 发送json数据
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static boolean sendJsonObjData(String postUrl,String json) throws Exception {
	    HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(postUrl);
		StringEntity s = new StringEntity(json,"UTF-8");
		s.setContentType("application/json;charset=UTF-8");
		s.setContentEncoding("UTF-8");
		post.setEntity(s);
		HttpResponse response = httpClient.execute(post);
		/*InputStream in = response.getEntity().getContent();
		 BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));  
         StringBuffer sb = new StringBuffer();  
         String str = ""; 
         while ((str = reader.readLine()) != null)  
         {  
             sb.append(str).append("\n");  
         }  
         System.out.println("======================");
         System.out.println(sb);
         System.out.println("=======================");*/
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 发送json数据
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static boolean sendJsonData(String postUrl,JSONArray json) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost post = new HttpPost(postUrl);
		StringEntity s = new StringEntity(json.toString());
		s.setContentEncoding("UTF-8");
		s.setContentType("application/json");
		post.setEntity(s);
		CloseableHttpResponse response = httpclient.execute(post);
		/*InputStream in = response.getEntity().getContent();
		 BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));  
         StringBuffer sb = new StringBuffer();  
         String str = ""; 
         while ((str = reader.readLine()) != null)  
         {  
             sb.append(str).append("\n");  
         }  
         System.out.println("======================");
         System.out.println(sb);
         System.out.println("=======================");*/
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 发送json数据
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static String test(JSONObject json) throws Exception {
		String respJson = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost postMethod = new HttpPost("http://114.215.199.19/cloudspd-crm/flowapi/order_uflowOrder.do");
		LogMgr.getInstance().infoLog(PostSendUtil.class, "sendGDTelecomeData reqJson==========>>>"+json.toString());
		StringEntity s = new StringEntity(json.toString());
		s.setContentEncoding("UTF-8");
		s.setContentType("application/json");
		postMethod.setEntity(s);
		CloseableHttpResponse response = httpclient.execute(postMethod);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			HttpEntity resEntity = response.getEntity();
			if (resEntity != null) {
				respJson = EntityUtils.toString(resEntity, "UTF-8");
			}
			return respJson;
		}
		return respJson;
	}
}

