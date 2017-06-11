package com.st.first.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.st.first.R;

public class JavaNetUrlRead extends Activity {
	private EditText editUrl;
	private TextView textContents;
	private ProgressDialog pd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_url_read);
		textContents = (TextView) findViewById(R.id.textContents);
		editUrl = (EditText) findViewById(R.id.editUrl);
		editUrl.setText("http://www.srikanthtechnologies.com/schedule.xml");
	}

	public void showContents(View clickedButton) {
		pd  = ProgressDialog.show(this, "Downloading",
				"Reading content. Please wait...");

		DownloadThread dt = new DownloadThread(editUrl.getText().toString());
		dt.start();
	}

	class DownloadThread extends Thread {
		String url;
		public DownloadThread(String url) {
			this.url = url;
		}
		public void run() {

			final StringBuffer content = new StringBuffer("");
			try {
				URL sourceUrl = new  URL(this.url);
				InputStream is = sourceUrl.openStream();
				// Get the response
				BufferedReader rd = new BufferedReader(
						new InputStreamReader(is));
				String line = "";

				while ((line = rd.readLine()) != null) {
					content.append(line);
				}
				rd.close();
			} catch (Exception ex) {
			    content.append("Sorry! Invalid URL. Please try again!");
			}


			textContents.post( new Runnable() {
				public void run() {
					textContents.setText(content);
					pd.dismiss();

				}
			});


		} // run
	}; // DownloadThread
} // UrlRead
