package com.st.first.threads;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class SumBoundService extends Service {

	    private final IBinder binder = new ResultBinder();
	    
	    public class ResultBinder extends Binder {
	        SumBoundService getService() {
	            return SumBoundService.this;
	        }
	    }

	    @Override
	    public void onCreate() {
		  Log.i(this.getClass().getName(),"onCreate()");
     	}

	    @Override
	    public IBinder onBind(Intent intent) {
	    	Log.i(this.getClass().getName(),"onBind()");
	        return binder;
	    }

	    @Override
		public boolean onUnbind(Intent intent) {
	    	Log.i(this.getClass().getName(),"onUnBind()");
			return super.onUnbind(intent);
		}

		@Override
		public void onDestroy() {
			super.onDestroy();
			Log.i(this.getClass().getName(),"onDestory()");
		}

		public int getSum(int num) {
		  Log.d( getClass().getName(), "Thread : " + Thread.currentThread().getName());
	      int sum = 0;

			for(int i = 1; i <= num; i ++)
			{
				sum += i;
			}
			return sum;
	  }
}
