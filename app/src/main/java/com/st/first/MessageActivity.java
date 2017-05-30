package com.st.first;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MessageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        String message = getIntent().getStringExtra("message");

        TextView tv = (TextView) findViewById(R.id.textMessage);
        tv.setText(message);

    }
}
