package com.intsol.school;
import image_loader.ImageHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PerfilFragment extends Fragment {
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	View rootView = inflater.inflate(R.layout.perfil, container, false);
        return rootView;
    }
	
	@Override
	 public void onActivityCreated(Bundle savedInstanceState) {  
	        super.onActivityCreated(savedInstanceState);
	        
	        
	        ImageView icon =(ImageView)getActivity().findViewById(R.id.img_perfil);
			Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.perfil_foto);
			bMap=ImageHelper.getRoundedCornerBitmap(bMap,100);
			icon.setImageBitmap(bMap);
	        
	
	}
	
}
