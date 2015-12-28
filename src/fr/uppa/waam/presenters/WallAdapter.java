package fr.uppa.waam.presenters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import fr.uppa.waam.R;
import fr.uppa.waam.models.Message;
import fr.uppa.waam.util.DataHandler;

public class WallAdapter extends ArrayAdapter<Message> {
	/** The message list need to be sorted by seniority **/
	private List<Message> messages;

	public WallAdapter(Context context, int textViewResourceId, List<Message> messages) {
		super(context, textViewResourceId, messages);
		this.messages = messages;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.message, parent, false);
		}
		if (messages.size() > 0) {
			Message message = messages.get(position);

			ImageView genderIcon = (ImageView) convertView.findViewById(R.id.genderIcon);
			TextView content = (TextView) convertView.findViewById(R.id.content);
			TextView timestamp = (TextView) convertView.findViewById(R.id.timestamp);
			TextView distance = (TextView) convertView.findViewById(R.id.distance);

			if (message.getGender() == Message.MALE_CODE) {
				genderIcon.setImageResource(R.drawable.gender_male);
			} else {
				genderIcon.setImageResource(R.drawable.gender_female);
			}

			content.setText(message.getContent());
			timestamp.setText(DataHandler.formatDate(message.getTimestamp()));
			distance.setText(DataHandler.formatDistance(message.getLocation().getDistance()));
		}

		return convertView;
	}

}
