package com.st.first;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

public class ReadUserPreferencesActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_user_preferences);
	}

	public void  readPrefs(View v) {
		// def shared pref name - com.st.first/shared_prefs/com.st.first_preferences.xml
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		String uname = prefs.getString("uname","none");
		String password = prefs.getString("password","none");
		boolean autoLogin = prefs.getBoolean("autologin",false);

		TextView tv = (TextView) findViewById(R.id.textPrefs);
		tv.setText( uname + "," + password + "," + autoLogin);

	}
}

