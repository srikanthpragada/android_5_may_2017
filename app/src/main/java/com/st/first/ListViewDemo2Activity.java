package com.st.first;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListViewDemo2Activity extends ListActivity {

    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        ctx = this.getApplicationContext();
        final ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("Android Programming", 40,5000));
        courses.add(new Course("Angular", 10,1500));
        courses.add(new Course("Microsoft.Net", 60,6000));


        ArrayList<Map<String,String>> data = new ArrayList<>();

        for(Course c : courses)
        {
            HashMap<String,String> course = new HashMap<>();
            course.put("title", c.getTitle());
            course.put("duration", String.valueOf(c.getDuration()));
            course.put("fee", String.valueOf(c.getFee()));

            data.add(course);
        }

        SimpleAdapter adpater = new SimpleAdapter(this.getApplicationContext(),
                data, R.layout.course_layout, new String []  { "title","duration", "fee"},
                new int [] { R.id.textTitle, R.id.textDuration, R.id.textFee});


        ListView lv = this.getListView();
        lv.setBackgroundColor(Color.BLACK);
        lv.setAdapter(adpater);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Log.d("First", "Selected course : " + courses.get(position).getTitle());
                 TextView tv = (TextView) view.findViewById(R.id.textTitle);

                 Toast.makeText(ctx , "You selected "  + tv.getText().toString(),
                        Toast.LENGTH_LONG).show();

            }
        });
    }
}
