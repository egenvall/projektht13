package com.dat255.ht13.grupp23.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MessagesDatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "messagestable.db";
	private static final int DATABASE_VERSION = 1;

	public MessagesDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//MessagesTable.onCreate(db);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	//	MessagesTable.onUpgrade(db, oldVersion, newVersion);

	}

}
