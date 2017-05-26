package com.st.first;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class IntentDemoActivity extends Activity {

    EditText editAction, editCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_demo);

        editAction = (EditText) findViewById(R.id.editAction);
        editCategory = (EditText) findViewById(R.id.editCategory);
    }

    public void invoke(View v) {

        Intent intent = new Intent();
        if ( editAction.getText().toString().trim().length() != 0 )
             intent.setAction( editAction.getText().toString());
        startActivity(intent);

    }
}
