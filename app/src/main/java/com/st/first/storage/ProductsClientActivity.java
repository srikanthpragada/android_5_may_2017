package com.st.first.storage;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.st.first.R;

public class ProductsClientActivity extends Activity {
    EditText editName, editPrice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.activity_products_client);
		editName = (EditText) findViewById(R.id.editName);
		editPrice = (EditText) findViewById(R.id.editPrice);
		getProducts();
	}

	public void addProduct(View v) {
		ContentValues values = new ContentValues();
		values.put( ProductsProvider.NAME, editName.getText().toString());
		values.put( ProductsProvider.PRICE, editPrice.getText().toString());
		getContentResolver().insert( ProductsProvider.CONTENT_URI, values);
		Toast.makeText(this,"Product Added Successfully!", Toast.LENGTH_LONG).show();
		getProducts();
	}

	public void getProducts()
	{
		Cursor c =  getContentResolver().query( ProductsProvider.CONTENT_URI,null,"price > 50000",null,null);

		TextView textProducts = (TextView) this.findViewById( R.id.textProducts);

		textProducts.setText("");
		while (c.moveToNext()) {
			textProducts.append(  String.format("%s - %s \n",
					c.getString( c.getColumnIndex( ProductsProvider.NAME)),
					c.getString( c.getColumnIndex( ProductsProvider.PRICE))));

		}
		c.close();
	}

}
