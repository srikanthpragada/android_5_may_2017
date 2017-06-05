package com.st.first.storage;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.st.first.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DeleteCoursesActivity extends Activity {
    private final String  FILENAME = "courses.txt";
    private Spinner spinnerCourses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_courses);
        spinnerCourses = (Spinner)  findViewById(R.id.spinnerCourses);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        loadCourses();

    }

    public void deleteCourse(View v) {
        // get selected value
        String course = spinnerCourses.getSelectedItem().toString();

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader br = new BufferedReader( new InputStreamReader(fis));
            ArrayList<String> courses = new ArrayList<>();
            while(true) {
                String line = br.readLine();
                if (line == null)
                    break;
                String [] parts = line.split(",");
                if (parts.length > 0)
                {
                    if (!parts[0].equals(course))
                        courses.add(line);  // add except course to delete
                }
            }
            br.close();
            fis.close();

            // write all lines from ArrayList back to file
            FileOutputStream fos = openFileOutput(FILENAME, MODE_PRIVATE);
            PrintWriter pw = new PrintWriter(fos);
            for(String line : courses)
            {
                pw.println(line);
            }
            pw.close();
            fos.close();

            loadCourses();
        }
        catch(Exception ex)
        {
            Log.e("Storage", ex.getMessage());
        }

    }

    public void loadCourses() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader br = new BufferedReader( new InputStreamReader(fis));
            ArrayList<String> courses = new ArrayList<>();
            while(true) {
                String line = br.readLine();
                if (line == null)
                    break;
                String [] parts = line.split(",");
                if (parts.length > 0)
                {
                    courses.add( parts[0]);
                }
            }
            br.close();
            ArrayAdapter<String> adpater = new ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, courses);
            spinnerCourses.setAdapter(adpater);
        }
        catch(Exception ex)
        {
            Log.e("Storage", ex.getMessage());
        }
    }

}
