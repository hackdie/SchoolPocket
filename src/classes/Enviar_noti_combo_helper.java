package classes;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;

public class Enviar_noti_combo_helper extends AsyncTask <String, String, String>{
	

	
	Button btn_siguiente;
	JSONArray jdata;
	
	String IP_Server = "coupongo.com.mx/schoolpocket";
	String URL_connect = "http://" + IP_Server + "/sii/home/send_notif";
	Httppostaux post;
	
	protected void onPreExecute(){
		post =new Httppostaux();
		
	}
	
	@Override
	protected String doInBackground(String... param) {	
		String tipo_notif,desintatario,emisor,txt_mje;
		tipo_notif = param[0];
		desintatario = param[1];
		emisor = param[2];
		txt_mje = param[3];
		
		if(insert_respuestas(tipo_notif,desintatario,emisor,txt_mje) == true){
			return "ok";
		}
		else{
			return "err";
		}
	}
	
	protected void onPostExecute(String result){
		Log.e("onPostExecute=",""+result);
		if(result.equals("ok")){
		}
		else{
		}
	}
	
	 public  boolean insert_respuestas(String txt_tipo_notig,String txt_destinatario,String txt_emisor,String txt_mensaje){
    	try{
        	ArrayList<NameValuePair> param2send =  new ArrayList<NameValuePair>();
        	param2send.add(new BasicNameValuePair("tipo_notif",txt_tipo_notig));
        	param2send.add(new BasicNameValuePair("destinatario",txt_destinatario));
        	param2send.add(new BasicNameValuePair("emisro",txt_emisor));
        	param2send.add(new BasicNameValuePair("txt_mensaje",txt_mensaje));
        		
        	jdata = post.getservicedata(param2send, URL_connect);
        	
        	if(jdata != null && jdata.length() >  0){
        		Log.e("JSON","Success"+ jdata);
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
