package com.intsol.school;

import java.util.ArrayList;
import classes.SessionManager;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity  {

	private String[] array_menu_textos_escolares, array_menu_textos_tools, array_menu_textos_general ;
    private ListView lst_escolares, lst_tools, lst_general;
    private DrawerLayout drawerLayout;
    private ImageButton btn_menu;
    public TextView txt_nombre;
    MenuAdapter MenuAdapter;
    String mje_notif;
    
    private ArrayList<Item_menu> Items_escolares,Items_tools,Items_general;
    private TypedArray array_menu_iconos_escolares,array_menu_iconos_tools,array_menu_iconos_general;

    SessionManager session;	

	@SuppressLint("Recycle")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.home);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.barra_title);
		
		
		
		// Session class instance
        session = new SessionManager(getApplicationContext());
        
        if(!session.checkLogin()){
        	Toast.makeText(getApplicationContext(), "Session no valida", Toast.LENGTH_LONG).show();
			Intent i = new Intent(this, PresentationActivity.class);
			this.startActivity(i);
			finish();
        }
        
        //Lista Lateral
        lst_escolares =  (ListView)findViewById(R.id.list_menu);
        lst_tools =  (ListView)findViewById(R.id.lst_tools);
        lst_general = (ListView)findViewById(R.id.lst_general);
		
		array_menu_textos_escolares = getResources().getStringArray(R.array.menus_lista_escolares);
		array_menu_textos_tools = getResources().getStringArray(R.array.menus_lista_tools);
		array_menu_textos_general = getResources().getStringArray(R.array.menus_lista_general);
		
		
		array_menu_iconos_escolares = getResources().obtainTypedArray(R.array.menu_icons_escolares);
		array_menu_iconos_tools = getResources().obtainTypedArray(R.array.menu_icons_tools);
		array_menu_iconos_general = getResources().obtainTypedArray(R.array.menu_icons_general);
		Items_escolares = new ArrayList<Item_menu>();
		Items_tools = new ArrayList<Item_menu>();
		Items_general = new ArrayList<Item_menu>();
        
      	//Llenamos el array para pasarlo a la lista
		for(int x=0; x<array_menu_textos_escolares.length; x++){
			Items_escolares.add(new Item_menu(array_menu_textos_escolares[x], array_menu_iconos_escolares.getResourceId(x, -1)));
		}
		
        MenuAdapter= new MenuAdapter(this,Items_escolares);
        lst_escolares.setAdapter(MenuAdapter);	
        
        
      //Llenamos el array para pasarlo a la lista
        for(int x=0; x<array_menu_textos_tools.length; x++){
        	Items_tools.add(new Item_menu(array_menu_textos_tools[x], array_menu_iconos_tools.getResourceId(x, -1)));
		}
        
        MenuAdapter= new MenuAdapter(this,Items_tools);
        lst_tools.setAdapter(MenuAdapter);
        
        
      //Llenamos el array para pasarlo a la lista
        for(int x=0; x<array_menu_textos_general.length; x++){
        	Items_general.add(new Item_menu(array_menu_textos_general[x], array_menu_iconos_general.getResourceId(x, -1)));
		}
        
        MenuAdapter= new MenuAdapter(this,Items_general);
        lst_general.setAdapter(MenuAdapter);
        
        
        
     // Evento click lista 1        
        lst_escolares.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    	  @Override
    	  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
    		  lst_tools.requestLayout();
    		  lst_general.requestLayout();
    		  lst_tools.clearChoices();
    		  lst_general.requestLayout();
    		  MostrarFragment(position);
    		  drawerLayout.closeDrawer(Gravity.LEFT);
    	  }
    	});
        
     // Evento click lista 2        
        lst_tools.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    	  @Override
    	  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
    		  lst_escolares.requestLayout();
    		  lst_general.requestLayout();
    		  lst_escolares.clearChoices();
    		  lst_general.requestLayout();
    		  
    		  MostrarFragment(position+6);
    		  drawerLayout.closeDrawer(Gravity.LEFT);
    	  }
    	});
        
     // Evento click lista 3        
        lst_general.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    	  @Override
    	  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
    		  lst_escolares.requestLayout();
    		  lst_tools.requestLayout();
    		  lst_escolares.clearChoices();
    		  lst_tools.requestLayout();
    		  MostrarFragment(position+9);
    		  drawerLayout.closeDrawer(Gravity.LEFT);
    	  }
    	});
        
        
        // Objetos de la barra lateral
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        btn_menu = (ImageButton)findViewById(R.id.btn_menu);
        
        //evento btn menu
        btn_menu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
					drawerLayout.closeDrawer(Gravity.LEFT);
		        } else {
		        	drawerLayout.openDrawer(Gravity.LEFT); 
		        }   
			}
		});
        
        //Agregar datos en dado caso q sea una notificacion
        boolean flag_metodo = false;
        try{
        	Intent notif = getIntent();
            flag_metodo = notif.getBooleanExtra("flag_notif", false);
            mje_notif = notif.getStringExtra("from_mje");
        }
        catch(Exception ex){
        	ex.printStackTrace();
        }
        
        if(flag_metodo){
        	MostrarFragment(12);
        }
        else{
        	MostrarFragment(0);
        }
        
	}
	
	
	/*Pasando la posicion de la opcion en el menu nos mostrara el Fragment correspondiente*/
    private void MostrarFragment(int position) {
        // update the main content by replacing fragments
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        
        
        switch (position) {
        	case 0:
        		HomeFragment fragment_home = new HomeFragment();
	        	transaction.replace(R.id.frame_container, fragment_home);
	            position=1;
			break;
        	case 1:break;
        	case 2:
        		PruebaFragment fragment_prueba = new PruebaFragment();
	            transaction.replace(R.id.frame_container, fragment_prueba);
                break;
        	case 3:
        		HorarioFragment fragment2 = new HorarioFragment();
	            transaction.replace(R.id.frame_container, fragment2);
                break;
	        case 4:
	        	BoletasFragment frag_boletas = new BoletasFragment();
	        	transaction.replace(R.id.frame_container, frag_boletas);
	            break;
	        case 5:
	        	PreguntasFragment fragment_preg = new PreguntasFragment();
	        	transaction.replace(R.id.frame_container, fragment_preg);
	            break;
	        case 6:
	        	SendMessageFragment fragment_enviar_mje = new SendMessageFragment();
            	transaction.replace(R.id.frame_container, fragment_enviar_mje);
	            break;
	        case 7:
	        	MensajesFragment fragment_mjes = new MensajesFragment();
	        	transaction.replace(R.id.frame_container, fragment_mjes);
	            break;
	        case 8:
	        	AgendaFragment agenga_fragment = new AgendaFragment();
            	transaction.replace(R.id.frame_container, agenga_fragment);
	        	break;
	        case 9:
	        	PerfilFragment fragment_perfil = new PerfilFragment();
            	transaction.replace(R.id.frame_container, fragment_perfil);
	        	break;
	        case 10:break;
	        case 11:
		        session.logoutUser();
				Intent i = new Intent(MainActivity.this, LoginActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				MainActivity.this.startActivity(i);
				finish();
			break;
	        case 12:
	        	Bundle bundle = new Bundle();
	        	bundle.putString("id_mje", mje_notif); // cambiar
	        	ConversacionFragment fragment_conv = new ConversacionFragment();
	        	fragment_conv.setArguments(bundle);
	       	 	transaction.replace(R.id.frame_container, fragment_conv);
	        	break;
	            
	        default:
	        	
        	break;
        }
        
        transaction.addToBackStack(null);
        transaction.commit();
    }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
