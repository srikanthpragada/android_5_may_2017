package com.st.first.storage;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

public class CountryContentProvider extends  ContentProvider {
	public static final Uri CONTENT_URI = 
            Uri.parse("content://com.st.countries");
	
	public static final String  COUNTRY_ID ="_id";
	public static final String  COUNTRY_NAME ="country_name";
	public static final String  COUNTRY_CAPITAL ="country_capital";
	
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		return "vnd.android.cursor.item/country";
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		MatrixCursor c = new MatrixCursor(new String[] { COUNTRY_ID, COUNTRY_NAME, COUNTRY_CAPITAL});
		
		c.addRow( new String [] { "1", "India","New Delhi"});
		c.addRow( new String [] {"2", "China","Beijing"});
		c.addRow( new String [] {"3",  "Japan","Tokyo"});

        return c;	

	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
