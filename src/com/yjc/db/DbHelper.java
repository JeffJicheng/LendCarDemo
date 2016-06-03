package com.yjc.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper{

	public DbHelper(Context context,
			int version) {
		super(context, "user.db", null, version);
		// TODO Auto-generated constructor stub
	}
	//´´½¨±í
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "create table T_user( _id integer primary key autoincrement , user_name varchar(20) ,user_pwd varchar(20) ,head varchar(50),isLogin integer)";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
