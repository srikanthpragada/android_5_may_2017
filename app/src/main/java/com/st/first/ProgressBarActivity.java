package com.st.first;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

public class ProgressBarActivity extends Activity {
	ProgressDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress_bar);
	}
    
	public void download(View v){
		 dialog = ProgressDialog.show(this,"Dowload","Downloading..... Please wait!");

//		try {
//			Thread.sleep(10000);
//		}
//		catch(Exception ex) {
//
//		}
//		dialog.dismiss();

		 (new Thread() {
		     @Override
		     public void run() {
		    	  try {
		    		  // downloading ...
		    		  Thread.sleep(10000);
		    	  }
		    	  catch(Exception ex) {

		    	  }
		    	  dialog.dismiss();
		     }
		 }).start();

	}
	
	public void showProgress(View v){
		 dialog = new ProgressDialog(this);
		 dialog.setMax(10);
		 dialog.setTitle("Processing");
		 dialog.setProgressStyle( ProgressDialog.STYLE_HORIZONTAL);
		 dialog.show();
		 
		 
		 (new Thread() {
		     @Override 
		     public void run() {
		    	  try {
		    		  for ( int i = 1;  i<=10;i++) {
		    		     Thread.sleep(1000);
		    		     dialog.setProgress(i);
		    		  }
		    	  }
		    	  catch(Exception ex) {
		    	  }
		    	  dialog.dismiss();
		     }
		 }).start(); 
		 
		 
	}

}
