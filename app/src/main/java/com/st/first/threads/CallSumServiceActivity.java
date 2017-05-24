package com.st.first.threads;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import com.st.first.R;

public class CallSumServiceActivity extends Activity {

    EditText editNumber;
    TextView textSum;
    int sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_sum_service);
        editNumber = (EditText) findViewById(R.id.editNumber);
        textSum = (TextView) findViewById(R.id.textSum);

    }

    // Calculates sum with service
    public void calculateSum(View v) {

        Intent intent =  new Intent(this, SumService.class);
        intent.putExtra("number", Integer.parseInt(editNumber.getText().toString()));
        startService(intent);
    }

    // Calculates sum with Intent service

    public void calculateSum2(View v) {
        Intent intent =  new Intent(this, SumIntentService.class);
        intent.putExtra("number", Integer.parseInt(editNumber.getText().toString()));
        startService(intent);
    }


    public void showDateTime(View v) {
        Toast.makeText(this, new Date().toString(), Toast.LENGTH_LONG).show();
    }


}
