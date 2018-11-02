
package com.wondersgroup.framework.utils;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientUtil {
	
	private static Logger logger = Logger.getLogger(HttpClientUtil.class);
	
	private static CloseableHttpClient buildSSLCloseableHttpClient() throws Exception {
		
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			
			// 信任所有
			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				
				return true;
			}
		}).build();
		// ALLOW_ALL_HOSTNAME_VERIFIER:这个主机名验证器基本上是关闭主机名验证的,实现的是一个空操作，并且不会抛出javax.net.ssl.SSLException异常。
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[] {
		        "TLSv1"
		}, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}
	
	public static String doPost(String url, Map<String, String> map, String charset) {
		
		CloseableHttpClient buildSSLCloseableHttpClient = null;
		try {
			// 创建httpclient对象
			buildSSLCloseableHttpClient = buildSSLCloseableHttpClient();
			System.setProperty("jsse.enableSNIExtension", "false");
			// 创建post方式请求对象
			HttpPost httpPost = new HttpPost(url);
			// 装填参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if (map != null) {
				for (Entry<String, String> entry : map.entrySet()) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			// 设置参数到请求对象中
			if (nvps.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps, charset);
				httpPost.setEntity(entity);
			}
			
			HttpResponse response = buildSSLCloseableHttpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					String result = EntityUtils.toString(resEntity, "UTF-8");
					logger.debug(result);
					return result;
				}
			}
		} catch (Exception e) {
			logger.error("Exception in doPost: ", e);
			return null;
		} finally {
			if (buildSSLCloseableHttpClient != null) {
				try {
					buildSSLCloseableHttpClient.close();
				} catch (IOException e) {
					logger.error("IOException in doPost: ", e);
				}
			}
		}
		return null;
	}
	
	public static String doGet(String url, String charset) {
		
		CloseableHttpClient buildSSLCloseableHttpClient = null;
		try {
			buildSSLCloseableHttpClient = buildSSLCloseableHttpClient();
			System.setProperty("jsse.enableSNIExtension", "false");
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = buildSSLCloseableHttpClient.execute(httpGet);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					String result = EntityUtils.toString(resEntity, "UTF-8");
					logger.debug(result);
					return result;
				}
			}
		} catch (Exception e) {
			logger.error("Exception in doGet: ", e);
			return null;
		} finally {
			if (buildSSLCloseableHttpClient != null) {
				try {
					buildSSLCloseableHttpClient.close();
				} catch (IOException e) {
					logger.error("IOException in doGet: ", e);
				}
			}
		}
		return null;
	}
	
}
