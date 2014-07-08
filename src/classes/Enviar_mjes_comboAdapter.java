package classes;

import java.util.ArrayList;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class Enviar_mjes_comboAdapter implements SpinnerAdapter {

	 Context context;
	 ArrayList<Item_enviar_mjes_combo> grupos;
    
	 public Enviar_mjes_comboAdapter(Context context ,ArrayList<Item_enviar_mjes_combo> _grupos){
        this.context =context;
        this.grupos = _grupos;
	 }
	
	
	@Override
	public int getCount() {
		return grupos.size();
	}

	@Override
	public Object getItem(int position) {
		return grupos.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public int getItemViewType(int arg0) {
		return 0;
	}
	
	public int getIDFromIndex(int position) {
        return  grupos.get(position).getId();       
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView textview = (TextView) inflater.inflate(android.R.layout.simple_spinner_item, null);
        textview.setText(grupos.get(position).getMateria());
       
        return textview;
	}

	@Override
	public int getViewTypeCount() {
		 return android.R.layout.simple_spinner_item;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	  public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        TextView textview = (TextView) inflater.inflate(android.R.layout.simple_spinner_item, null);
        textview.setText(grupos.get(position).getMateria());
       
        return textview;
    }

}
