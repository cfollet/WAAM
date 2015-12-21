package fr.uppa.waam.tasks;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import fr.uppa.waam.models.GeoLocation;
import fr.uppa.waam.models.Message;
import fr.uppa.waam.util.ServiceHandler;
import fr.uppa.waam.views.WallActivity;

public class RetrieveMessages extends AsyncTask<GeoLocation, Void, List<Message>> {

	private static final String BASE_URL = "http://www.iut-adouretud.univ-pau.fr/~olegoaer/waam/wallMessages.php";
	private volatile WallActivity activity;
	private ProgressDialog progress;

	public RetrieveMessages(WallActivity activity) {
		this.activity = activity;
		this.progress = new ProgressDialog(this.activity);
	}

	@Override
	protected void onPreExecute() {
		this.progress.setTitle("Veuillez patienter");
		this.progress.setMessage("Récupération des messages du mur en cours...");
		this.progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		this.progress.show();
	}

	@Override
    protected List<Message> doInBackground(GeoLocation... params) {
    	List<Message> messages = new ArrayList<Message>();
		try {
            ServiceHandler sh = new ServiceHandler();
            String jsonString = sh.makeServiceCall(BASE_URL, ServiceHandler.GET, params[0].toNameValuePairs());
            if (jsonString != null) {
            	JSONObject jsonObj = new JSONObject(jsonString);
            	int count = jsonObj.getInt("count");
            	JSONArray items = jsonObj.getJSONArray("items");
            	for (int i = 0; i < count; i++) {
            		messages.add(new Message(items.getJSONObject(i)));
				}
			}
		}catch(Exception e){
			Log.i("test", e.getCause().toString());
		}
		
		return messages;
    }

	@Override
	protected void onPostExecute(List<Message> result) {
		if (this.progress.isShowing()) {
			this.progress.dismiss();
		}
		this.activity.populate(result);
	}

}
