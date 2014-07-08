package com.intsol.school;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import classes.Httppostaux;
import classes.Item_mensajes;
import classes.MensajesAdaptar;
import classes.SessionManager;
import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

public class MensajesFragment extends Fragment {

	ProgressDialog pDialog;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState){
  
		View mContainer = inflater.inflate(R.layout.mensajes, null);
		return mContainer;
    }
	
    ImageView imageView;
    ListView lista_imgs;
    ArrayList<Item_mensajes> list_mensajes;
    MensajesAdaptar my_adapter_list;
    JSONArray jdata;
    
    
    String IP_Server = "coupongo.com.mx/schoolpocket";
	String URL_connect = "http://" + IP_Server + "/sii/home/get_lista_mjes";
	Httppostaux post;
	
	
	SessionManager session;
	String no_control;
	 public void onActivityCreated(Bundle savedInstanceState) {  
	        super.onActivityCreated(savedInstanceState);
	        
	        imageView = (ImageView)getActivity().findViewById(R.id.imageView1);
	        lista_imgs = (ListView)getActivity().findViewById(R.id.lista_mensajes);
	        list_mensajes = new ArrayList<Item_mensajes>();
	        
	        post =new Httppostaux();
	        
	        session = new SessionManager(getActivity());
			HashMap<String, String> user_variables = session.getUserDetails();
	        no_control = user_variables.get(SessionManager.KEY_no_de_control);
	        alertDialog = new AlertDialog.Builder(getActivity()).create();
			
			new async_mensajes().execute(no_control);
	 }
	 
	 static AlertDialog alertDialog;
	 @SuppressWarnings("deprecation")
	void error_dialog(){
		 alertDialog.setTitle("Error");
			alertDialog.setMessage("No se enconutro nungun mensaje.");
			alertDialog.setIcon(R.drawable.fulltime);
			
			// Setting OK Button
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			       
			        }
			});
			alertDialog.show();
	 }
	 
	 class async_mensajes extends AsyncTask <String, String, String>{
	    	String no_de_control;
	    	protected void onPreExecute(){
	    		pDialog =  new ProgressDialog(getActivity());
	    		pDialog.setMessage("Cargando...");
	    		pDialog.setIndeterminate(false);
	    		pDialog.setCancelable(false);
	    		pDialog.show();
	    	}
	    	
	    	@Override
	    	protected String doInBackground(String... param) {
	    		
	    		no_de_control = param[0];
	    		
	    		if(conexion_http(no_de_control) == true){
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
    		        
    		        my_adapter_list= new MensajesAdaptar(getActivity(),list_mensajes);
    		        lista_imgs.setAdapter(my_adapter_list);	
    		        
    		        
    		        lista_imgs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    		      	  @Override
    		      	  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
    		      		  
    		      		  // intent triggered, you can add other intent for other actions
    		              Intent intent = new Intent(getActivity(), MainActivity.class);
    		              
    		              // Pass data to the new activity
    		              intent.putExtra("flag_notif", true);
    		              intent.putExtra("from_mje", list_mensajes.get(position).getId_mensaje());
    		              
    		              startActivity(intent);
    		      		  
    		      		  
    		      		  //Toast.makeText(getActivity().getApplicationContext(),  list_mensajes.get(position).getId_mensaje() , Toast.LENGTH_LONG).show();
    		      	  }
    		      	});
	    		}
	    		else{
	    			error_dialog();
	    		}
	    	}
	    	
	    	public  boolean conexion_http(String no_de_control){
		    	try{
		        	ArrayList<NameValuePair> param2send =  new ArrayList<NameValuePair>();
		        	param2send.add(new BasicNameValuePair("no_de_control",no_de_control));
		        		
		        	jdata = post.getservicedata(param2send, URL_connect);
		        	if(jdata != null && jdata.length() >  0){
		        		for(int x=0; x<jdata.length(); x++){
		        			JSONObject fila_mensajes = jdata.getJSONObject(x);
		        			
		        			String id_mje = fila_mensajes.getString("from_mje");
		        			String url_foto = fila_mensajes.getString("from_mje");
		        			String mje = fila_mensajes.getString("mje");
		        			String Nombre_maestro = fila_mensajes.getString("nombre");
		        			
		        			list_mensajes.add(new Item_mensajes(id_mje,url_foto,mje,Nombre_maestro));
		        		}
		        		
		        		Log.e("JSON","Success");
		        		return true;
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
	    	
	    	
	 }
}
