package fr.uppa.waam.listeners;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferencesManager {
	
	private Activity activity;
	private Editor editor;
	SharedPreferences preferences;
	
	
	public PreferencesManager(Activity activity) {
		super();
		this.activity = activity;
		this.preferences = this.activity.getPreferences(Context.MODE_PRIVATE);

	}



	public void save(String key, String value){
		this.editor = this.preferences.edit();
		this.editor.putString(key, value);
		this.editor.commit();
	}
	
	public String get(String key){
		return this.preferences.getString(key,null);
	}
}
