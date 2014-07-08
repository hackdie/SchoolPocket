package com.intsol.school;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import classes.HorarioAdapter;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

public class HorarioFragment extends Fragment{
	
	ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	View rootView = inflater.inflate(R.layout.horario, container, false);
        return rootView;
    }
	
	
	@Override
	 public void onActivityCreated(Bundle savedInstanceState) {  
	        super.onActivityCreated(savedInstanceState);
	        
	        
	        expListView = (ExpandableListView) getActivity().findViewById(R.id.lst_horario);
	        
	        prepareListData();
	        
	        listAdapter = new HorarioAdapter(getActivity(), listDataHeader, listDataChild);
	        expListView.setAdapter(listAdapter);
	 
	 }
	
	private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
 
        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");
 
        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
 
        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
 
        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
 
        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
	
}
