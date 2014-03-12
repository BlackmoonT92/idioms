package com.blackmoon.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.blackmoon.Utils.ConfigData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseHandler {

	private static final String DB_NAME = "DanhNgonAV";
	private static final String TABLE_NAME = "danhngon";
	private static final String CATEGORY = "category";
	private static final String ENGLIGH = "english";
	private static final String VIETNAMESE = "vietnamese";
	private static final String AUTHOR = "author";
	private static final String FAVORITE = "favorite";
	private static final String AWARD = "award";

	private static final int DATABASE_VERSION = 2;
	private SQLiteDatabase sqliteDB;
	private DatebaseHelper dbHelper;

	public DatabaseHandler(Context context) {

		dbHelper = new DatebaseHelper(context, DB_NAME, null, DATABASE_VERSION);

	}

	// Open Database
	public void open() {
		sqliteDB = dbHelper.getWritableDatabase();

	}

	// Close Database
	public void close() {
		if (sqliteDB != null)
			sqliteDB.close();
	}

	public List<IdiomItem> getAllDanhNgon(int pageIndicator) {
		open();
		List<IdiomItem> listDanhNgon = new ArrayList<IdiomItem>();
		int endLoad = 0;

		Cursor cursor = sqliteDB.query(TABLE_NAME, null, null, null, null,
				null, null);

		// get object at position end
		if (pageIndicator + 20 < cursor.getCount()) {
			endLoad = pageIndicator + 20;
		} else {
			endLoad = cursor.getCount();

		}

		if (pageIndicator == 0) {
			cursor.moveToFirst();
			IdiomItem item = new IdiomItem();
			item.set_id(cursor.getInt(0));
			item.set_category(cursor.getString(1));
			item.set_english(cursor.getString(3));
			item.set_vietnamese(cursor.getString(2));
			item.set_author(cursor.getString(4));
			item.set_favorite(cursor.getInt(5));
			item.set_award(cursor.getInt(6));
			listDanhNgon.add(item);

		} else {
			cursor.move(pageIndicator + 1);
		}

		while (cursor.moveToNext() && (cursor.getPosition() <= endLoad)) {
			Log.d("load more",
					pageIndicator + "," + endLoad + ", " + cursor.getPosition());
			IdiomItem item = new IdiomItem();
			item.set_id(cursor.getInt(0));
			item.set_category(cursor.getString(1));
			item.set_english(cursor.getString(3));
			item.set_vietnamese(cursor.getString(2));
			item.set_author(cursor.getString(4));
			item.set_favorite(cursor.getInt(5));
			item.set_award(cursor.getInt(6));
			listDanhNgon.add(item);

		}
		close();
		return listDanhNgon;
	}

	public List<IdiomItem> getIdiomsByCategory(String category,
			int pageIndicator) {
		open();

		List<IdiomItem> listDanhNgon = new ArrayList<IdiomItem>();

		Cursor cursor = sqliteDB.query(TABLE_NAME, null, CATEGORY + " = "
				+ category, null, null, null, null);

		// get object at position end
		int endLoad = 0;
		if (pageIndicator + 20 < cursor.getCount()) {
			endLoad = pageIndicator + 20;
		} else {
			endLoad = cursor.getCount();

		}

		if (pageIndicator == 0) {
			cursor.moveToFirst();
			IdiomItem item = new IdiomItem();
			item.set_id(cursor.getInt(0));
			item.set_category(cursor.getString(1));
			item.set_english(cursor.getString(3));
			item.set_vietnamese(cursor.getString(2));
			item.set_author(cursor.getString(4));
			item.set_favorite(cursor.getInt(5));
			item.set_award(cursor.getInt(6));
			listDanhNgon.add(item);
		} else {
			cursor.move(pageIndicator + 1);
		}

		while (cursor.moveToNext() && (cursor.getPosition() <= endLoad)) {
			Log.d("load more",
					pageIndicator + "," + endLoad + ", " + cursor.getPosition());
			IdiomItem item = new IdiomItem();
			item.set_id(cursor.getInt(0));
			item.set_category(cursor.getString(1));
			item.set_english(cursor.getString(3));
			item.set_vietnamese(cursor.getString(2));
			item.set_author(cursor.getString(4));
			item.set_favorite(cursor.getInt(5));
			item.set_award(cursor.getInt(6));
			listDanhNgon.add(item);
		}
		close();

		return listDanhNgon;
	}

	public List<IdiomItem> getIdiomsFavorite(int pageIndicator) {
		open();

		List<IdiomItem> listDanhNgon = new ArrayList<IdiomItem>();

		Cursor cursor = sqliteDB.query(TABLE_NAME, null, FAVORITE + "=" + 1,
				null, null, null, null);
		// get object at position end
		int endLoad = 0;
		if (pageIndicator + 20 < cursor.getCount()) {
			endLoad = pageIndicator + 20;
		} else {
			endLoad = cursor.getCount();

		}

		if (pageIndicator == 0) {
			cursor.moveToFirst();

			IdiomItem item = new IdiomItem();
			item.set_id(cursor.getInt(0));
			item.set_category(cursor.getString(1));
			item.set_english(cursor.getString(3));
			item.set_vietnamese(cursor.getString(2));
			item.set_author(cursor.getString(4));
			item.set_favorite(cursor.getInt(5));
			item.set_award(cursor.getInt(6));
			listDanhNgon.add(item);
		} else {
			cursor.move(pageIndicator + 1);
		}

		while (cursor.moveToNext() && (cursor.getPosition() <= endLoad)) {
			Log.d("load more",
					pageIndicator + "," + endLoad + ", " + cursor.getPosition());
			IdiomItem item = new IdiomItem();
			item.set_id(cursor.getInt(0));
			item.set_category(cursor.getString(1));
			item.set_english(cursor.getString(3));
			item.set_vietnamese(cursor.getString(2));
			item.set_author(cursor.getString(4));
			item.set_favorite(cursor.getInt(5));
			item.set_award(cursor.getInt(6));
			listDanhNgon.add(item);
		}
		close();

		return listDanhNgon;
	}

	public IdiomItem getRandomIdiom() {
		try {
			open();
			IdiomItem item = new IdiomItem();

			Cursor cursor = sqliteDB.query(TABLE_NAME, null, null, null, null,
					null, null);
			int number = (new Random().nextInt(cursor.getCount()));

			// get an item
			cursor.move(number);
			while (cursor.getString(3).length() > 150) {
				cursor.moveToNext();
				Log.d("GetRandomItem", "idiom too long");
			}

			item.set_id(cursor.getInt(0));
			item.set_category(cursor.getString(1));
			item.set_english(cursor.getString(3));
			item.set_vietnamese(cursor.getString(2));
			item.set_author(cursor.getString(4));
			item.set_favorite(cursor.getInt(5));
			item.set_award(cursor.getInt(6));

			close();
			return item;
		} catch (Exception e) {
			return null;
		}
	}

	public long insertIdiomItem(IdiomItem item) {
		open();

		ContentValues contentValues = new ContentValues();
		sqliteDB = dbHelper.getWritableDatabase();
		contentValues.put(CATEGORY, item.get_category());
		contentValues.put(ENGLIGH, item.get_english());
		contentValues.put(VIETNAMESE, item.get_vietnamese());
		contentValues.put(AUTHOR, item.get_author());
		contentValues.put(FAVORITE, item.get_favorite());
		contentValues.put(AWARD, item.get_award());

		long key = sqliteDB.insert(TABLE_NAME, null, contentValues);
		close();

		return key;
	}

	public long updateIdomItem(IdiomItem item) {
		open();

		ContentValues contentValues = new ContentValues();
		sqliteDB = dbHelper.getWritableDatabase();
		contentValues.put(FAVORITE, item.get_favorite());
		contentValues.put(AWARD, item.get_award());
		long key = sqliteDB.update(TABLE_NAME, contentValues,
				"id =" + item.get_id(), null);
		close();

		return key;

	}
}
