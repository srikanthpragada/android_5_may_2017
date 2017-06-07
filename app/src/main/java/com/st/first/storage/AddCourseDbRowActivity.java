package com.st.first.storage;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.st.first.R;

public class AddCourseDbRowActivity extends Activity {
	private EditText editName, editFee, editDuration;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_db_course_row);
		editName = (EditText) this.findViewById(R.id.editName);
		editFee = (EditText) this.findViewById(R.id.editFee);
		editDuration = (EditText) this.findViewById(R.id.editDuration);
	}


	public void addCourse(View v) {

		// get access to writeable database
		try {
			STDatabase dbhelper = new STDatabase(this); 
			SQLiteDatabase db = dbhelper.getWritableDatabase();

			// execute insert command

			ContentValues values = new ContentValues();
			values.put( STDatabase.COURSES_NAME, editName.getText().toString());
			values.put( STDatabase.COURSES_FEE , editFee.getText().toString());
			values.put(STDatabase.COURSES_DURATION, editDuration.getText().toString());

			db.insert(STDatabase.COURSES_TABLE_NAME, null, values);
			Toast.makeText(this, "Added Course Successfully!",
					Toast.LENGTH_SHORT).show();
		} catch (Exception ex) {
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
		}

	}
}
