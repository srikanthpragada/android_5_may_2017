package com.st.first;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import java.util.Date;

public class SendMessageActivity extends Activity {
    TimeTickReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        receiver = new TimeTickReceiver();
    }

    public void sendMessage(View v) {
        Intent intent = new Intent("com.st.first.MESSAGE");
        intent.putExtra("message", new Date().toString());
        sendBroadcast(intent);
    }

    public void register(View v) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_TIME_TICK);
        registerReceiver(receiver,filter);
    }

    public void unregister(View v) {
         unregisterReceiver(receiver);
    }
}
