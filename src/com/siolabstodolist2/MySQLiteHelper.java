package com.siolabstodolist2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper  extends SQLiteOpenHelper{
	
	public MySQLiteHelper(Context context){
	
		super(context,DATABASE_NAME,null,1 );
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(tableCreate);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	private static final String tableCreate = "Create table items( _id integer primary key autoincrement, desc text not null , category text );";
	private static final String DATABASE_NAME = "items.db";
	private static final String COL_ID = "_id";
	private static final String COL_DESC = "desc";
	private static final String COL_CATEGORY = "category";

}
