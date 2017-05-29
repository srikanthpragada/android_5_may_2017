package com.st.first;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;


public class LogOutgingCall extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("First", "Calling " );
        String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        Log.d("First", phoneNumber);

    }
}
