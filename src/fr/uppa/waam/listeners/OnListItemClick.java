package fr.uppa.waam.listeners;

import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import fr.uppa.waam.models.Message;
import fr.uppa.waam.views.WallActivity;

public class OnListItemClick implements OnItemClickListener {

	WallActivity activity;

	public OnListItemClick(WallActivity activity) {
		super();
		this.activity = activity;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Message message = (Message) parent.getItemAtPosition(position);

		String uri = String.format(Locale.ENGLISH, "geo:%f,%f", message.getLocation().getLatitude(),
				message.getLocation().getLongitude());
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));

		PackageManager packageManager = this.activity.getPackageManager();
		List activities = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
		boolean isIntentSafe = activities.size() > 0;
		if (isIntentSafe) {
			this.activity.startActivity(intent);
		}else{
			Toast.makeText(this.activity.getApplicationContext(), "Veuillez installer Google Maps.", Toast.LENGTH_LONG).show();
			
		}
	}

}
