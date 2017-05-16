package com.st.first;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

public class BasicControlsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_controls);
    }

    public void showDate(View v) {

        DatePicker dp = (DatePicker) findViewById(R.id.dpJoinedOn);

        Log.d("First", String.format("%d-%d-%d", dp.getDayOfMonth(), dp.getMonth(), dp.getYear()));
    }
}
