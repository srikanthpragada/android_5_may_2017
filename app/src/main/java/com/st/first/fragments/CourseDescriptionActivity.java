package com.st.first.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.WindowManager;

import com.st.first.R;


public class CourseDescriptionActivity extends Activity {
    CourseDescriptionFragment f;
    int mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_description);
        Log.d("Demo","Mode :" + getMode());

        // if in landscape mode then display CoursesFragmentsActivity
        if (getMode() == 2) {
            Intent intent  = new Intent(this, CoursesFragmentsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        else {
            // get course description from intent
            String desc = getIntent().getStringExtra("description");
            CourseDescriptionFragment f = (CourseDescriptionFragment) getFragmentManager().findFragmentById(R.id.courseDescFragment);
            f.setDescription(desc);
        }

    }

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


}
