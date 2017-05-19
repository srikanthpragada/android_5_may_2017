package com.st.first;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;

public class DatePickerDialogActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_picker_dilaog);
	}

	public void showDatePicker(View v) {
		DialogFragment fragment = new DatePickerDialogFragment();
		fragment.show(getFragmentManager(), "datepicker");
	}


}
