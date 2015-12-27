package fr.uppa.waam.util;

import java.util.Map;

import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Window;
import fr.uppa.waam.R;
import fr.uppa.waam.models.Message;

public class ThemeHandler {
	private Activity activity;

	public ThemeHandler(Activity activity) {
		this.activity = activity;
	}

	public void init() {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.activity);

		String gender = preferences.getString("gender_preference", "");

		ActionBar actionBar = this.activity.getActionBar();
		Log.i("test", gender);

		if (Integer.parseInt(gender) == Message.MALE_CODE) {

			actionBar.setBackgroundDrawable(
					new ColorDrawable(this.activity.getResources().getColor(R.color.primary_indigo_500)));
			actionBar.setDisplayShowTitleEnabled(false);
			actionBar.setDisplayShowTitleEnabled(true);
		}
		if (Integer.parseInt(gender) == Message.FEMALE_CODE) {
			actionBar.setBackgroundDrawable(new ColorDrawable(this.activity.getResources().getColor(R.color.primary_pink_500)));
			actionBar.setDisplayShowTitleEnabled(false);
			actionBar.setDisplayShowTitleEnabled(true);
		}
	}

}
