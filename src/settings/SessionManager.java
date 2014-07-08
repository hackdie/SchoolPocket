package com.IntelligentSolutions.schoolpocketdocente.settings;

import java.util.HashMap;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

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
	public static final String IS_LOGIN = "IsLoggedIn";
	public static final String KEY_RFC = "no_control";
	public static final String KEY_nombre = "nombre_completo";
	public static final String KEY_Rol = "semestre";
	public static final String KEY_Password = "Encripted_password";
	
	
	// Constructor
	public SessionManager(Context context){
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}
	
	/**
	 * Create login session
	 * */
	public void createLoginSession(String rfc, String nombre, String rol,  String password){
		
		//agregar datos 
		editor.putBoolean(IS_LOGIN, true);
		editor.putString(KEY_RFC, rfc);
		editor.putString(KEY_Password, password);
		editor.putString(KEY_nombre, nombre);
		editor.putString(KEY_Rol, rol);
		
		// commit changes
		editor.commit();
		
		Log.i("Session","Sessiones creada con exito");
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
		user.put(KEY_RFC, pref.getString(KEY_RFC, null));
		user.put(KEY_nombre, pref.getString(KEY_nombre, null));
		user.put(KEY_Password, pref.getString(KEY_Password, null));
		user.put(KEY_Rol, pref.getString(KEY_Rol, null));
		
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
