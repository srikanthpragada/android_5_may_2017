package com.st.first;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class FirstActivity extends Activity {
    static final int THIRD = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("First", "onResume()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("First", "onDestroy()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("First", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("First", "onStop()");
    }

    public void callSecond(View v) {
        Intent  intent = new Intent(this, SecondActivity.class);
        intent.putExtra("message","Hello!");
        startActivity(intent);
    }

    public void callThird(View v) {
        Intent  intent = new Intent(this, ThirdActivity.class);
        startActivityForResult(intent, THIRD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode ==  THIRD)
        {
            if (resultCode == Activity.RESULT_OK) {
                String message =  data.getStringExtra("message");
                Log.d("first","Meesage : " +  message);
                //Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }

    }
}
