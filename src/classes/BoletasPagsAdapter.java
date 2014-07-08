package classes;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class BoletasPagsAdapter extends PagerAdapter {
	
	private ArrayList<View> views; 
    private ArrayList<String> pagerTabStrip;
	    
    public BoletasPagsAdapter(ArrayList<View> views,ArrayList<String> titulo_paginas) {
        this.views =views; 
        this.pagerTabStrip = titulo_paginas;
    }

    @Override
    public int getCount() {
        return views == null ? 0 :views.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pagerTabStrip.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position);
        
        container.addView(view);
        return view;
    }
	

}
