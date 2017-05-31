package com.st.first.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.st.first.R;

public class FragmentsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);
    }

    public void addFragment(View v) {
        FragmentManager fm =  getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add( R.id.fragmentContainer, new SecondFragment(), "second");
        ft.commit();
    }

    public void removeFragment(View v) {
        FragmentManager fm =  getFragmentManager();
        Fragment f = fm.findFragmentByTag("second");
        if ( f == null) {
            Toast.makeText(this,"Sorry! Fragment Not Found", Toast.LENGTH_LONG).show();
            return;
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(f);
        ft.commit();
    }
}
