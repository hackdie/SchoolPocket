package com.IntelligentSolutions.schoolpocketdocente.settings;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class Httppostaux {
	InputStream is = null;
	String result = "";
	
	public JSONArray getservicedata(ArrayList<NameValuePair> parametrs, String urlwebservice){
		try{
			Httppostconnect(parametrs,urlwebservice);
			
			if(is!=null){
				getpostresponse();
				return getjsonarray();
			}
			else{
				return null;
			}
		}catch(Exception ex){
			Log.e("Erro", "Getservicedata_error= " +ex.toString());
			return null;
		}
	}

	private JSONArray getjsonarray() {
		try{
			JSONArray jArray = new JSONArray(result);
			return jArray;
			
		}catch(JSONException jex){
			Log.e("log_er","Error tratando de pasar datos a JSON "+ jex.toString());
			return null;
		}
	}

	private void getpostresponse() {
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while((line = reader.readLine()) != null){
				sb.append(line+"\n");
			}
			is.close();
			result = sb.toString();
			Log.e("getpostresponse","result="+sb.toString());
		}
		catch(Exception ex){
			Log.e("Log_tag","Error convirtiendo JSON "+ex.toString());
		}
	}

	private void Httppostconnect(ArrayList<NameValuePair> parametrs,String urlwebservice) {
		try{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost =  new HttpPost(urlwebservice);
			httppost.setEntity(new UrlEncodedFormEntity(parametrs));
			
			//Ejecutamos peticion para mandar datos por post
			
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		}
		catch(Exception ex){
			Log.e("log_tag","Error in http connection " + ex.toString());
		}
	}
}
