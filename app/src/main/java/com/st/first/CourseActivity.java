package com.st.first;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CourseActivity extends Activity {
    Spinner spinnerCourses;
    RadioButton rbMorning, rbEvening;
    CheckBox checkOldStudent;
    TextView textCourseFee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        spinnerCourses = (Spinner) findViewById(R.id.spinnerCourses);
        rbMorning = (RadioButton) findViewById(R.id.radioMorning);
        rbEvening = (RadioButton) findViewById(R.id.radioEvening);
        checkOldStudent = (CheckBox) findViewById(R.id.checkOldStudent);
        textCourseFee = (TextView) findViewById(R.id.textCourseFee);

        spinnerCourses.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            calculateFee();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );

        checkOldStudent.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        Log.e ("First", "Status : " + checkOldStudent.isChecked());
                        calculateFee();
                    }
                }

        );
    }

    public void calculateFee()
    {
       // calculate fee and show
    }

    public void calculate(View v) {
        int courseFee = 4000;
        switch( spinnerCourses.getSelectedItemPosition())
        {
            case 1:  courseFee = 3500; break;
            case 2:  courseFee = 5500; break;
            case 3:  courseFee = 1500; break;
        }

        if (checkOldStudent.isChecked())
             courseFee = courseFee * 90 /100;

        if (rbMorning.isChecked())
            courseFee = courseFee * 90 /100;

        textCourseFee.setText( String.valueOf(courseFee));

    }
}
