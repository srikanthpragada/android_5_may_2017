package com.st.first.media;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.st.first.R;

public class CameraCaptureImageActivity extends Activity {
	
	private static final int REQUEST_CODE = 100;
	public static final int MEDIA_TYPE_IMAGE = 1;
	private ImageView photo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_camera_capture_image);
		photo = (ImageView) findViewById(R.id.photo);
	}
	
	public void activateCamera(View v) 
	{
	    // create Intent to take a picture and return control to the calling application
	    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    // start the image capture Intent
	    startActivityForResult(intent, REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
				Bitmap bitmap = (Bitmap) data.getExtras().get("data");
				photo.setImageBitmap(bitmap);
	        }
	        else
	        {
				photo.setImageBitmap(null);
	            Toast.makeText(this, "Failed to take a Snap", Toast.LENGTH_LONG).show();
	        }
	    }
	}

}
