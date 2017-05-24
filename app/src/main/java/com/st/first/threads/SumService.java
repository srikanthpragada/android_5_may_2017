package com.st.first.threads;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class SumService extends Service {
    public SumService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("First", "onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("First", "onDestroy()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int number = intent.getIntExtra("number",0);
        Log.d("First","Processing number =  " + number);
        int sum =0;
        for(int i =1; i <= number; i ++) {
            try {
                Thread.sleep(1000);
            }
            catch(Exception ex) {

            }
            sum += i;
        }

        Log.d ("First","Sum = " + sum);
        stopSelf(); // stop service
        return  START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
