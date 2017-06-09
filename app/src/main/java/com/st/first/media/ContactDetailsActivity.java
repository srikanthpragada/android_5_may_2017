package com.st.first.media;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.st.first.R;

public class ContactDetailsActivity extends Activity {

	EditText editName;
	TextView textInfo;

	public void showColumnNames(Cursor cursor) {
		// display all columns of cursor
		for (int i = 0; i < cursor.getColumnCount(); i++) {
			Log.d("Column Name", String.format("%d: %s", i, cursor.getColumnName(i)));
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_details);
		editName = (EditText) this.findViewById(R.id.editName);
		textInfo = (TextView) this.findViewById(R.id.textInfo);
	}

	public void getDetails(View v) {
		textInfo.setText("");

		Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
				null, ContactsContract.Contacts.DISPLAY_NAME +  " = ?",
				new String[]  {	editName.getText().toString()},
				null);



		if (cursor.moveToFirst()) {
			String contactId = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts._ID));
			//
			// Get all phone numbers.
			//
			Cursor phones = getContentResolver().query(Phone.CONTENT_URI, null,
					Phone.CONTACT_ID + " = " + contactId, null, null);

			showColumnNames(phones);

			while (phones.moveToNext()) {
				String number = phones.getString(phones
						.getColumnIndex(Phone.NUMBER));
				int type = phones.getInt(phones.getColumnIndex(Phone.TYPE));
				switch (type) {
				case Phone.TYPE_HOME:
					textInfo.append("Home : " + number + "\n");
					break;
				case Phone.TYPE_MOBILE:
					textInfo.append("Mobile : " + number + "\n");
					break;
				case Phone.TYPE_WORK:
					textInfo.append("Work  : " + number + "\n");
					break;
				}
			}
			phones.close();
			//
			// Get all email addresses.
			//
			Cursor emails = getContentResolver().query(Email.CONTENT_URI, null,
					Email.CONTACT_ID + " = " + contactId, null, null);

			showColumnNames(emails);

			while (emails.moveToNext()) {
				String email = emails.getString(emails
						.getColumnIndex(Email.DATA));
				textInfo.append("Email : " + email + "\n");
			}
			emails.close();
		}
		else
			textInfo.setText("Contact Not Found");
		
		cursor.close();
	}
}
