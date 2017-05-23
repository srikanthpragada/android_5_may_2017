
// this program should display ANR dialog after 5 seconds of clicking on Reset button to test ANR dialog

package com.st.first.threads;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.st.first.R;


public class ThreadSumActivity extends Activity {

	EditText editNumber;
	TextView textSum;
	int sum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sum_ui);
		editNumber = (EditText) findViewById(R.id.editNumber);
		textSum = (TextView) findViewById(R.id.textSum);
	}

	public void reset(View v) {
		Log.d("First", "Resetting...");
	}

	class SumThread extends Thread  {
		public void run() {
			int num = Integer.parseInt(editNumber.getText().toString());
			sum = 0;
			for (int i = 1; i <= num; i++) {
				try {
					Thread.sleep(1000);
					Log.d("First", "Number :" + i);
				}
				catch (Exception ex) {
				}
				sum += i;
			}
			// Run the following code in run() using UI thread
			textSum.post( new Runnable() {
				public void run() {
					textSum.setText( "Sum = " + sum);
				}
			});


		}
	}
	public void calculateSum(View v) {
		SumThread t = new SumThread();
		t.start();
	}
	


}
