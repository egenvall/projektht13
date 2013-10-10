package com.dat255.ht13.grupp23.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MessagePointsDatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "messagepointstable.db";
	private static final int DATABASE_VERSION = 1;

	public MessagePointsDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		MessagePointsTable.onCreate(db);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		MessagePointsTable.onUpgrade(db, oldVersion, newVersion);

	}

}
