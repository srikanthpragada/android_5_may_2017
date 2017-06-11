package com.st.first.network;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.SimpleAdapter;

import com.st.first.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListCountriesActivity extends ListActivity {
    private static final String URL = "https://restcountries.eu/rest/v2/all";
    ArrayList<Map<String,String>> countriesList =   new ArrayList<>();
    EditText editName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getCountries();

//        editName.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                return false;
//            }
//        });
    }


    public void getCountries() {
        final Handler contentHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // process data
                try {
                    JSONArray countries = new JSONArray(msg.obj.toString());

                    for( int i = 0; i < countries.length(); i ++)
                    {
                        JSONObject country = countries.getJSONObject(i);
                        HashMap<String,String> countryDetails =
                                 new HashMap<>();
                        countryDetails.put("name",country.getString("name"));
                        countryDetails.put("capital",country.getString("capital"));
                        countriesList.add(countryDetails);
                    }

                    SimpleAdapter adpater = new SimpleAdapter( getApplicationContext(),
                             countriesList,R.layout.country_details_layout,
                             new String[] {"name","capital"},
                             new int[] { R.id.textName, R.id.textCaptial});

                    getListView().setAdapter(adpater);

                } catch (Exception ex) {
                    Log.e("Countries", ex.getMessage());
                }
            }
        };

        DownloadThread dt = new DownloadThread(contentHandler);
        dt.start();

    }


    class DownloadThread extends Thread {
        Handler handler;

        public DownloadThread(Handler handler) {
            this.handler = handler;
        }

        public void run() {
            try {
                URL sourceUrl = new URL(URL);
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

}

