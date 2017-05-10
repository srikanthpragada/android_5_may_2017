package com.st.first;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView tv = (TextView) findViewById(R.id.textMessage);
        Intent intent = getIntent();
        tv.setText(intent.getStringExtra("message"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Second", "onResume()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Second", "onDestroy()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Second", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Second", "onStop()");
    }

}
