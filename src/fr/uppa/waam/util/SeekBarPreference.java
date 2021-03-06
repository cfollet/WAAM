package fr.uppa.waam.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import fr.uppa.waam.R;

public final class SeekBarPreference extends DialogPreference implements OnSeekBarChangeListener {
	// Namespaces to read attributes
	private static final String PREFERENCE_NS = "http://schemas.android.com/apk/res/fr.uppa.waam.util.seekbarpreference";
	private static final String ANDROID_NS = "http://schemas.android.com/apk/res/android";
	
	// Attribute names
	private static final String ATTR_DEFAULT_VALUE = "defaultValue";
	private static final String ATTR_MAX_VALUE = "maxValue";
	private static final String ATTR_MIN_VALUE = "minValue";
	
	// Default values for defaults
	private static final int DEFAULT_CURRENT_VALUE = 250;
	private static final int DEFAULT_MAX_VALUE = 500;
	private static final int DEFAULT_MIN_VALUE = 50;
	
	private static final int STEP = 50;

	// Current value
	private int mCurrentValue;
	// Real defaults
	private final int mDefaultValue;
	private final int mMaxValue;
	private final int mMinValue;

	// View elements
	private SeekBar mSeekBar;
	private TextView mValueText;

	public SeekBarPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		// Read parameters from attributes
		mMinValue = attrs.getAttributeIntValue(PREFERENCE_NS, ATTR_MIN_VALUE, DEFAULT_MIN_VALUE);
		mMaxValue = attrs.getAttributeIntValue(PREFERENCE_NS, ATTR_MAX_VALUE, DEFAULT_MAX_VALUE);
		mDefaultValue = attrs.getAttributeIntValue(ANDROID_NS, ATTR_DEFAULT_VALUE, DEFAULT_CURRENT_VALUE);
	}

	@Override
	public CharSequence getSummary() {
		// Format summary string with current value
		String summary = super.getSummary().toString();
		int value = getPersistedInt(mDefaultValue);
		return String.format(summary, value);
	}

	@Override
	protected View onCreateDialogView() {
		// Get current value from preferences
		mCurrentValue = getPersistedInt(mDefaultValue);

		// Inflate layout
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.dialog_seek_bar, null);

		// Setup minimum and maximum text labels
		((TextView) view.findViewById(R.id.min_value)).setText(Integer.toString(mMinValue));
		((TextView) view.findViewById(R.id.max_value)).setText(Integer.toString(mMaxValue));

		// Setup SeekBar
		mSeekBar = (SeekBar) view.findViewById(R.id.seek_bar);
		mSeekBar.setMax(mMaxValue - mMinValue);
		mSeekBar.setProgress(mCurrentValue - mMinValue);
		mSeekBar.setOnSeekBarChangeListener(this);

		// Setup text label for current value
		mValueText = (TextView) view.findViewById(R.id.current_value);
		mValueText.setText(Integer.toString(mCurrentValue));

		return view;
	}

	@Override
	protected void onDialogClosed(boolean positiveResult) {
		super.onDialogClosed(positiveResult);

		// Return if change was cancelled
		if (!positiveResult) {
			return;
		}

		// Persist current value if needed
		if (shouldPersist()) {
			persistInt(mCurrentValue);
		}

		// Notify activity about changes (to update preference summary line)
		notifyChanged();
	}

	@Override
	protected Object onGetDefaultValue(TypedArray a, int index) {
		return a.getInteger(index, mDefaultValue);
	}

	@Override
	public void onProgressChanged(SeekBar seek, int value, boolean fromTouch) {
		// Update current value
		mCurrentValue = value + mMinValue;
		mCurrentValue = mCurrentValue / STEP;
		mCurrentValue = mCurrentValue * STEP;
		// Update label with current value
		mValueText.setText(Integer.toString(mCurrentValue));
	}

	@Override
	protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
		if (restorePersistedValue) {
			// Restore existing state
			mCurrentValue = this.getPersistedInt(mDefaultValue);
		} else {
			// Set default state from the XML attribute
			mCurrentValue = (Integer) defaultValue;
			persistInt(mCurrentValue);
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seek) {
		// Not used
	}

	@Override
	public void onStopTrackingTouch(SeekBar seek) {
		// Not used
	}
}