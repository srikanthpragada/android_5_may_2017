package com.st.first.media;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.st.first.R;

public class CameraDemoActivity extends Activity {
	
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	public static final int MEDIA_TYPE_IMAGE = 1;
	private Uri fileUri;
	private File mediaStorageDir, mediaFile;
	private ImageView photo;
	private TextView filename;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_camerademo);
		photo = (ImageView) findViewById(R.id.photo);
		filename = (TextView) findViewById(R.id.filename);
	}
	
	public void activateCamera(View v) 
	{
	    // create Intent to take a picture and return control to the calling application
	    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

	    fileUri = getOutputMediaFileUri(); // create a file to save the image
		if ( fileUri != null ) {
			intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
			Log.d("First"," File : " + fileUri.toString());
			// start the image capture Intent
			startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
		}
		else
			Log.d("First","Unable to create output file!");
	}
	
	
	private  Uri getOutputMediaFileUri(){
	    mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),"Camera");
		//mediaStorageDir = getExternalFilesDir(null);

	    // Create the storage directory if it does not exist
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            Log.d("First", "Failed to create directory");
	            return null;
	        }
	    }

	    // Create a media file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    mediaFile = new File(mediaStorageDir.getPath() + File.separator +  "IMG_"+ timeStamp + ".jpg");
	    return Uri.fromFile(mediaFile);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
				filename.setText( mediaFile.toString());
				Log.d("First", "Photo file : "  +  mediaFile);
				Bitmap bitmap = BitmapFactory.decodeFile(mediaFile.toString());
				if ( bitmap != null) {
					photo.setImageBitmap(bitmap);
					Log.d("First", "Stored photo in "  +  mediaFile);
				}
				else
					Log.d("First", "Could not load photo from : "  +  mediaFile);

	        }
	        else
	        {
				photo.setImageBitmap(null);
	            Toast.makeText(this, "Could not save image to " +  mediaFile, Toast.LENGTH_LONG).show();
	        }
	    }
	}

}
