package classes;

import java.util.ArrayList;
import com.intsol.school.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ConversacionAdapter extends BaseAdapter {

	private Activity activity;  
	ArrayList<Item_conversacion> arrayitms; 
	
	public ConversacionAdapter(Activity _activity, ArrayList<Item_conversacion> _arrayitms  ){
		this.activity = _activity;
		this.arrayitms = _arrayitms;
	}
	
	@Override
	public int getCount() {
		return arrayitms.size();
	}

	@Override
	public Object getItem(int position) {
		return arrayitms.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 View vi=convertView;
		 LayoutInflater inflator = activity.getLayoutInflater(); 
		 
		 Item_conversacion my_objet = arrayitms.get(position);
		 vi = inflator.inflate(R.layout.item_bubble_mje, null);
		 
		 //Propiedades 
		 TextView txt_mje = (TextView)vi.findViewById(R.id.comentario);
		 txt_mje.setText(my_objet.getMje());
		
		 return vi;
	}

}
