package fr.uppa.waam.listeners;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/**
 * This class is used to manage the text message and display the characters left allowed.
 * @author cfollet
 */
public class MessageTextChangedListener implements TextWatcher {
	
	private static int MESSAGE_LIMIT = 500;
	private TextView inputCount;

	public MessageTextChangedListener(TextView inputCount) {
		this.inputCount = inputCount;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterTextChanged(Editable s) {
		 this.inputCount.setText(MESSAGE_LIMIT - s.toString().length() + "/"+MESSAGE_LIMIT);
	}

}
