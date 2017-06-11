package com.st.first.network;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.st.first.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class GitHubUserInfoActivity extends Activity {

    private EditText editUser;
    private TextView textDetails;
    private ImageView imagePhoto;
    private static final String URL = "https://api.github.com/users/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_user_info);
        textDetails = (TextView) findViewById(R.id.textCompany);
        editUser = (EditText) findViewById(R.id.editUser);
        imagePhoto = (ImageView) findViewById(R.id.imagePhoto);
        editUser.setText("srikanthpragada");
    }

    public void getDetails(View v) {
        final Handler contentHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // process data
                try {
                    if ( msg.obj.toString().startsWith("Error")) {
                        textDetails.setText("Sorry! Unable to get details!");
                        // clear image
                        imagePhoto.setImageBitmap(null);
                        return;
                    }

                    JSONObject obj = new JSONObject(msg.obj.toString());
                    textDetails.setText(obj.get("company").toString());

                    // get avatar url
                    String avatar_url = obj.get("avatar_url").toString();
                    DownloadPhoto photoThread = new DownloadPhoto(avatar_url);
                    photoThread.start();

                } catch (Exception ex) {
                    Log.e("GitHub", ex.getMessage());
                }
            }
        };

        DownloadThread dt = new DownloadThread(contentHandler, editUser.getText().toString());
        dt.start();

    }


    class DownloadThread extends Thread {
        Handler handler;
        String user;

        public DownloadThread(Handler handler, String user) {
            this.handler = handler;
            this.user = user;
        }

        public void run() {
            try {
                URL sourceUrl = new URL(URL + this.user);
                InputStream is = null;
                try {
                    is = sourceUrl.openStream();
                } catch (Exception ex) {
                    Message m = handler.obtainMessage();
                    m.obj = "Error : Unable to access URL";
                    handler.sendMessage(m); // update view
                    return;
                }
                // Get the response
                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(is));
                String line = "";
                StringBuffer content = new StringBuffer("");
                while ((line = rd.readLine()) != null) {
                    content.append(line);
                }
                rd.close();
                Message m = handler.obtainMessage();
                m.obj = content;
                handler.sendMessage(m); // update view
            } catch (Exception ex) {
                Message m = handler.obtainMessage();
                m.obj = "Error : " + ex.getMessage();
                handler.sendMessage(m); // update view
            }
        } // run
    }


    class DownloadPhoto extends Thread {
        String url;
        public DownloadPhoto(String url) {
            this.url = url;
        }
        public void run() {
            try {
                URL sourceUrl = new URL(this.url);
                InputStream is = null;
                try {
                    is = sourceUrl.openStream();
                    final Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imagePhoto.post(new Runnable() {
                        @Override
                        public void run() {
                            imagePhoto.setImageBitmap(bitmap);
                        }
                    });

                } catch (Exception ex) {
                    Log.d("ST", "Error processing stream -> " + ex.getMessage());
                }


            } catch (Exception ex) {
                Log.d("ST", "Error while using URL -> " + ex.getMessage());
            }
        } // run
    } // DownloadThread

}

