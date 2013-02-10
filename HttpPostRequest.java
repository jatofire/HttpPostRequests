package com.syntaxcandy.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class HttpPostRequest {
	
	//The HTTP client
	private HttpClient httpClient;
	//The Post val
	private HttpPost httpPost;
	
	//A list to hold the pais of data
	private List<NameValuePair> nameValuePair;
	
	StringBuilder total;
	
	public HttpPostRequest(String url) {
		httpClient = new DefaultHttpClient();
	    httpPost = new HttpPost(url);
	    nameValuePair = new ArrayList<NameValuePair>(2);
	}
	
	public void add(String name, String value)
	{
		nameValuePair.add(new BasicNameValuePair(name,value));
	}
	
	public void setEntity()
	{
		try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        }
        catch (UnsupportedEncodingException e) {
            // writing error to Log
            e.printStackTrace();
        }
	}
	
	public void execute()
	{
		
		InputStream test = null;
        String line = "";
        total = new StringBuilder();
        
		 try {
	            HttpResponse response = httpClient.execute(httpPost);
	         
	           test = response.getEntity().getContent();
	           // Wrap a BufferedReader around the InputStream
	           BufferedReader rd = new BufferedReader(new InputStreamReader(test));

	           // Read response until the end
	           while ((line = rd.readLine()) != null) { 
	               total.append(line); 
	           }
	         
	        } catch (ClientProtocolException e) {
	            // writing exception to log
	            e.printStackTrace();
	         
	        } catch (IOException e) {
	            // writing exception to log
	            e.printStackTrace();
	        }

	}
	
	public String getResponse(){
		return total.toString();
	}

}
