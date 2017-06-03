package com.st.first.storage;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.st.first.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListCoursesActivity extends Activity {
    private final String  FILENAME = "courses.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_courses);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader br = new BufferedReader( new InputStreamReader(fis));
            ArrayList<Map<String,String>> courses = new ArrayList<>();
            while(true) {
                String line = br.readLine();
                if (line == null)
                    break;
                String [] parts = line.split(",");
                if ( parts.length > 0)
                {
                    HashMap<String,String> course = new HashMap<>();
                    course.put("name", parts[0]);
                    course.put("duration", parts[1]);
                    course.put("fee", parts[2]);
                    courses.add(course);
                }
            }
            br.close();

            SimpleAdapter adpater = new SimpleAdapter(this,
                    courses, R.layout.course_row_layout,
                    new String []  { "name","duration", "fee"},
                    new int [] { R.id.textName, R.id.textDuration, R.id.textFee});

            // get access to listview
            ListView lv = (ListView) findViewById(R.id.listCourses);
            lv.setAdapter(adpater);

        }
        catch(Exception ex)
        {
            Log.e("Storage", ex.getMessage());
        }
    }

    public void callAddCourse(View v) {
        Intent intent = new Intent(this, AddCourseActivity.class);
        startActivity(intent);
    }

}
