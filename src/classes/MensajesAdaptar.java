package classes;

import image_loader.ImageLoader;

import java.util.ArrayList;
import com.intsol.school.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MensajesAdaptar  extends BaseAdapter {
	
	private Activity activity;  
	ArrayList<Item_mensajes> arrayitms; 
	public ImageLoader imageLoader; 
	
	public MensajesAdaptar(Activity activity,ArrayList<Item_mensajes> listarry){
		this.activity = activity;
		this.arrayitms=listarry;
		imageLoader=new ImageLoader(activity.getApplicationContext());
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

	
   public View getView(int position, View convertView, ViewGroup parent) {  
	   View vi=convertView;
       LayoutInflater inflator = activity.getLayoutInflater();  
           
       //Creo objeto item y lo obtengo del array
       Item_mensajes itm=arrayitms.get(position);
       vi = inflator.inflate(R.layout.item_mensaje, null);
       
       //Propiedades
       TextView titulo_itm = (TextView) vi.findViewById(R.id.txt_nombre_mensaje);
       TextView txt_mensaje = (TextView) vi.findViewById(R.id.txt_mensaje);
       ImageView image = (ImageView) vi.findViewById(R.id.img_foto_perfil);
       
       titulo_itm.setText(itm.getAutor());
       txt_mensaje.setText(itm.getMej());
       String ruta = "http://192.168.1.112/sii/fotos/";
       imageLoader.DisplayImage(ruta + itm.getRuta_img()+".png", image);
          
       return vi;  
   }
}
