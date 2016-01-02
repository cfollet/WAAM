package fr.uppa.waam.listeners;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import fr.uppa.waam.views.WallActivity;

public class OnNextButtonClickListener implements OnClickListener {

	WallActivity activity;
	Button nextButton;

	public OnNextButtonClickListener(WallActivity activity, Button nextButton) {
		super();
		this.activity = activity;
		this.nextButton = nextButton;
		this.nextButton.setEnabled(this.activity.isNextButtonEnabled());
	}

	@Override
	public void onClick(View v) {
		this.activity.incrementPage();
		this.activity.populate(this.activity.getMessages());
	}

}
