package classes;

import java.util.HashMap;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {
	// Shared Preferences
	SharedPreferences pref;
	
	// Editor for Shared preferences
	Editor editor;
	
	// Context
	Context _context;
	
	// Shared pref mode
	int PRIVATE_MODE = 0;
	
	// Sharedpref file name
	private static final String PREF_NAME = "AndroidHivePref";
	
	// Mis variables de session
	private static final String IS_LOGIN = "IsLoggedIn";
	public static final String KEY_no_de_control = "no_control";
	public static final String KEY_nombre = "nombre_completo";
	public static final String KEY_semestre = "semestre";
	public static final String KEY_id_carrera = "id_carrera";
	
	
	// Constructor
	public SessionManager(Context context){
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}
	
	/**
	 * Create login session
	 * */
	public void createLoginSession(String no_de_control, String nombre, String semestre,  String id_carrera){
		
		//agregar datos 
		editor.putBoolean(IS_LOGIN, true);
		editor.putString(KEY_no_de_control, no_de_control);
		editor.putString(KEY_nombre, nombre);
		editor.putString(KEY_semestre, semestre);
		editor.putString(KEY_id_carrera, id_carrera);
		
		// commit changes
		editor.commit();
	}	
	
	public boolean checkLogin(){
		// Check login status
		if(!this.isLoggedIn()){
			return false;
		}
		return true;
	}
	
	/**
	 * Get stored session data
	 * */
	public HashMap<String, String> getUserDetails(){
		HashMap<String, String> user = new HashMap<String, String>();
		
		// Obtener mis variables
		user.put(KEY_no_de_control, pref.getString(KEY_no_de_control, null));
		user.put(KEY_nombre, pref.getString(KEY_nombre, null));
		user.put(KEY_semestre, pref.getString(KEY_semestre, null));
		user.put(KEY_id_carrera, pref.getString(KEY_id_carrera, null));
		
		// return user
		return user;
	}
	
	/**
	 * Clear session details
	 * */
	public void logoutUser(){
		editor.clear();
		editor.commit();
	}

	// Get Login State
	public boolean isLoggedIn(){
		return pref.getBoolean(IS_LOGIN, false);
	}
}
