package com.st.first.storage;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.st.first.R;

public class ListCourseDbRowsActivity extends ListActivity {
	@Override
	public void onResume() {
		super.onResume();
		try {
			STDatabase dbhelper = new STDatabase(this);
			SQLiteDatabase db = dbhelper.getReadableDatabase();
			Cursor  courses = db.query( 
					  STDatabase.COURSES_TABLE_NAME,null,null,null,null,null,null);
			
			String from [] = { STDatabase.COURSES_NAME, STDatabase.COURSES_FEE, STDatabase.COURSES_DURATION};
			int to [] = { R.id.textName, R.id.textFee, R.id.textDuration};
			
		
			SimpleCursorAdapter ca  = new SimpleCursorAdapter(
					this,R.layout.course_row, courses,from,to);
			
		    getListView().setAdapter(ca);
			
		} catch (Exception ex) {
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
		}

	}
}
