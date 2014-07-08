package com.intsol.school;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import classes.Enviar_noti_combo_helper;
import classes.Httppostaux;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SendMessageFragment extends Fragment {

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState){
  
		View mContainer = inflater.inflate(R.layout.enviar_mje, null);
		return mContainer;
    }
	
	private ProgressDialog pDialog;
	String IP_Server = "coupongo.com.mx/schoolpocket";
	String URL_connect = "http://" + IP_Server + "/sii/home/get_lista_horario_by_maestro";
	Httppostaux post;
	Spinner spinner2;
	List<String> list;
	ArrayAdapter<String> dataAdapter;
	Button btn_lista;
	
	 public void onActivityCreated(Bundle savedInstanceState) {  
	        super.onActivityCreated(savedInstanceState);
	        
	        post =new Httppostaux();
	        spinner2 = (Spinner) getActivity().findViewById(R.id.cmbo_grupos);
	        list = new ArrayList<String>();
	        btn_lista = (Button)getActivity().findViewById(R.id.btn_mjes_grupos);
	       
	        new asynclogin().execute("");
	        
	 }
	 
	 public boolean loginstatus(String id_maestro){
	    	try{
	    		int logstatus = -1;
	  
	        	ArrayList<NameValuePair> param2send =  new ArrayList<NameValuePair>();
	        	
	        	param2send.add(new BasicNameValuePair("id_maestro",id_maestro));
	        		
	        	JSONArray jdata = post.getservicedata(param2send, URL_connect);
	        	
	        	if(jdata != null && jdata.length() >  0){
	        		try{	
	        	    	for(int x=0; x<jdata.length(); x++){
	        	    		JSONObject json_data = jdata.getJSONObject(x);
	        	    		list.add(json_data.getString("horario"));
	        	    	}
	        			//accedemos al valor
	        			logstatus = 1; //cambiar
	        			
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
	    	String id_maestro;
	    	protected void onPreExecute(){
	    		pDialog =  new ProgressDialog(getActivity());
	    		pDialog.setMessage("Autenticando...");
	    		pDialog.setIndeterminate(false);
	    		pDialog.setCancelable(false);
	    		pDialog.show();
	    	}
	    	
	    	@Override
	    	protected String doInBackground(String... param) {
	    		
	    		id_maestro = param[0];
	    		
	    		if(loginstatus(id_maestro) == true){
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
	    			dataAdapter = new ArrayAdapter<String>(getActivity(),
    	    	    		android.R.layout.simple_spinner_item, list);
	    	    	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    	    	spinner2.setAdapter(dataAdapter);
	    	    	
	    	    	
	    	    	btn_lista.setOnClickListener(new OnClickListener(){
	    	    		@Override
	    				public void onClick(View arg0) {
	    	    			String id_materia = String.valueOf(spinner2.getSelectedItem());
	    	    			String id[] = id_materia.split("-");
	    	    			//Toast.makeText(getActivity(),id[2].trim(),Toast.LENGTH_SHORT).show();
	    	    			
	    	    			TextView txt_meje_combo = (TextView) getActivity().findViewById(R.id.txt_meje_combo);
	    	    			
	    	    			if(txt_meje_combo.getText().toString().trim().length() != 0  && id[2].toString().trim().length() !=0){
	    	    				new Enviar_noti_combo_helper().execute("2",id[2].trim(),"Hector Carlos",txt_meje_combo.getText().toString());
	    	    			}
	    	    			else{
	    	    				Toast.makeText(getActivity(),"Favor de rectificar datos",Toast.LENGTH_SHORT).show();
	    	    			}
	    	    			
	    	    			
	    				}
	    	    	});
	    		}
	    		else{
	    			
	    		}
	    	}
	 }
	
	
}
