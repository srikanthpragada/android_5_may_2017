package com.st.first.storage;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.st.first.R;

import java.io.File;
import java.io.FileWriter;

public class ExternalStorageActivity extends Activity {
    private TextView textMessage;
    private int REQUEST_FOR_STORAGE = 1111;
    private int STORAGE_PERMISSION = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
        textMessage = (TextView) this.findViewById(R.id.textMessage);

        // check for permission in api 23 or above

        if ( Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                          != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_FOR_STORAGE);
            } else
                textMessage.append("Permission present\n");
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(requestCode == REQUEST_FOR_STORAGE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                STORAGE_PERMISSION = 1;
                textMessage.append("Permission  granted\n");
            }
            else
              textMessage.append("Permission NOT granted\n");
        }
    }


    public void checkAvailability(View v) {
        String state = Environment.getExternalStorageState();

        textMessage.append(state + "\n");

        if (Environment.MEDIA_MOUNTED.equals(state))
            textMessage.append("Available and writable");
        else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
            textMessage.append("Available and read only");
        else
            textMessage.append("Not available");
    }

    public void createDirectory(View v) {
        try {
            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File mydir = new File(dir, "demo");
            textMessage.append("\n External Storage Directory  :" + mydir.getAbsolutePath());

            if (!mydir.exists()) {
                if (mydir.mkdir())
                    textMessage.append("\nCreated demo directory...");
                else {
                    textMessage.append("\nCould not create demo directory...");
                    return;
                }
            } else
                textMessage.append("\nUsing demo directory...");


        } catch (Exception ex) {
            textMessage.append("\nError: " + ex.getMessage());
        }

    }

    public void createFile(View v) {
        try {
            File dir = this.getExternalFilesDir(null);
            textMessage.append("\n" + dir.getAbsolutePath());
            File file = new File(dir, "numbers.txt");
            FileWriter fw = new FileWriter(file);
            for (int i = 1; i <= 10; i++)
                fw.write(i + "\n");
            fw.close();

        } catch (Exception ex) {
            textMessage.append("\nError: " + ex.getMessage());
        }
    }


    public void listPhotos(View v) {
        try {
            File dir = Environment.getExternalStoragePublicDirectory
                        (Environment.DIRECTORY_DCIM );
            dir = new File(dir,"camera");
            if (dir.exists())
                textMessage.append("\n" + dir.getAbsolutePath() + " is existing");
            else {
                textMessage.append("\n" + dir.getAbsolutePath() + " is not found!");
                return;
            }

            String[] files = dir.list();

            for (String file : files) {
                textMessage.append("\n" + file);
            }
        } catch (Exception ex) {
            textMessage.append("\nError: " + ex.getMessage());
        }
    }
}
