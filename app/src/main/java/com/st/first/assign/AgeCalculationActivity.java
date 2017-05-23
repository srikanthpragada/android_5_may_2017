package com.st.first.assign;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import com.st.first.R;

public class AgeCalculationActivity extends Activity {

    TextView textAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_calculation);
        textAge = (TextView) findViewById(R.id.textAge);
    }

    public void calculateAgeWithDialog(View v) {

        DatePickerDialog dp = new DatePickerDialog(this, new HandleSelectedDate(), 2017, 0, 1);
        dp.show();
    }

    ;

    public void calculateAgeWithPicker(View v) {

        // get selected date
        DatePicker dp = (DatePicker) findViewById(R.id.datePicker);
        calculateAge(dp.getYear(), dp.getMonth(), dp.getDayOfMonth());

    }

    public void calculateAge(int year, int month, int day) {

        Calendar dob = Calendar.getInstance();
        dob.set(year, month, day);

        Calendar today = Calendar.getInstance();

        long noyears = ((today.getTimeInMillis() - dob.getTimeInMillis()) / (24 * 60 * 60 * 1000)) / 365;

        // Toast.makeText(this, "No. years : " + noyears, Toast.LENGTH_LONG).show();
        textAge.setText( "No. Of Years : " + noyears);
    }

    class HandleSelectedDate implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            calculateAge(year, month, day);
        }
    }
}