package com.st.first.media;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.st.first.R;

public class SendEmailActivity extends Activity {

	private EditText editEmail;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendemail);
		editEmail = (EditText)  this.findViewById(R.id.editEmail);
    }
	
    public void sendEmail(View v) {
      	Intent email = new Intent(Intent.ACTION_SEND);
    	email.putExtra(Intent.EXTRA_EMAIL,
				   new String[]{ "srikanthpragada@gmail.com",
						         editEmail.getText().toString()});
    	email.putExtra(Intent.EXTRA_SUBJECT, "Test");
    	email.putExtra(Intent.EXTRA_TEXT, "Sending From Android");
    	email.setType("message/rfc822");
    	// startActivity(Intent.createChooser(email, "Choose an Email client :"));
		try {
			startActivity(email);
		}
		catch(Exception ex)
		{
			Log.d("First","No email client app is installed!");
		}
    } 

}
