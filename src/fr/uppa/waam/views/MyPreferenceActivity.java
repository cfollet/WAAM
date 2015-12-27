package fr.uppa.waam.views;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import fr.uppa.waam.R;
import fr.uppa.waam.util.ThemeHandler;

public class MyPreferenceActivity extends PreferenceActivity {
	private ThemeHandler themeHandler;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.activity_preference);
	}
}
