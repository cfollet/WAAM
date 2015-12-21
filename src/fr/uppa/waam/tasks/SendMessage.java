package fr.uppa.waam.tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import fr.uppa.waam.models.Message;
import fr.uppa.waam.views.MessageActivity;
import fr.uppa.waam.views.WallActivity;

public class SendMessage extends AsyncTask<Message, Void, Void> {
	private static final String BASE_URL = "http://www.iut-adouretud.univ-pau.fr/~olegoaer/waam/newMessage.php";

	private volatile WallActivity activity;
	private ProgressDialog progress; 
    
	public SendMessage(WallActivity activity){
			this.activity = activity;
	    	this.progress = new ProgressDialog(this.activity);
	}
	
	 @Override
	    protected void onPreExecute() {                           
	        progress.setTitle("Veuillez patienter");
	        progress.setMessage("Envoi du message en cours ...");
	        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);      
	        progress.show();
	 }
	
	@Override
	protected Void doInBackground(Message... params) {	
		ServiceHandler handler = new ServiceHandler();
		handler.makeServiceCall(SendMessage.BASE_URL, ServiceHandler.POST, params[0].toNameValuePairs());
		return null;
	}
	
}
