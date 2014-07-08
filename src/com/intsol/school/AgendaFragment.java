package com.intsol.school;

import java.util.ArrayList;

import classes.AgendaAdapter;
import classes.Item_agenda;
import classes.MensajesAdaptar;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class AgendaFragment extends Fragment {
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
  
		View mContainer = inflater.inflate(R.layout.agenda, null);
		return mContainer;
    }
	
	ListView lst_agenda;
	ArrayList<Item_agenda> item_agenda;
	private String[] array_telefonos, array_nombres,array_puestos ,array_horarios;
	private TypedArray array_avatars;
	AgendaAdapter agendaAdapter;
	
	
	
	@SuppressLint("Recycle")
	public void onActivityCreated(Bundle savedInstanceState) {  
		super.onActivityCreated(savedInstanceState);
		
		lst_agenda = (ListView)getActivity().findViewById(R.id.lst_agenda);
		item_agenda = new ArrayList<Item_agenda>();
		
		//Obtenemos textos del xml
		array_telefonos = getResources().getStringArray(R.array.agenda_telefonos);
		array_nombres = getResources().getStringArray(R.array.agenda_nombres);
		array_puestos = getResources().getStringArray(R.array.agenda_puestos);
		array_horarios = getResources().getStringArray(R.array.agenda_horarios);
		array_avatars = getResources().obtainTypedArray(R.array.agenda_fotos);
		
		
		for(int x=0; x<array_telefonos.length;x++){
			item_agenda.add(new Item_agenda(array_telefonos[x],array_nombres[x],array_puestos[x],array_horarios[x],array_avatars.getResourceId(x, -1)));
		}
		
		agendaAdapter= new AgendaAdapter(getActivity(),item_agenda);
		lst_agenda.setAdapter(agendaAdapter);
		
		lst_agenda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    	  @Override
	    	  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
	    		  try{
	    		      startActivity(new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+item_agenda.get(position).getNumero_tel())));
	    		   }catch(Exception e){
	    		      e.printStackTrace();
	    		   }  
	    	  }
    	});
		
		
	}
}
