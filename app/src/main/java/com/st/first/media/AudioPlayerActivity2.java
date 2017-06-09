package com.st.first.media;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.st.first.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AudioPlayerActivity2 extends Activity {
    ListView listSongs;
    ArrayList<Song> songs;
    EditText editTitle;
    LinearLayout songsView;

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // search for songs on restore
        searchSongs(null);
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_audio_player);

        // get the list of files and place them in ListView
        listSongs = (ListView) this.findViewById(R.id.listSongs);
        editTitle = (EditText) this.findViewById(R.id.editTitle);
        songsView = (LinearLayout) this.findViewById(R.id.songsView);
        songsView.setVisibility(View.INVISIBLE);

        listSongs.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> view, View item,
                                    int position, long id) {

                // Play song using  built-in audio player
                String filename = songs.get(position).getFilename();
                Uri audio = Uri.parse("file://" +filename);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(audio, "audio/*");
                startActivity(intent);
            }
        });
    }

    public void searchSongs(View v) {
        bindSongsToListView(editTitle.getText().toString());
        if (songs.size() > 0)
            songsView.setVisibility(View.VISIBLE);
        else
            songsView.setVisibility(View.INVISIBLE);

    }

    private void bindSongsToListView(String title) {
        songs = new ArrayList<Song>();

        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                   null,
                   MediaStore.Audio.Media.TITLE + " like  ? ",
                   new String[] { "%"  + title + "%"},
                   null);
        ArrayList<Map<String,String>> songsList = new ArrayList< Map<String,String>>();

        while (cursor.moveToNext()) {
            Song s = new Song();
            s.setFilename(cursor.getString(cursor.getColumnIndex( MediaStore.Audio.Media.DATA)));
            Log.d("AudioPlayerActivity", s.getFilename());
            s.setTitle( cursor.getString(cursor.getColumnIndex( MediaStore.Audio.Media.TITLE)));
            long duration =  cursor.getLong(cursor.getColumnIndex( MediaStore.Audio.Media.DURATION));

            s.setDuration( String.format("%5.2f Mins", (duration / 1000.0) / 60));
            s.setSinger( cursor.getString(cursor.getColumnIndex( MediaStore.Audio.Media.ARTIST)));
            songs.add(s);

            Map<String, String> mapobject = convertSongToMap(s);
            songsList.add(mapobject);
        }


        SimpleAdapter adapter = new SimpleAdapter(this, songsList, R.layout.song,
                new String[]{"title", "duration", "singer"},
                new int[]{R.id.textTitle, R.id.textDuration, R.id.textSinger});

        listSongs.setAdapter(adapter);
    }

    public Map<String, String> convertSongToMap(Song s) {

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("title", s.getTitle());
        map.put("duration", s.getDuration());
        map.put("singer", s.getSinger());
        return map;

    }
}