package com.st.first.storage;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class ProductsProvider extends  ContentProvider {
	
	
	public static final Uri CONTENT_URI = 
            Uri.parse("content://com.st.products");
	
	public static final String  ID ="_id";
	public static final String  NAME ="name";
	public static final String  PRICE  ="price";
	
	
	@Override
	public int delete(Uri arg0, String selection, String[] selectionArgs) {
		ProductsDatabase dbhelper = new  ProductsDatabase(this.getContext());
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		try {
			int count = db.delete(ProductsDatabase.PRODUCTS_TABLE_NAME, selection, selectionArgs);
			return count;
		}
		catch(Exception ex) {
			Log.e("Storage", ex.getMessage());
			return 0;
		}
		finally {
			db.close();
		}
	}

	@Override
	public String getType(Uri uri) {
		return "vnd.android.cursor.item/product";
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
        // insert row into Products
		ProductsDatabase dbhelper = new  ProductsDatabase(this.getContext());
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		

		db.insert(ProductsDatabase.PRODUCTS_TABLE_NAME, null, values);
		db.close();
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		ProductsDatabase dbhelper = new  ProductsDatabase(this.getContext());
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		try {
			Cursor c = db.query(ProductsDatabase.PRODUCTS_TABLE_NAME, projection, selection,
					selectionArgs, null, null, sortOrder);
			return c;
		}
		catch(Exception ex)
		{
			return null;
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		ProductsDatabase dbhelper = new  ProductsDatabase(this.getContext());
		SQLiteDatabase db = dbhelper.getWritableDatabase();
		
		db.update( ProductsDatabase.PRODUCTS_TABLE_NAME, values, selection, selectionArgs);
		
		return 0;
	}

}
