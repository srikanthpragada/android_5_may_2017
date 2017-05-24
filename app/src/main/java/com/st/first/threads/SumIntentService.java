package com.st.first.threads;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

public class SumIntentService extends IntentService {


    public SumIntentService() {
        super("SumIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Code", "Intent Service onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Code", "Intent Service onDestroy()");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        int number = intent.getIntExtra("number",0);
        Log.d("First", "Procesing number =  " + number);
        int sum =0;
        for(int i =1; i <= number; i ++) {
            try {
                Thread.sleep(1000);
            }
            catch(Exception ex) {

            }
            sum += i;
        }

        Log.d("First", "Sum = " + sum);


    }


}
