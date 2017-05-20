package com.st.first;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class CountDownActivity extends Activity {

    EditText editNumber;
    ProgressBar progressBar;
    Button btnStartStop;
    int number, counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);

        btnStartStop = (Button) findViewById(R.id.btnStartStop);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editNumber = (EditText) findViewById(R.id.editNumber);
    }

    public void startStop(View v)
    {
        // find out button text
        if ( btnStartStop.getText().equals("Start") )
        {
            number = Integer.parseInt(editNumber.getText().toString());
            btnStartStop.setText("Stop");
            progressBar.setMax(number);


            new Thread() {
                public void run() {
                    for(int i = number - 1 ; i >= 0 ; i --)
                    {
                        try {
                            Thread.sleep(1000);
                            progressBar.setProgress(number-i);
                        }
                        catch(Exception ex) {}
                    }
                    progressBar.setProgress(number); // complete progress

                    btnStartStop.post( new Runnable() {
                         public void run() {
                             btnStartStop.setText("Start");
                             progressBar.setProgress(0);
                         }
                    });

                }
            }.start();

            // btnStartStop.setText("Start");

        }
        else
        {
            progressBar.setMax(0);
            progressBar.setProgress(0);
            btnStartStop.setText("Start");
        }

    }
}
