package com.st.first;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class GridViewActivity extends Activity {

	GridView gv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_view);
		gv = (GridView) findViewById( R.id.gridView);
		
		
		Calendar c = Calendar.getInstance();
		int year = c.get( Calendar.YEAR);
		
        ArrayList<String> years = new ArrayList<String>();
		
		for ( int i = year - 9; i <= year; i ++)
			years.add( String.valueOf(i));
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,
				    android.R.layout.simple_list_item_1, years);
		
		gv.setAdapter(adapter);
	}


}
