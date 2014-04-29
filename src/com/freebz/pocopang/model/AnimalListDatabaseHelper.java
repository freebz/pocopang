package com.freebz.pocopang.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AnimalListDatabaseHelper {

	private static final int DATABASE_VERSION = 39;
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
		database.execSQL("update cherry set "
				+ "cherry = 0"
				+ " where type in (2, 4)");
	}
	
	public void saveAnimal(Animal animal) {		
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
	}
	
	public Cursor getAnimalList() {
		return database.rawQuery("select A.* from " + TABLE_NAME
				+ " A INNER JOIN ANIMAL_LIST B "
				+ "on A._ID = B._ID "
				+ "where A.COUNT > 0 "
				+ "order by B.SEQ", null);
	}
	
	public int getCount() {
		return (int) getLongValue("select sum(" + ANIMAL_COLUMN_COUNT + ") from " + TABLE_NAME);
	}
	
	public long getCherry() {
		return getLongValue("select cherry from cherry where type = 1");
	}
	
	public long getUsedCherry() {
		return getLongValue("select cherry from cherry where type = 2");
	}
	
	public long getDiamond() {
		return getLongValue("select cherry from cherry where type = 3");
	}
	
	public long getUsedDiamond() {
		return getLongValue("select cherry from cherry where type = 4");
	}
	
	public void addCherry(long cherry) {
		database.execSQL("update cherry set "
				+ "cherry = cherry + " + Long.toString(cherry)
				+ " where type = 1");
	}
	
	public void addDiamond(long diamond) {
		database.execSQL("update cherry set "
				+ "cherry = cherry + " + Long.toString(diamond)
				+ " where type = 3");
	}
	
	public void subCherry(long cherry) {
		if (hasCherry(cherry)) {
			database.execSQL("update cherry set "
					+ "cherry = cherry - " + Long.toString(cherry)
					+ " where type = 1");
			
			database.execSQL("update cherry set "
					+ "cherry = cherry + " + Long.toString(cherry)
					+ " where type = 2");
		}
	}
	
	public void subDiamond(long diamond) {
		if (hasDiamond(diamond)) {
			database.execSQL("update cherry set "
					+ "cherry = cherry - " + Long.toString(diamond)
					+ " where type = 3");
			
			database.execSQL("update cherry set "
					+ "cherry = cherry + " + Long.toString(diamond)
					+ " where type = 4");
		}
	}
	
	public boolean hasCherry(long cherry) {
		return getCherry() >= cherry;
	}
	
	public boolean hasDiamond(long diamond) {
		return getDiamond() >= diamond;
	}
	
	public boolean hasAnimals(int grade, long count) {
		return getAnimalCountByGrade(grade) >= count;
	}
	
	public void subAnimal(int grade, long count) {
		Cursor cursor = database.rawQuery("select A._id, A.count from ANIMALS A INNER JOIN ANIMAL_LIST B "
				+ "ON A._ID = B._ID "
				+ "WHERE B.GRADE = " + grade
				+ " ORDER BY A.COUNT DESC, B.SEQ, A._ID", null);
		
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()) {
			int id = cursor.getInt(0);
			long cnt = cursor.getLong(1);
			
			if (cnt >= count) {
				cnt -= count;
				count = 0;
			}
			else {
				count -= cnt;
				cnt = 0;
			}
			updateAnimalCount(id, cnt);
			
			if (count == 0) {
				break;
			}
			cursor.moveToNext();
		}
		
		cursor.close();
	}
	
	private void updateAnimalCount(int id, long count) {
		database.execSQL("update animals set "
				+ "count = " + count
				+ " where _id = " + id);
	}
	
	private long getAnimalCountByGrade(int grade) {
		return getLongValue("select sum(count) from ANIMALS A inner join ANIMAL_LIST B "
				+ "ON A._ID = B._ID "
				+ "WHERE B.GRADE = " + grade);
	}
	
//	private long getAnimalCountById(int id) {
//		return getLongValue("select count from ANIMALS "
//				+ "WHERE _ID = " + id);
//	}
	
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
			onUpgrade(database, -1, -1);
		}

		@Override
		public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
			if (oldVersion < 0) {
				createTableAnimals(database);
			}
			if (oldVersion < 30) {
				createTableCherry(database);
			}
			if (oldVersion < 37) {
				addDiamondToCherryTable(database);
			}
			createTableAnimalList(database);
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
			values.put("cherry", 0);
			database.insert("cherry", null, values);
		}
		
		private void addDiamondToCherryTable(SQLiteDatabase database) {
			ContentValues values = new ContentValues();
			values.put("type", 3);
			values.put("cherry", 900);
			database.insert("cherry", null, values);
			
			values.put("type",  4);
			values.put("cherry", 0);
			database.insert("cherry", null, values);
		}
		
		private void createTableAnimalList(SQLiteDatabase database) {
			database.execSQL("DROP TABLE IF EXISTS ANIMAL_LIST");
			database.execSQL(
					"create table ANIMAL_LIST ( "
					+ ANIMAL_COLUMN_ID + " INTEGER PRIMARY KEY, "
					+ ANIMAL_COLUMN_NAME + " TEXT, "
					+ "RATIO INTEGER, "
					+ "GRADE INTEGER, "
					+ "SEQ INTEGER)"
			);
			
			ContentValues values = new ContentValues();
			for (Animal animal : new AnimalList().getAnimalList()) {
				values.put(ANIMAL_COLUMN_ID, animal.getId());
				values.put(ANIMAL_COLUMN_NAME, animal.getName());
				values.put("RATIO", animal.getRatio());
				values.put("GRADE", animal.getGrade());
				values.put("SEQ", animal.getSeq());
				database.insert("ANIMAL_LIST", null, values);
			}
		}
	}
	
}
