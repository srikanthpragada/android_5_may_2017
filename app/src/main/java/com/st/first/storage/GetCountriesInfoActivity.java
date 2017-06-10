package com.st.first.storage;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import com.st.first.R;

public class GetCountriesInfoActivity extends Activity {
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.activity_countries_info);
		
		Cursor c =  getContentResolver().query(
				 CountryContentProvider.CONTENT_URI, null,null,null,null);

		TextView textCountries = (TextView) this.findViewById( R.id.textCountries);
		
		textCountries.setText("");

        while (c.moveToNext()) {
           textCountries.append(  String.format("%s - %s \n",
        		   c.getString( c.getColumnIndex( CountryContentProvider.COUNTRY_NAME)),
        		   c.getString( c.getColumnIndex( CountryContentProvider.COUNTRY_CAPITAL))));
        		   
        }
        c.close();
	}

}
