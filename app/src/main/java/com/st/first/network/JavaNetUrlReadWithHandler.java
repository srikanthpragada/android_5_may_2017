package com.st.first.network;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.st.first.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class JavaNetUrlReadWithHandler extends Activity {
	private EditText editUrl;
	private TextView textContents;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_url_read);
		textContents = (TextView) findViewById(R.id.textContents);
		editUrl = (EditText) findViewById(R.id.editUrl);
		editUrl.setText("http://www.srikanthtechnologies.com/schedule.xml");
	}

	
	public void showContents(View clickedButton) {

		final ProgressDialog pd = ProgressDialog.show(this, "Downloading",
				"Reading content. Please wait...");

		final Handler contentHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				textContents.setText(msg.obj.toString());
				// Log.d("UrlRead",msg.obj.toString());
				pd.dismiss();
			}
		};

		DownloadThread dt = new DownloadThread(contentHandler,
											   editUrl.getText().toString());
		dt.start();

	}

	
	
	class DownloadThread extends Thread {
		Handler handler;
		String url;

		public DownloadThread(Handler handler, String url) {
			this.handler = handler;
			this.url = url;
		}

		public void run() {
			try {
				URL sourceUrl = new  URL(this.url);
				InputStream is = sourceUrl.openStream();
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
	}; // DownloadThread
} // UrlRead
