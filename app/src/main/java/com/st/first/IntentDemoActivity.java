package com.st.first;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class IntentDemoActivity extends Activity {

    EditText editAction, editCategory,editData, editMIME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_demo);

        editAction = (EditText) findViewById(R.id.editAction);
        editCategory = (EditText) findViewById(R.id.editCategory);
        editData = (EditText) findViewById(R.id.editData);
        editMIME = (EditText) findViewById(R.id.editMIME);
    }

    public void invoke(View v) {

        Intent intent = new Intent();
        if ( editAction.getText().toString().trim().length() != 0 )
            intent.setAction( editAction.getText().toString());

        if ( editCategory.getText().toString().trim().length() != 0)
             intent.addCategory(editCategory.getText().toString());


        if ( editData.getText().toString().trim().length() != 0)
            intent.setData(Uri.parse(editData.getText().toString()));

        if ( editMIME.getText().toString().trim().length() != 0)
            intent.setType(editMIME.getText().toString());

        try {
            startActivity(intent);
        }
        catch(Exception ex) {
            Log.d("First", ex.getMessage());
        }

    }


}
