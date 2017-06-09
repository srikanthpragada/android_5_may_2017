package com.st.first.media;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.Data;
import android.util.Log;
import android.widget.TextView;

import com.st.first.R;

public class ListContactsActivity extends Activity {
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		if(requestCode == 1) {
			if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				Log.i("First", "Permission Granted");
			}
		}
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_contacts);

		if ( Build.VERSION.SDK_INT >= 23) {
			if (checkSelfPermission(Manifest.permission.READ_CONTACTS)
					!= PackageManager.PERMISSION_GRANTED) {
				requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},1);
			}
		}

		TextView textList = (TextView) this.findViewById(R.id.textList);

		Cursor c = getContentResolver()
				.query(ContactsContract.Contacts.CONTENT_URI, // URI
						null,null,null,null);

		// display all columns coming from query
		for (int i = 0; i < c.getColumnCount(); i++) {
			Log.d("Column Name", String.format("%d: %s", i, c.getColumnName(i)));
		}

		textList.setText("");
		while (c.moveToNext()) {
			textList.append(c.getString(c.getColumnIndex(CommonDataKinds.Phone.DISPLAY_NAME))+ "\n");
		}

	}

}