package com.st.first.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.TextView;

import com.st.first.R;


public class CoursesFragmentsActivity extends Activity {
    CourseDescriptionFragment f;
    int mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_fragments);
        mode = getMode();
        Log.d ("Anroid Demo", "Rotation : " + mode);
    }

    // 1 = portrain , 2 = landscape
    public int getMode()
    {
        final int rotation = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();

        switch (rotation) {
            case Surface.ROTATION_90:
            case Surface.ROTATION_270:
                return 2;  // landscape
        }
        return 1; // portrait
    }

    public void setCourseDescription(String desc) {
        // start a new activity if in portrain mode otherwise use another fragment
        if ( mode == 2) // landscape
        {
            f = (CourseDescriptionFragment) getFragmentManager().findFragmentById(R.id.courseDescFragment);
            Log.i("Course", "Got fragment : " + f);
            f.setDescription(desc);
        }
        else
        {
            // invovke another activity
            Intent intent  = new Intent(this, CourseDescriptionActivity.class);
            intent.putExtra("description", desc);
            startActivity(intent);
        }
    }
}
