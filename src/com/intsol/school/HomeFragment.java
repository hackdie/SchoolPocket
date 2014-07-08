package com.intsol.school;

import java.util.HashMap;
import classes.SessionManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class HomeFragment extends Fragment {
	
	Button btn_prueba;
	TextView txt_nombre;
	SessionManager session;
	public HomeFragment(){}

 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
    	View rootView = inflater.inflate(R.layout.main, container, false);
          
        return rootView;
    }
    
    @Override 
    public void onActivityCreated(Bundle savedInstanceState) {  
        super.onActivityCreated(savedInstanceState);
        session = new SessionManager(getActivity());
        
 	   	HashMap<String, String> user_variables = session.getUserDetails();
        String nombre_completo = user_variables.get(SessionManager.KEY_nombre);
        
        txt_nombre = (TextView)getActivity().findViewById(R.id.txt_nombre_user);
        txt_nombre.setText(nombre_completo);
    }
}