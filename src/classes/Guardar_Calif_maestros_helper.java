package classes;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import com.intsol.school.PreguntasFragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Guardar_Calif_maestros_helper extends AsyncTask <String, String, String>{
	
	
	CheckBox editText[];
	static int id_pregunta = 0;
	TextView txt_pregunta;
	LinearLayout linearLayout;
	String[] array_preguntas;
	int pregunta_actual,tot_preguntas,tot_maestros;
	int matr_preguntas [][];
	String [] nombres;
	String vect_id_maestro[];
	Button btn_siguiente;
	JSONArray jdata;
	
	String IP_Server = "coupongo.com.mx/schoolpocket";
	String URL_connect = "http://" + IP_Server + "/sii/home/prueba";
	Httppostaux post;
	
	protected void onPreExecute(){
		post =new Httppostaux();
		
		PreguntasFragment.mje_alert();
	}
	
	@Override
	protected String doInBackground(String... param) {	
		String json;
		json = param[0];
		if(insert_respuestas(json) == true){
			return "ok";
		}
		else{
			return "err";
		}
	}
	
	protected void onPostExecute(String result){
		Log.e("onPostExecute=",""+result);
		if(result.equals("ok")){
			PreguntasFragment.mje_cerrar();
			PreguntasFragment.mje_correcto();
		}
		else{
			PreguntasFragment.mje_error();
		}
	}
	
	 public  boolean insert_respuestas(String query_json){
    	try{
        	ArrayList<NameValuePair> param2send =  new ArrayList<NameValuePair>();
        	param2send.add(new BasicNameValuePair("query_json",query_json));
        		
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
