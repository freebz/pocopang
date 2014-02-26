package com.freebz.pocopang.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AnimalListDatabaseHelper {

	private static final int DATABASE_VERSION = 15;
	private static final String DATABASE_NAME = "pocopang.db";
	private static final String TABLE_NAME = "animals";
	
	public static final String ANIMAL_COLUMN_ID = "_id";
//	public static final String ANIMAL_COLUMN_NO = "no";
	public static final String ANIMAL_COLUMN_NAME = "name";
	public static final String ANIMAL_COLUMN_COUNT = "count";
	
	private AnimalOpenHelper openHelper;
	private SQLiteDatabase database;
	
	public AnimalListDatabaseHelper(Context context) {
		openHelper = new AnimalOpenHelper(context);
		database = openHelper.getWritableDatabase();
	}
	
	public void saveAnimal(Animal animal) {
		ContentValues values = new ContentValues();
		values.put(ANIMAL_COLUMN_ID, animal.getId());
//		values.put(ANIMAL_COLUMN_ID, 10);
		values.put(ANIMAL_COLUMN_NAME, animal.getName());
		values.put(ANIMAL_COLUMN_COUNT, 1);
//		long result = database.insert(TABLE_NAME, null, values);
//		long result = database.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		
		try {
			database.insertOrThrow(TABLE_NAME, null, values);
		} catch (SQLiteConstraintException e) {
			database.execSQL("update " + TABLE_NAME + " set "
					+ ANIMAL_COLUMN_COUNT + " = " + ANIMAL_COLUMN_COUNT + " + 1 where "
					+ ANIMAL_COLUMN_ID + " = " +  animal.getId());
		}
		
	}
	
	public Cursor getAnimalList() {
		return database.rawQuery("select * from " + TABLE_NAME + " order by " + ANIMAL_COLUMN_ID, null);
	}
	
	private class AnimalOpenHelper extends SQLiteOpenHelper {

		public AnimalOpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase database) {
			database.execSQL(
					"create table " + TABLE_NAME + " ( "
					+ ANIMAL_COLUMN_ID + " INTEGER PRIMARY KEY, "
//					+ ANIMAL_COLUMN_NO + " INTEGER, "
					+ ANIMAL_COLUMN_NAME + " TEXT, "
					+ ANIMAL_COLUMN_COUNT + " INTEGER )"
			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
			database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(database);
		}

	}
	
}
