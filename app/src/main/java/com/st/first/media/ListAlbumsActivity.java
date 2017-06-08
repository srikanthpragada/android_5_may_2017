package com.st.first.media;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.TextView;

import com.st.first.R;

public class ListAlbumsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_albums);

		Log.i("First", MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI.toString());
		Log.i("First", MediaStore.Audio.Albums.ALBUM);
		Log.i("First", MediaStore.Audio.Albums.ARTIST);

		Cursor cur = getContentResolver().query(
				MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, null, null, null,
				null);

		if ( cur == null)
			  return;

		TextView textAlbums = (TextView) this.findViewById(R.id.textAlbums);

		if  (cur.getCount() == 0)
			return;

		while (cur.moveToNext()) {
			// int albumIdIndex =
			// cur.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
			int albumIndex = cur.getColumnIndex(MediaStore.Audio.Albums.ALBUM);
			int artistIndex = cur
					.getColumnIndex(MediaStore.Audio.Albums.ARTIST);
			// int albumId = cur.getInt(albumIdIndex);
			String name = cur.getString(albumIndex);
			String artist = cur.getString(artistIndex);
			textAlbums.append(String.format("%s - %s\n", name, artist));

		}

	}

}
