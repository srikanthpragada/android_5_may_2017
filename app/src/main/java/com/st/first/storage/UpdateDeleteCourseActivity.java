package com.st.first.storage;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.st.first.R;

public class UpdateDeleteCourseActivity extends Activity {

    EditText editCourseId, editCourseName, editCourseFee, editCourseDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_course);

        editCourseId = (EditText) findViewById(R.id.editCourseId);
        editCourseName = (EditText) findViewById(R.id.editCourseName);
        editCourseFee = (EditText) findViewById(R.id.editCourseFee);
        editCourseDuration = (EditText) findViewById(R.id.editCourseDuration);
    }

    public void getCourseDetails(View v) {

        try {
            STDatabase dbhelper = new STDatabase(this);
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor courseCursor = db.query(
                    STDatabase.COURSES_TABLE_NAME,
                    null,
                    STDatabase.COURSES_ID + " = ?",
                    new String[]{editCourseId.getText().toString()},
                    null, null, null, null);

            if (courseCursor.moveToFirst())  // found course, display details
            {
                editCourseName.setText(courseCursor.getString(courseCursor.getColumnIndex(STDatabase.COURSES_NAME)));
                editCourseDuration.setText(courseCursor.getString(courseCursor.getColumnIndex(STDatabase.COURSES_DURATION)));
                editCourseFee.setText(courseCursor.getString(courseCursor.getColumnIndex(STDatabase.COURSES_FEE)));
            } else {
                Log.d("Storage", "Course Not Found!");
                Toast.makeText(this, "Course Not Found!", Toast.LENGTH_LONG).show();
                editCourseName.setText("");
                editCourseDuration.setText("");
                editCourseFee.setText("");

            }

            courseCursor.close();
            db.close();

        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void updateCourse(View v) {

    }

    public void deleteCourse(View v) {

        try {
            STDatabase dbhelper = new STDatabase(this);
            SQLiteDatabase db = dbhelper.getWritableDatabase();

            int count = db.delete(STDatabase.COURSES_TABLE_NAME,
                                  STDatabase.COURSES_ID + " = ?",
                                  new String[]{editCourseId.getText().toString()}
            );

            if (count == 1) {
                editCourseName.setText("");
                editCourseDuration.setText("");
                editCourseFee.setText("");
                Toast.makeText(this, "Deleted Course Successfully!", Toast.LENGTH_LONG).show();
            } else {

                Log.d("Storage", "Could not delete course!");
                Toast.makeText(this, "Could not delete course!", Toast.LENGTH_LONG).show();
            }

            db.close();

        } catch (Exception ex) {
            Log.d("Storage","Error -->" +  ex.getMessage());
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}


