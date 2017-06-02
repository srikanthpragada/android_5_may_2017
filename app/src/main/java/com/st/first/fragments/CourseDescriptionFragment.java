package com.st.first.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.st.first.R;


public class CourseDescriptionFragment extends Fragment {

    TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Course","OnCreateView()");
        // Inflate the layout for this fragment
        View  view =  inflater.inflate(R.layout.fragment_course_description, container,false);
        return view;
    }

    public void setDescription(String desc) {
       tv = (TextView) getView().findViewById(R.id.tvDescription);
       tv.setText(desc);
    }
}
