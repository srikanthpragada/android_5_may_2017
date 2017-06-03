package com.st.first.storage;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.PrintWriter;

import com.st.first.R;

public class AddCourseActivity extends Activity {
    private final String  FILENAME = "courses.txt";
    private FileOutputStream fileStream;
    private PrintWriter writer;
    private EditText editFee,editName, editDuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course_row);
        editName = (EditText) findViewById(R.id.editName);
        editDuration = (EditText) findViewById(R.id.editDuration);
        editFee = (EditText) findViewById(R.id.editFee);
    }

    @Override
    public void onStart() {
        super.onStart();
        openFile();
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            writer.close();
            fileStream.close();
            Log.d("Storage","onStop() closed files");
        }
        catch(Exception ex) {
            Log.e("Storage","Error closing file :" + ex.getMessage());
        }
    }

    public void openFile() {
        try {
            fileStream = this.openFileOutput(FILENAME, MODE_APPEND);
            writer = new PrintWriter(fileStream, true);
            Log.d("Storage","Opening files!");
        }
        catch(Exception ex) {
            Log.e("Storage", "Error opening file : " + ex.getMessage());
        }
    }

    public void addCourse(View v) {
        String course;

        course = editName.getText().toString()+ ","
                 + editDuration.getText().toString() + "," +
                    editFee.getText().toString();

        writer.println(course);
        Log.d("Storage","Added Course " + course);
        Toast.makeText(this,"Added Course Sucessfully!", Toast.LENGTH_SHORT).show();
    }
}
