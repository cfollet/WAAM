package fr.uppa.waam.tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;
import fr.uppa.waam.models.Message;
import fr.uppa.waam.util.ServiceHandler;
import fr.uppa.waam.views.WallActivity;

public class SendMessageTask extends AsyncTask<Message, Void, Void> {
	private static final String BASE_URL = "http://www.iut-adouretud.univ-pau.fr/~olegoaer/waam/newMessage.php";

	private volatile WallActivity activity;
	private ProgressDialog progress;

	public SendMessageTask(WallActivity activity) {
		this.activity = activity;
		this.progress = new ProgressDialog(this.activity);
	}

	@Override
	protected Void doInBackground(Message... params) {
		ServiceHandler handler = new ServiceHandler();
		handler.makeServiceCall(SendMessageTask.BASE_URL, ServiceHandler.POST, params[0].toNameValuePairs());
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		this.progress.dismiss();
		Toast.makeText(this.activity.getApplicationContext(), "Le message à été envoyé", Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onPreExecute() {
		progress.setTitle("Veuillez patienter");
		progress.setMessage("Envoi du message en cours ...");
		progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progress.show();
	}

}
