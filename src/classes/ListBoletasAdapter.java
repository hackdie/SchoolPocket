package classes;

import java.util.ArrayList;

import com.intsol.school.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListBoletasAdapter extends BaseAdapter{
	
	 private ArrayList<Item_boleta> datos ;
    private LayoutInflater layoutInflater;
    
    public ListBoletasAdapter(ArrayList<Item_boleta> files, LayoutInflater layoutInflater) {
        super();
        this.datos = files;
        this.layoutInflater =layoutInflater;
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int arg0) {
        return datos.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	 
    	//obtenemos el contenedor de todos las vistas
    	convertView = layoutInflater.inflate(R.layout.item_boleta, null);
        
        
        TextView txt_titulo  = (TextView)convertView.findViewById(R.id.txt_titulo);
        TextView txt_codigo  = (TextView)convertView.findViewById(R.id.txt_codigo_materia);
        
        txt_titulo.setText(datos.get(position).getNombre_materia());
        txt_codigo.setText(datos.get(position).getCodigo_carrera());
        
        return convertView;
    }
}
