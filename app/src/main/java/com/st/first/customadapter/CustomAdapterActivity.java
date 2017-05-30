package com.st.first.customadapter;

import android.app.ListActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class CustomAdapterActivity extends ListActivity {

	private  ArrayList<Company>  companies;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		companies = new ArrayList<Company>();
		companies.add( new Company("Srikanth Technologies","9059057000","http://www.srikanthtechnologies.com","st"));
		companies.add( new Company("Spring People","08065679700","http://www.springpeople.com","sp"));
		
		CompanyAdapter adapter = new CompanyAdapter(this,companies);
		getListView().setAdapter(adapter);
	}

	
	
	
}


