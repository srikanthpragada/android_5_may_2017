package com.st.first;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

public class TodayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);
    }

    public void show(View v) {
        TextView textToday = (TextView) findViewById(R.id.textToday);
        textToday.setText( new Date().toString());
    }
}
