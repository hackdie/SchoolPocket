package com.intsol.school;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import classes.Guardar_Calif_maestros_helper;
import classes.Httppostaux;
import classes.SessionManager;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;


public class PreguntasFragment extends Fragment {
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
	String no_de_control;
	
	String IP_Server = "coupongo.com.mx/schoolpocket";
	String URL_connect = "http://" + IP_Server + "/sii/home/prueba2";
	Httppostaux post;
	
	private static ProgressDialog pDialog,pDialog2;
	
	static FragmentTransaction fragTransaction;
	SessionManager session;
	
	public PreguntasFragment(){}

 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
		View mContainer = inflater.inflate(R.layout.style_pregunta, null);
		return mContainer;
    }
    
    @Override 
    public void onActivityCreated(Bundle savedInstanceState) {  
        super.onActivityCreated(savedInstanceState);
        
        
        linearLayout = (LinearLayout) getActivity().findViewById(R.id.view_preguntas);
        txt_pregunta = (TextView) getActivity().findViewById(R.id.txt_pregunta);
        array_preguntas = getResources().getStringArray(R.array.preguntas);
        
        txt_pregunta.setText(array_preguntas[0]);
        
		post =new Httppostaux();
		
		//fragTransaction = getFragmentManager().beginTransaction();
		
		session = new SessionManager(getActivity());
		HashMap<String, String> user_variables = session.getUserDetails();
        no_de_control = user_variables.get(SessionManager.KEY_no_de_control);
		
		new asyncGetDatos().execute(no_de_control);
    }
    
    public static void mje_alert(){
    	
		pDialog2.setMessage("Guardando");
		pDialog2.setIndeterminate(false);
		pDialog2.setCancelable(false);
		pDialog2.show();
    }
    
    public static void mje_cerrar(){
    	pDialog2.dismiss();
    }
    
    static AlertDialog alertDialog;
    @SuppressWarnings("deprecation")
	public static void mje_correcto(){
    	
			alertDialog.setTitle("Success");
			alertDialog.setMessage("Registro Completo Gracias.");
			alertDialog.setIcon(R.drawable.check);
			
			// Setting OK Button
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			        	
			        	
			        	/*HomeFragment secFrag = new HomeFragment();
			        	
	                    fragTransaction.replace(R.id.frame_container,secFrag );
	                    fragTransaction.addToBackStack(null);
	                    fragTransaction.commit();*/
			        	
			        }
			});
			alertDialog.show();
    }
    
    
    @SuppressWarnings("deprecation")
	public static void mje_error(){
    	alertDialog.setTitle("Error");
		alertDialog.setMessage("Error al intentar guardar registros");
		alertDialog.setIcon(R.drawable.fulltime);
		
		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) {
		        	Log.e("asd", "asdasdadsasd");
		       
		        }
		});
		alertDialog.show();
    }
    
    
    //Helper Http
    class asyncGetDatos extends AsyncTask <String, String, String>{
    	
    	String no_de_control;
    	
    	protected void onPreExecute(){
    		pDialog =  new ProgressDialog(getActivity());
    		pDialog.setMessage("Cargando");
    		pDialog.setIndeterminate(false);
    		pDialog.setCancelable(false);
    		pDialog.show();
    	}
    	
    	@Override
    	protected String doInBackground(String... param) {	
    		no_de_control = param[0];
    		if(loginstatus(no_de_control) == true){
    			return "ok";
    		}
    		else{
    			return "err";
    		}
    	}
    	
    	@SuppressLint("NewApi")
		@SuppressWarnings("deprecation")
		protected void onPostExecute(String result){
    		//Cerrar ventana de cargando
			pDialog.dismiss();
    		Log.e("onPostExecute=",""+result);
    		if(result.equals("ok")){
    			try{
        			tot_maestros = jdata.length();
        			int id = 100;
        			
        			nombres = new String[tot_maestros];
        	        pregunta_actual = -1; //Cambiar con la consulta sql
        			tot_preguntas = array_preguntas.length;
        			editText = new CheckBox[tot_maestros];
        			vect_id_maestro = new String[tot_maestros];
        			matr_preguntas = new int[tot_maestros][tot_preguntas];
        			
        			//crear checkboxs
        			for(int x=0; x<tot_maestros;x++){
        				JSONObject fila_maestros = jdata.getJSONObject(x);
    					
        				editText[x] = new CheckBox(getActivity());
    					editText[x].setId(id); //Set id to remove in the future.
    					editText[x].setTextColor(getResources().getColor(R.color.gris_obscuro));
    					editText[x].setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
    					            LayoutParams.WRAP_CONTENT));
    					
    					editText[x].setText(fila_maestros.getString("nombre_docente"));
    					
    					vect_id_maestro[x] = fila_maestros.getString("rfc");
    					try{
    						linearLayout.addView(editText[x]);
    						id++;
    					}catch(Exception e){
    						x--;
    						e.printStackTrace();
    					}
        			}
        			
        			//Crear boton
        			
        			btn_siguiente = new Button(getActivity()) ;
					btn_siguiente.setId(123456); //Set id to remove in the future.
					btn_siguiente.setBackground(getResources().getDrawable(R.drawable.style_btn));
					btn_siguiente.setText("Siguiente");
					
					LinearLayout.LayoutParams para=new LinearLayout.LayoutParams(
						    LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT );
					para.setMargins(0, 20, 0, 10); //left,top,right, bottom
					btn_siguiente.setLayoutParams(para);
					btn_siguiente.setTextColor(getResources().getColor(R.color.blanco));
					btn_siguiente.setTextSize(20);
					
					try{
				       linearLayout.addView(btn_siguiente);
				       id++;
					}catch(Exception e){
						e.printStackTrace();
					}
					
					//Evento boton
					btn_siguiente.setOnClickListener(new OnClickListener() {
				        @Override
				        public void onClick(View v) {
				        	pregunta_actual++;
				        	if(pregunta_actual<=tot_preguntas-1){
				        		
				        		for(int x=0;x<tot_maestros;x++){
				        			if(editText[x].isChecked()){
				        				matr_preguntas[x][pregunta_actual] = 1;
				        			}
				        			else{
				        				matr_preguntas[x][pregunta_actual] = 0;
				        			}
								}
								
								for(int x=0;x<tot_maestros;x++){
									editText[x].setChecked(false);
								}
								
				        		txt_pregunta.setText(array_preguntas[pregunta_actual]);
				        	}else{
				        		
				        		JSONObject calificados = new JSONObject();
				        		
								try {
									calificados.put("no_de_control", no_de_control);
									calificados.put("finalizado", "1");
									
									
									JSONArray lista_maestros = new JSONArray();
									for(int x=0; x<tot_maestros;x++){
										lista_maestros.put(vect_id_maestro[x]);
									}
									calificados.put("key_maestros", lista_maestros);
									
									
									JSONArray calif[] = new JSONArray[tot_maestros];
									JSONArray all_calif = new JSONArray();
				        		
									for(int x=0; x<tot_maestros;x++){
										calif[x] = new JSONArray();
					        			for(int y=0; y<tot_preguntas;y++){
					        				calif[x].put(matr_preguntas[x][y]);
					        			}
					        			all_calif.put(calif[x]);
					        		}
									
									calificados.put("all_calificaciones", all_calif);
									
									JSONObject body = new JSONObject();
									body.put( "main", calificados);
									
									String json = body.toString();
									
									pDialog2 =  new ProgressDialog(getActivity());
									alertDialog = new AlertDialog.Builder(getActivity()).create();
									new Guardar_Calif_maestros_helper().execute(json);
									
									
								} catch (JSONException e) {
									e.printStackTrace();
								}
				        	}
				        }
				    });
        		}catch(Exception jex){
        			Log.e("error_login","Error al conectar gon httppostaux " + jex.toString());
        		}
    		}
    		else{
    			txt_pregunta.setText("Por el momento no hay preguntas por contestar, intente mas tarde gracias.");
    		}
    	}
    	
    	 public  boolean loginstatus(String no_de_control){
	    	try{
	        	ArrayList<NameValuePair> param2send =  new ArrayList<NameValuePair>();
	        	param2send.add(new BasicNameValuePair("no_de_control",no_de_control));
	        		
	        	jdata = post.getservicedata(param2send, URL_connect);
	        	
	        	if(jdata != null && jdata.length() >  0){
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