package com.intsol.school;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import classes.ConversacionAdapter;
import classes.Httppostaux;
import classes.Item_conversacion;
import classes.SessionManager;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ConversacionFragment extends Fragment {

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState){
  
		View mContainer = inflater.inflate(R.layout.list_conversacion, null);
		return mContainer;
    }
	
	
	ArrayList<Item_conversacion> my_conversaciones;
	ListView lst_conversaciones;
	ConversacionAdapter my_adapter_list;
	Button btn_cargar;
	ProgressDialog pDialog;
	String IP_Server = "coupongo.com.mx/schoolpocket";
	String URL_connect = "http://" + IP_Server + "/sii/home/get_mje_by_id";
	Httppostaux post;
	SessionManager session;
	String no_control;
	

	public void onActivityCreated(Bundle savedInstanceState) {  
		super.onActivityCreated(savedInstanceState);
	    
		String id_conversacion = getArguments().getString("id_mje");   
		//Toast.makeText(getActivity().getApplicationContext(),id_conversacion, Toast.LENGTH_SHORT).show();
		 
		my_conversaciones = new ArrayList<Item_conversacion>();
		lst_conversaciones = (ListView)getActivity().findViewById(R.id.lst_conversacion);
		btn_cargar = (Button)getActivity().findViewById(R.id.btn_cargar_mensajes);
		
		post = new Httppostaux();
		
		session = new SessionManager(getActivity());
		HashMap<String, String> user_variables = session.getUserDetails();
        no_control = user_variables.get(SessionManager.KEY_no_de_control);
		
		
		new asynclogin().execute(id_conversacion,no_control);
	 }
	
	
	public boolean helper_post(String id_maestro,String id_alumno){
    	try{
    		int logstatus = -1;
  
        	ArrayList<NameValuePair> param2send =  new ArrayList<NameValuePair>();
        	
        	param2send.add(new BasicNameValuePair("txt_maestro",id_maestro));
        	param2send.add(new BasicNameValuePair("txt_alumno",id_alumno));
        		
        	JSONArray jdata = post.getservicedata(param2send, URL_connect);
        	
        	if(jdata != null && jdata.length() >  0){
        		try{	
        			
        			for(int x=0; x<jdata.length(); x++){
        				JSONObject json_data = jdata.getJSONObject(x);
        				my_conversaciones.add(new Item_conversacion(json_data.getString("mje"),json_data.getString("fecha"),"Sistema"));
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
    	String id_maestro,id_alumno;
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
    		id_alumno = param[1];
    		if(helper_post(id_maestro, id_alumno) == true){
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
    			   
    	        my_adapter_list= new ConversacionAdapter(getActivity(),my_conversaciones);
    	        lst_conversaciones.setAdapter(my_adapter_list);	
    	        
    	        
    	        lst_conversaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    	      	  @Override
    	      	  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
    	      		  Toast.makeText(getActivity().getApplicationContext(),"Fecha de recibido:\n " + my_conversaciones.get(position).getFecha() , Toast.LENGTH_SHORT).show();
    	      	  }
    	      	});
    	        
    	        btn_cargar.setOnClickListener(new OnClickListener(){

    				@Override
    				public void onClick(View arg0) {
    				 	
    				}
    	        });
    		}
    		else{
    			
    		}
    	}
 }
	
}
