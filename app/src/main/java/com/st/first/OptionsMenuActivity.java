package com.st.first;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class OptionsMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_menu);

        ActionBar abar = getActionBar();
        abar.setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("First","Menu item selected!");
        switch( item.getItemId())
        {
            case android.R.id.home :
                Log.d("First","Home button clicked ");
            case  R.id.optFill:
                Log.d("First","Fill Selected");
                return true;
            case  R.id.optSave :
                Log.d("First","Save Selected");
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }

    }
}
