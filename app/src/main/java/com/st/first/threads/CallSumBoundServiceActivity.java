package com.st.first.threads;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import com.st.first.R;

public class CallSumBoundServiceActivity extends Activity {

    EditText editNumber;
    TextView textSum;
    int sum;
    SumBoundService sumService;
    boolean bound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_sum_bound_service);
        editNumber = (EditText) findViewById(R.id.editNumber);
        textSum = (TextView) findViewById(R.id.textSum);
    }

    @Override
    protected void onStart() {
        super.onStart();
        connectToService();
}


    @Override
    protected void onStop() {
        super.onStop();
        if (bound) {
            unbindService(connection);
            bound = false;
        }
    }

    private void connectToService() {
        Intent intent = new Intent(this, SumBoundService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }




    public void unbind(View v) {
        if (bound) {
           unbindService(connection);
           bound=false;
        }


    }


    public void getSum(View v) {
        if ( !bound) {
            connectToService();
        }

        if (bound) {
              Log.d( getClass().getName(), "Thread : " + Thread.currentThread().getName());
              int sum = sumService.getSum( Integer.parseInt(editNumber.getText().toString()));
              textSum.setText ( String.valueOf(sum));
        }
    }

    private ServiceConnection connection = new ServiceConnection()
    {
        public void onServiceConnected (ComponentName className, IBinder service) {
            Log.i(this.getClass().getName(),"onServiceConnected()");
            SumBoundService.ResultBinder binder = (SumBoundService.ResultBinder) service;
            sumService = binder.getService();
            bound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            Log.i( this.getClass().getName(),"onServiceDisconnected()");
            bound = false;
        }
    };


}
