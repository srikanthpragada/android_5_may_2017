package com.st.first;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class MessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("message");
        Log.d("First", "Thread : " + Thread.currentThread().getName());
        Log.d("First", message);

        Intent aintent   = new Intent(context, MessageActivity.class);
        aintent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
        aintent.putExtra("message",message);
        context.startActivity(aintent);

    }
}
