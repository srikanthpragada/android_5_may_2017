package com.st.first;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;


public class AlertDialogActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alter_dialog);
	}

	public void showAlert(View v) {
		AlertDialogFragment fragment = new AlertDialogFragment();
		fragment.show(getFragmentManager(), "delete");
	}


}
