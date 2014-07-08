package com.intsol.school;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.android.gcm.GCMRegistrar;

import classes.Httppostaux;
import classes.SessionManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	Button btn_login;
	EditText no_de_control, pass;
	
	private ProgressDialog pDialog;
	
	String IP_Server = "coupongo.com.mx/schoolpocket";
	String URL_connect = "http://" + IP_Server + "/sii/home/prueba3";
	Httppostaux post;
	
	// Session Manager Class
	SessionManager session;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		
		btn_login = (Button)findViewById(R.id.btn_login);
		no_de_control = (EditText)findViewById(R.id.txt_no_de_control);
		pass = (EditText)findViewById(R.id.txt_pass);
		
		post =new Httppostaux();
		session = new SessionManager(getApplicationContext()); 
		
		GCMRegistrar.checkDevice(this);
        GCMRegistrar.checkManifest(this);
        GCMRegistrar.register(LoginActivity.this,
                GCMIntentService.SENDER_ID);
		
		
		btn_login.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				
				String User = no_de_control.getText().toString();
				String Pass = pass.getText().toString();
				
				if(val_datos(User,Pass)){
					
					GCMRegistrar.register(LoginActivity.this,
			                GCMIntentService.SENDER_ID);
					String txt_device = GCMRegistrar.getRegistrationId(LoginActivity.this);
					
					new asynclogin().execute(User,Pass,txt_device);
				}
				else{
					err_login();
				}
				
				/*Intent mainIntent = new Intent().setClass(
                		LoginActivity.this, MainActivity.class);
                startActivity(mainIntent);*/
				
			}
		});
	}
	
	public boolean val_datos(String user, String pass){	
    	if(user.equals("") || pass.equals("")){
    		return false;
    	}
    	else{
    		return true;
    	}
    }
	
	
	  //mje error
    public void err_login(){
    	Vibrator vibr = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    	vibr.vibrate(200);
    	Toast alert = Toast.makeText(getApplicationContext(), "Datos Incorrecto.", Toast.LENGTH_SHORT);
    	alert.show();
    }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	 public boolean loginstatus(String user, String pass,String id_device){
	    	try{
	    		int logstatus = -1;
	    		String nombre_completo,semestre, id_carrera;
	  
	        	ArrayList<NameValuePair> param2send =  new ArrayList<NameValuePair>();
	        	
	        	param2send.add(new BasicNameValuePair("no_de_control",user));
	        	param2send.add(new BasicNameValuePair("pass",pass));
	        	param2send.add(new BasicNameValuePair("id_device",id_device));
	        		
	        	JSONArray jdata = post.getservicedata(param2send, URL_connect);
	        	
	        	if(jdata != null && jdata.length() >  0){
	        		JSONObject json_data;
	        		try{
	        			//Leemos el 1er parametro q debe ser el unico
	        			json_data = jdata.getJSONObject(0);
	        			
	        			//accedemos al valor
	        			logstatus = 1; //cambiar
	        			nombre_completo = json_data.getString("nombre_completo");
	        			semestre = json_data.getString("ultimo_periodo_inscrito");
	        			id_carrera = json_data.getString("id_carrera");
	        			session.createLoginSession(user,nombre_completo,semestre,id_carrera);
	        			
	        			Log.e("loginstatus","loginstatus= " + logstatus);
	        		}catch(JSONException jex){
	        			Log.e("error_login","Error al conectar gon httppostaux " + jex.toString());
	        		}
	        		
	        		if(logstatus == 1){//[{logstatus:1}]
	        			Log.e("loginstatus","valido");
	        			return true;
	        		}
	        		else{
	        			Log.e("logstatus","invalido");
	        			return false;
	        		}
	        	}
	        	else{
	        		Log.e("JSON","Error");
	        		return false;
	        	}
	    	}
	    	catch(Exception ex){
	    		Log.e("Error","Error unknown = " + ex.toString() );
	    		return false;
	    	}
	    }
	
	
	 class asynclogin extends AsyncTask <String, String, String>{
	    	String user,pass,id_device;
	    	protected void onPreExecute(){
	    		pDialog =  new ProgressDialog(LoginActivity.this);
	    		pDialog.setMessage("Autenticando...");
	    		pDialog.setIndeterminate(false);
	    		pDialog.setCancelable(false);
	    		pDialog.show();
	    	}
	    	
	    	@Override
	    	protected String doInBackground(String... param) {
	    		
	    		user = param[0];
	    		pass = param[1];
	    		id_device = param[2];
	    		
	    		if(loginstatus(user,pass,id_device) == true){
	    			return "ok";
	    		}
	    		else{
	    			return "err";
	    		}
	    	}
	    	
	    	protected void onPostExecute(String result){
	    		pDialog.dismiss();
	    		Log.e("onPostExecute=",""+result);
	    		if(result.equals("ok")){
	    			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			        startActivity(intent);
			        finish();
	    		}
	    		else{
	    			err_login();
	    		}
	    	}
	 }

}
