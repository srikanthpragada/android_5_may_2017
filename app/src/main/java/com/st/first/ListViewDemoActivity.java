package com.st.first;

import android.app.Activity;
import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewDemoActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        final ArrayList<String> courses = new ArrayList<>();
        courses.add("Android Programming");
        courses.add("Angular 2");
        courses.add("Java EE");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this.getApplicationContext(), android.R.layout.simple_list_item_1, courses);



        ListView lv = this.getListView();
        lv.setBackgroundColor(Color.BLACK);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Log.d("First", "Selected course : " + courses.get(position));
            }
        });
    }
}
