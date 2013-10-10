package com.dat255.ht13.grupp23.database;

import android.database.sqlite.SQLiteDatabase;

public class MessagePointsTable {

	/* Database Table */
	public static final String TABLE_MESSAGEPOINTS = "messagepoints";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_XPOS = "xpos";
	public static final String COLUMN_YPOS = "ypos";
	public static final String COLUMN_MESSAGES = "messages";

	/* Database creation SQL */
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_MESSAGEPOINTS + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_XPOS + " real, "
			+ COLUMN_YPOS + " real, " + COLUMN_MESSAGES + " text not null)";

	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);

	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		System.out
				.println("MessagePointsTable, Upgrading database from version "
						+ oldVersion + " to " + newVersion);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_MESSAGEPOINTS);
		onCreate(database);

	}
}
