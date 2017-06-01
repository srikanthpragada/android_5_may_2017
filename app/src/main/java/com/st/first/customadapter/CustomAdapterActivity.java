package com.st.first.customadapter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.ArrayList;

public class CustomAdapterActivity extends ListActivity {

	private  ArrayList<Company>  companies;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		companies = new ArrayList<Company>();
		companies.add( new Company("Srikanth Technologies","9059057000","http://www.srikanthtechnologies.com","st"));
		companies.add( new Company("Spring People","08065679700","http://www.springpeople.com","sp"));
		companies.add( new Company("Microsoft","9059057000","http://www.microsoft.com","mail"));
		companies.add( new Company("Oracle","08065679700","http://www.oracle.com","datetime"));
		
		CompanyAdapter adapter = new CompanyAdapter(this,companies);
		getListView().setAdapter(adapter);

		requestPermissions( new String[] {Intent.ACTION_CALL},1);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode,
										   @NonNull String[] permissions,
										   @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);

	}
}


