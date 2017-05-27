package com.st.first;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class AnotherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        Intent intent = getIntent();

        Log.d("First",intent.getData().toString());
    }
}
