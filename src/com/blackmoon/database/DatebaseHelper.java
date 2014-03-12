package com.blackmoon.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatebaseHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "DanhNgonAV";
	private static final String TABLE_NAME = "danhngon";
	private static final String CATEGORY = "category";
	private static final String ENGLIGH = "english";
	private static final String VIETNAMESE = "vietnamese";
	private static final String AUTHOR = "author";
	private static final String FAVORITE = "favorite";
	private static final String AWARD = "award";

	

	public DatebaseHelper(Context context, String db_name,
			CursorFactory factory, int version) {
		super(context, DB_NAME, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String create_table = "create table " + TABLE_NAME
				+ "(id integer primary key autoincrement," + CATEGORY + " text,"
				+ ENGLIGH +" text," + VIETNAMESE +" text," + AUTHOR +" text," + FAVORITE +" integer," + AWARD +" integer );";
		db.execSQL(create_table);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
		onCreate(db);
	}

}
