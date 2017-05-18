package com.st.first;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

public class NotifyActivity extends Activity {
    int count = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notify);
	}


	public void sendNotification(View v) {

		NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		Notification.Builder b = new Notification.Builder(this);
		b.setSmallIcon( R.drawable.mail);

		Bitmap bm = BitmapFactory.decodeResource
				(getResources(), R.drawable.settings);
		b.setLargeIcon(bm);

	    Intent notificationIntent = new Intent(this, MainActivity.class);
		PendingIntent contentIntent =
				PendingIntent.getActivity(this, 1, notificationIntent,0);
		b.setContentIntent(contentIntent);

		b.setContentText("Context Text");
		b.setContentTitle("Content Title");
		b.setTicker( "Ticket Text");

		b.setDefaults(Notification.DEFAULT_SOUND);
		b.setWhen(System.currentTimeMillis());
		nm.notify(2, b.build());
	}



}
