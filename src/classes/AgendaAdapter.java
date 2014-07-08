package classes;

import java.util.ArrayList;

import com.intsol.school.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AgendaAdapter extends BaseAdapter {

	private Activity activity;  
	ArrayList<Item_agenda> arrayitms; 
	
	public AgendaAdapter(Activity my_activity, ArrayList<Item_agenda> my_array_list){
		activity = my_activity;
		arrayitms = my_array_list;
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
		View vi = convertView;
		LayoutInflater inflator = activity.getLayoutInflater();	
		
		Item_agenda itm_ag = arrayitms.get(position);
		vi = inflator.inflate(R.layout.item_agenda, null);
		
		TextView txt_horario = (TextView) vi.findViewById(R.id.txt_horario_agenda);
		txt_horario.setText(itm_ag.getHorario());
		
		TextView txt_nombre = (TextView) vi.findViewById(R.id.txt_nombre_agenda);
		txt_nombre.setText(itm_ag.getNombre_encargado());
		
		TextView txt_puesto = (TextView) vi.findViewById(R.id.txt_puesto);
		txt_puesto.setText(itm_ag.getNombre_puesto());
		
		ImageView avatar = (ImageView) vi.findViewById(R.id.img_agenda);
		avatar.setImageResource(itm_ag.getAvatar());  
		
		return vi;
	}

}
