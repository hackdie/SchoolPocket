package classes;

import java.util.ArrayList;

import com.intsol.school.R;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class AlumnoMensaje_Adaptar extends ArrayAdapter<AlumnosMje_Item> {

	private ArrayList<AlumnosMje_Item> AlumnosMje_ItemList;
	Activity activity;
	
	public AlumnoMensaje_Adaptar(Activity my_activity,Context context, int textViewResourceId,ArrayList<AlumnosMje_Item> AlumnosMje_ItemList) {
		super(context, textViewResourceId, AlumnosMje_ItemList);
		activity = my_activity;
		this.AlumnosMje_ItemList = new ArrayList<AlumnosMje_Item>();
		this.AlumnosMje_ItemList.addAll(AlumnosMje_ItemList);
	}

	private class ViewHolder {
		TextView code;
		CheckBox name;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;
		Log.v("ConvertView", String.valueOf(position));

		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.alumno_mje_item, null);

			holder = new ViewHolder();
			holder.code = (TextView) convertView.findViewById(R.id.code);
			holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
			convertView.setTag(holder);

			holder.name.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					CheckBox cb = (CheckBox) v;
					AlumnosMje_Item AlumnosMje_Item = (AlumnosMje_Item) cb
							.getTag();
					Toast.makeText(activity.getApplicationContext(),
							"Clicked on Checkbox: " + cb.getText() + " is "
									+ cb.isChecked(), Toast.LENGTH_LONG).show();
					AlumnosMje_Item.setChecked(cb.isChecked());
				}
			});
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		AlumnosMje_Item AlumnosMje_Item = AlumnosMje_ItemList.get(position);
		holder.code.setText(" (" + AlumnosMje_Item.getNo_de_control() + ")");
		holder.name.setText(AlumnosMje_Item.getNombre());
		holder.name.setChecked(AlumnosMje_Item.isChecked());
		holder.name.setTag(AlumnosMje_Item);

		return convertView;

	}

}
