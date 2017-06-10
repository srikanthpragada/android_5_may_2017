package com.st.first.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ProductsDatabase extends SQLiteOpenHelper {

        public static final int DATABASE_VERSION = 2;
        public static final String DATABASE_NAME = "products.db";
        public static final String PRODUCTS_TABLE_NAME = "PRODUCTS";
	    private static final String TABLE_CREATE_SQL =
	                "CREATE TABLE " + PRODUCTS_TABLE_NAME + "( _id integer primary key autoincrement, name TEXT, price INTERGER)";
	    
	    public ProductsDatabase (Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }

	    @Override
	    public void onCreate(SQLiteDatabase db) {
	        db.execSQL(TABLE_CREATE_SQL);
	        db.execSQL( "insert into  products(name,price) values('iPhone7 plus',65500)");
	        db.execSQL( "insert into  products(name,price) values('iPad Air 2',40000)");
	        db.execSQL( "insert into  products(name,price) values('Bose Wireless HeadPhones',20000)");
	
	    }

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.i("Storage", "Upgrading Database");
            db.execSQL("delete from products");
			db.execSQL( "insert into  products(name,price) values('iPhone7 plus',65500)");
			db.execSQL( "insert into  products(name,price) values('iPad Air 2',40000)");
			db.execSQL( "insert into  products(name,price) values('Bose Wireless HeadPhones',20000)");
		}

}
