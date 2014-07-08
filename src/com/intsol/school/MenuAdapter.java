package com.intsol.school;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuAdapter extends BaseAdapter {
    private Activity activity;  
	ArrayList<Item_menu> arrayitms; 

   public MenuAdapter(Activity activity,ArrayList<Item_menu>  listarry) {  
       super();  
       this.activity = activity;  
       this.arrayitms=listarry;
   }     
   //Retorna objeto Item_objct del array list
   @Override
   public Object getItem(int position) {       
       return arrayitms.get(position);
   }   
    public int getCount() {  
      // TODO Auto-generated method stub  
        return arrayitms.size();  
    }    
    @Override
    public long getItemId(int position) {
        return position;
    }   
    //Declaramos clase estatica la cual representa a la fila
    public static class Fila  
    {  
    		TextView titulo_itm;
    		ImageView icono;
    }  
   public View getView(int position, View convertView, ViewGroup parent) {  
      // TODO Auto-generated method stub  
	   Fila view;  
       LayoutInflater inflator = activity.getLayoutInflater();  
       if(convertView==null){
    	   
           view = new Fila();
           //Creo objeto item y lo obtengo del array
           Item_menu itm=arrayitms.get(position);
           convertView = inflator.inflate(R.layout.sytel_lista_menu, null);
           //Titulo
           view.titulo_itm = (TextView) convertView.findViewById(R.id.txt_titulo_list);
           //Seteo en el campo titulo el nombre correspondiente obtenido del objeto
           view.titulo_itm.setText(itm.getTitulo());           
           //Icono
           view.icono = (ImageView) convertView.findViewById(R.id.img_icon_list);
           //Seteo el icono
           view.icono.setImageResource(itm.getIcono());           
           convertView.setTag(view);  
        }  
        else  
        {  
           view = (Fila) convertView.getTag();  
        }  
        return convertView;  
    }
}
