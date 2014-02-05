package com.freebz.pocopang.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AnimalOpenHelper extends SQLiteOpenHelper {

	public AnimalOpenHelper(Context context) {
		super(context, "animal.db", null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(
				"create table animals " +
				"(id integer primary key, name text, ratio integer)"
		);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
