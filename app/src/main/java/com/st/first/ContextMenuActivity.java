package com.st.first;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ContextMenuActivity extends Activity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);
        tv = (TextView) findViewById(R.id.textStory);
        registerForContextMenu(tv);  // 1
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.context_menu, menu); // 2
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {  // 3
        switch( item.getItemId())
        {
            case  R.id.optClear :
                tv.setText("");
                return true;
            case  R.id.optUpper :
                tv.setText(tv.getText().toString().toUpperCase());
                return true;
            default :
                return super.onContextItemSelected(item);
        }
    }


}
