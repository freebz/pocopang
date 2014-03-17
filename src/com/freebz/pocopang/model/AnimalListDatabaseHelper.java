package com.freebz.pocopang.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AnimalListDatabaseHelper {

	private static final int DATABASE_VERSION = 30;
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
	
	public void clearAnimal() {
		database.execSQL("delete from " + TABLE_NAME);
	}
	
	public void saveAnimal(Animal animal) {
		if (!hasCherry(6000)) {
    		return;
    	}
		
		ContentValues values = new ContentValues();
		values.put(ANIMAL_COLUMN_ID, animal.getId());
		values.put(ANIMAL_COLUMN_NAME, animal.getName());
		values.put(ANIMAL_COLUMN_COUNT, 1);
		
		try {
			database.insertOrThrow(TABLE_NAME, null, values);
		} catch (SQLiteConstraintException e) {
			database.execSQL("update " + TABLE_NAME + " set "
					+ ANIMAL_COLUMN_COUNT + " = " + ANIMAL_COLUMN_COUNT + " + 1 where "
					+ ANIMAL_COLUMN_ID + " = " +  animal.getId());
		}
		
		subCherry(6000);
	}
	
	public Cursor getAnimalList() {
		return database.rawQuery("select * from " + TABLE_NAME + " order by " + ANIMAL_COLUMN_ID, null);
	}
	
	public int getCount() {
		return (int) getLongValue("select sum(" + ANIMAL_COLUMN_COUNT + ") from " + TABLE_NAME);
	}
	
	public long getCherry() {
		return getLongValue("select cherry from cherry where type = 1");
	}
	
	public void addCherry(long cherry) {
		database.execSQL("update cherry set "
				+ "cherry = cherry + " + Long.toString(cherry));
	}
	
	public void subCherry(long cherry) {
		if (hasCherry(cherry)) {
			database.execSQL("update cherry set "
					+ "cherry = cherry - " + Long.toString(cherry)
					+ " where type = 1");
		}
	}
	
	public boolean hasCherry(long cherry) {
		return getCherry() >= cherry;
	}
	
	private long getLongValue(String sql) {
		Cursor cursor = database.rawQuery(sql, null);
		cursor.moveToFirst();
		long result = cursor.getLong(0);
		cursor.close();
		return result;
	}
	
	private class AnimalOpenHelper extends SQLiteOpenHelper {

		public AnimalOpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase database) {
			createTableAnimals(database);
			createTableCherry(database);
		}

		@Override
		public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
//			database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//			onCreate(database);
			
			if (oldVersion < 30) {
				createTableCherry(database);
			}
		}

		private void createTableAnimals(SQLiteDatabase database) {
			database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			database.execSQL(
					"create table " + TABLE_NAME + " ( "
					+ ANIMAL_COLUMN_ID + " INTEGER PRIMARY KEY, "
					+ ANIMAL_COLUMN_NAME + " TEXT, "
					+ ANIMAL_COLUMN_COUNT + " INTEGER )"
			);
		}
		
		private void createTableCherry(SQLiteDatabase database) {
			database.execSQL("DROP TABLE IF EXISTS cherry");
			database.execSQL(
					"create table cherry ( "
					+ "type INTEGER PRIMARY KEY, "
					+ "cherry INTEGER ) "
			);
			
			ContentValues values = new ContentValues();
			values.put("type", 1);
			values.put("cherry", 60000);
			database.insert("cherry", null, values);
			
			values.put("type",  2);
			database.insert("cherry", null, values);
		}
	}
	
}
