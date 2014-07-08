package com.intsol.school;

import java.util.ArrayList;

import classes.AlumnosMje_Item;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class PruebaFragment extends Fragment {

	AlumnoMensaje_Adaptar dataAdapter;
	LinearLayout LinearMainTAGS;
	ArrayList<String> mylista;
	private int mScreenWidth;
	static int num_actual = 0;
	
	ArrayList<AlumnosMje_Item> Lista_Alumnos;
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater
				.inflate(R.layout.fragm_index, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		
		mylista = new ArrayList<String>();
        
        
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        
        LinearMainTAGS =(LinearLayout) getActivity().findViewById(R.id.Linear_TAGS);
        
		

		// Array list of countries
		Lista_Alumnos = new ArrayList<AlumnosMje_Item>();
		AlumnosMje_Item Alumno = new AlumnosMje_Item("10150095", "Diego Ricardo Galindo Macias",false);
		Lista_Alumnos.add(Alumno);
		Alumno = new AlumnosMje_Item("10150095", "Juan Lopez Hernandez", false);
		Lista_Alumnos.add(Alumno);
		Alumno = new AlumnosMje_Item("10150094", "Pedro Macias Lozano", false);
		Lista_Alumnos.add(Alumno);
		Alumno = new AlumnosMje_Item("10150093", "Antonio Gloria Ruvalcaba", false);
		Lista_Alumnos.add(Alumno);
		Alumno = new AlumnosMje_Item("10150092", "Brenda Gonzales Rordriguez", false);
		Lista_Alumnos.add(Alumno);
		Alumno = new AlumnosMje_Item("10150091", "Ferndanda Gomez Mendez", false);
		Lista_Alumnos.add(Alumno);
		Alumno = new AlumnosMje_Item("10150090", "Mayela Esparza", false);
		Lista_Alumnos.add(Alumno);
		Alumno = new AlumnosMje_Item("10151020", "Raul Armendariz Lopez", false);
		Lista_Alumnos.add(Alumno);
		Alumno = new AlumnosMje_Item("10151021", "Juan Carlos Gutierrez", false);
		Lista_Alumnos.add(Alumno);
		Alumno = new AlumnosMje_Item("10151022", "Ricardo Meraz Mendez", false);
		Lista_Alumnos.add(Alumno);
		Alumno = new AlumnosMje_Item("10151023", "Hector de Jesus Sandana", false);
		Lista_Alumnos.add(Alumno);
		Alumno = new AlumnosMje_Item("10151024", "Juliana Perez Mendez", false);
		Lista_Alumnos.add(Alumno);
		Alumno = new AlumnosMje_Item("10151025", "Miriam Ruvalcaba", false);
		Lista_Alumnos.add(Alumno);

		// create an ArrayAdaptar from the String Array
		dataAdapter = new AlumnoMensaje_Adaptar(getActivity(), getActivity(),
				R.layout.alumno_mje_item, Lista_Alumnos);

		ListView listView = (ListView) getActivity().findViewById(
				R.id.listView1);
		// Assign adapter to ListView

		listView.setAdapter(dataAdapter);
	}

	@SuppressWarnings("deprecation")
	public void add_palabra() {

		LinearMainTAGS.removeAllViews();
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		params.setMargins(5, 5, 5, 5);

		if (!mylista.isEmpty()) {

			LinearLayout mNewLayout = new LinearLayout(getActivity()); // liner_temporal
			mNewLayout.setOrientation(LinearLayout.HORIZONTAL);
			int mButtonsSize = 0;
			Rect bounds = new Rect();

			for (int i = 0; i < mylista.size(); i++) {

				String mButtonTitle = mylista.get(i);
				TextView txt_TAG = new TextView(getActivity());
				txt_TAG.setPadding(10, 3, 10, 3);
				txt_TAG.setText(mButtonTitle);
				txt_TAG.setTextColor(getResources().getColor(R.color.blanco));
				txt_TAG.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.style_tag));

				Paint textPaint = txt_TAG.getPaint();
				textPaint.getTextBounds(mButtonTitle, 0, mButtonTitle.length(),
						bounds);
				mButtonsSize += (bounds.width() + 45); //padding
				if (mButtonsSize < (mScreenWidth - 32)) { // -32 because of
															// extra padding in
															// main layout.
					mNewLayout.addView(txt_TAG, params);
				} else {
					LinearMainTAGS.addView(mNewLayout);
					mNewLayout = new LinearLayout(getActivity());
					mNewLayout.setOrientation(LinearLayout.HORIZONTAL);
					mButtonsSize = bounds.width();
					mNewLayout.addView(txt_TAG, params); 
				}
			}

			LinearMainTAGS.addView(mNewLayout); // add the last layout/ button.
		}
	}
	
	
	public int obtener_indice(String nombre_alumno){
		int index = 0;
		for(int x=0; x<Lista_Alumnos.size();x++){
			if(nombre_alumno.compareTo(Lista_Alumnos.get(x).getNombre())==0){
				index = x;
				break;
			}
		}
		
		return index;
	}
	
	
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
						AlumnosMje_Item AlumnosMje_Item = (AlumnosMje_Item) cb.getTag();
						if(cb.isChecked()){
							int indice = obtener_indice(cb.getText().toString().trim());
							mylista.add(Lista_Alumnos.get(indice).getNo_de_control());
							add_palabra();
							num_actual++;
						}
						else{
							int indice = obtener_indice(cb.getText().toString().trim());
							mylista.remove(Lista_Alumnos.get(indice).getNo_de_control());
							add_palabra();
							num_actual--;
						}
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
	

}
