package com.intsol.school;

import java.util.ArrayList;

import classes.BoletasPagsAdapter;
import classes.Item_boleta;
import classes.ListBoletasAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class BoletasFragment extends Fragment {
	
    private ArrayList<View> views;
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState){
  
		View mContainer = inflater.inflate(R.layout.boletas, null);
		return mContainer;
    }
	
	
	@Override
	
	public void onActivityCreated(Bundle savedInstanceState) {  
		super.onActivityCreated(savedInstanceState);
		
		
		ArrayList<String> menus = new ArrayList<String>();
		menus.add("Ene/Jun 10");
		menus.add("Jun/Dic 10");
		menus.add("Ene/Jun 11");
		menus.add("Jun/Dic 11");
		menus.add("Ene/Jun 12");
		menus.add("Jun/Dic 12");
		
		
		ViewPager viewPager = (ViewPager)getActivity().findViewById(R.id.viewPager);
        views = new ArrayList<View>();
        View my_vies[] = new View[menus.size()]; 
        
        for(int tot_menus=0; tot_menus<menus.size(); tot_menus++){
        	my_vies[tot_menus] = getActivity().getLayoutInflater().inflate(R.layout.boleta, null);
        	views.add(my_vies[tot_menus]);
		}
        viewPager.setAdapter(new BoletasPagsAdapter(views,menus));
        
        
        ListView mis_listas[] = new ListView[4]; 
        
        for(int x=0; x<3; x++){
        	mis_listas[x] = (ListView)(views.get(x).findViewById(R.id.list_boletas));
        	mis_listas[x].setVisibility(ListView.VISIBLE);
        	mis_listas[x].setAdapter(new ListBoletasAdapter(obtenerItems(x),getActivity().getLayoutInflater()));
        }
	}
	
	private ArrayList<Item_boleta> obtenerItems(int position) {
		ArrayList<Item_boleta> items = new ArrayList<Item_boleta>();
		switch(position){
		case 0: 
		    items.add(new Item_boleta(1, "Admin. y Seguridad en redes", "(A28)",98,4, "Regular","Excelente trabajo, sigue asi no cambies."));
		    items.add(new Item_boleta(2, "Programacion Web", "(B28)",100,5, "Regular","Sin palabras fulanito, tienes un exelente trabajo y mereces eso y mas exito"));
		    items.add(new Item_boleta(3, "Protocolos Abiertos", "(C28)",72,4, "Complementaria","Bueno para nada, nomas por que me diste la botella si no no pasas cabron!!"));
			break;
		case 1:
			items.add(new Item_boleta(1, "Base de datos", "(A11)",98,4, "Regular","Excelente trabajo, sigue asi no cambies."));
		    items.add(new Item_boleta(2, "Negocios Electronicos", "(B11)",100,5, "Regular","Sin palabras fulanito, tienes un exelente trabajo y mereces eso y mas exito"));
		    items.add(new Item_boleta(3, "Computo en la nube", "(C11)",72,4, "Complementaria","Bueno para nada, nomas por que me diste la botella si no no pasas cabron!!"));
		    items.add(new Item_boleta(4, "Redes I", "(D11)",72,4, "Complementaria","Bueno para nada, nomas por que me diste la botella si no no pasas cabron!!"));
		    items.add(new Item_boleta(5, "Introducciono a las Tics", "(E11)",72,4, "Complementaria","Bueno para nada, nomas por que me diste la botella si no no pasas cabron!!"));
			break;
		case 2: 
			items.add(new Item_boleta(1, "Interaccion Humano", "(A20)",98,4, "Regular","Excelente trabajo, sigue asi no cambies."));
		    items.add(new Item_boleta(2, "Fundamentos de Progr.", "(B20)",100,5, "Regular","Sin palabras fulanito, tienes un exelente trabajo y mereces eso y mas exito"));
		    items.add(new Item_boleta(3, "Etica", "(C20)",72,4, "Complementaria","Bueno para nada, nomas por que me diste la botella si no no pasas cabron!!"));
		    items.add(new Item_boleta(4, "Redes II", "(D20)",72,4, "Complementaria","Bueno para nada, nomas por que me diste la botella si no no pasas cabron!!"));
			break;
		}
		
	         
	    return items;
	}

}
