package com.st.first.storage;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.st.first.R;

public class SearchCoursesActivity extends Activity {
    ListView listCourses;
	EditText editCourseName;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_courses);
		listCourses = (ListView) findViewById(R.id.listCourses);
		editCourseName = (EditText) findViewById(R.id.editCourseName);
	}

	public void search(View v) {
		try {
			STDatabase dbhelper = new STDatabase(this);
			SQLiteDatabase db = dbhelper.getReadableDatabase();
			Cursor  courses = db.query( 
					   STDatabase.COURSES_TABLE_NAME,null,
					   "upper(" + STDatabase.COURSES_NAME + ") like ?",
					   new String[] {"%" + editCourseName.getText().toString().toUpperCase() + "%"},
					   null,null,null);

			String from [] = { STDatabase.COURSES_NAME, STDatabase.COURSES_FEE, STDatabase.COURSES_DURATION};
			int to [] = { R.id.textName, R.id.textFee, R.id.textDuration};
			
		
			SimpleCursorAdapter ca  = new SimpleCursorAdapter(
					this,R.layout.course_row, courses,from,to);
			
		    listCourses.setAdapter(ca);
			
		} catch (Exception ex) {
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
		}

	}
}
