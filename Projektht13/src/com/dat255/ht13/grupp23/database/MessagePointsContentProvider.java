package com.dat255.ht13.grupp23.database;

import java.util.Arrays;
import java.util.HashSet;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class MessagePointsContentProvider extends ContentProvider {

	// Database
	private MessagePointsDatabaseHelper database;

	// For urimatcher
	private static final int MESSAGEPOINTS = 10;
	private static final int MESSAGEPOINTS_ID = 20;

	private static final String AUTHORITY = "com.dat255.ht13.grupp23.database";
	private static final String BASE_PATH = "messagepoints";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/" + BASE_PATH);
	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
			+ "/messagepoints";
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
			+ "/messagepoint";

	private static final UriMatcher tableURIMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);
	static {
		tableURIMatcher.addURI(AUTHORITY, BASE_PATH, MESSAGEPOINTS);
		tableURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", MESSAGEPOINTS_ID);
	}

	@Override
	public boolean onCreate() {
		database = new MessagePointsDatabaseHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

		// Check if the caller has requested a column which does not exist
		checkColumns(projection);

		// SELECT FROM MessagePointsTable
		queryBuilder.setTables(MessagePointsTable.TABLE_MESSAGEPOINTS);

		int uriType = tableURIMatcher.match(uri);
		switch (uriType) {
		case MESSAGEPOINTS:
			break;
		case MESSAGEPOINTS_ID:
			// ...WHERE COL_ID =
			queryBuilder.appendWhere(MessagePointsTable.COLUMN_ID + "="
					+ uri.getLastPathSegment());
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);

		}
		SQLiteDatabase db = database.getWritableDatabase();
		Cursor cursor = queryBuilder.query(db, projection, selection,
				selectionArgs, null, null, sortOrder);
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		return cursor;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = tableURIMatcher.match(uri);
		SQLiteDatabase sqlDB = database.getWritableDatabase();
		long id = 0;
		switch (uriType) {
		case MESSAGEPOINTS:
			id = sqlDB.insert(MessagePointsTable.TABLE_MESSAGEPOINTS, null,
					values);
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);

		}
		getContext().getContentResolver().notifyChange(uri, null);
		return Uri.parse(BASE_PATH + "/" + id);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int uriType = tableURIMatcher.match(uri);
		SQLiteDatabase sqlDB = database.getWritableDatabase();
		int rowsDeleted = 0;
		switch (uriType) {
		case MESSAGEPOINTS:
			rowsDeleted = sqlDB.delete(MessagePointsTable.TABLE_MESSAGEPOINTS,
					selection, selectionArgs);
			break;

		case MESSAGEPOINTS_ID:
			String id = uri.getLastPathSegment();
			if (TextUtils.isEmpty(selection)) {
				rowsDeleted = sqlDB.delete(
						MessagePointsTable.TABLE_MESSAGEPOINTS,
						MessagePointsTable.COLUMN_ID + "=" + id, null);
			} else {
				rowsDeleted = sqlDB.delete(
						MessagePointsTable.TABLE_MESSAGEPOINTS,
						MessagePointsTable.COLUMN_ID + "=" + id + "and"
								+ selection, selectionArgs);
			}
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);

		}
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsDeleted;
	}

	@Override
	public String getType(Uri arg0) {
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int uriType = tableURIMatcher.match(uri);
		SQLiteDatabase sqlDB = database.getWritableDatabase();
		int rowsUpdated = 0;

		switch (uriType) {
		case MESSAGEPOINTS:
			rowsUpdated = sqlDB.update(MessagePointsTable.TABLE_MESSAGEPOINTS,
					values, selection, selectionArgs);
			break;

		case MESSAGEPOINTS_ID:
			String id = uri.getLastPathSegment();
			if (TextUtils.isEmpty(selection)) {
				rowsUpdated = sqlDB.update(
						MessagePointsTable.TABLE_MESSAGEPOINTS, values,
						MessagePointsTable.COLUMN_ID + "=" + id, null);
			} else {
				rowsUpdated = sqlDB.update(
						MessagePointsTable.TABLE_MESSAGEPOINTS, values,
						MessagePointsTable.COLUMN_ID + "=" + id, selectionArgs);
			}
			break;
		default:
			throw new IllegalArgumentException("Unknown URI:" + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsUpdated;
	}

	public void checkColumns(String[] projection) {
		String[] available = { MessagePointsTable.COLUMN_ID,
				MessagePointsTable.COLUMN_MESSAGES,
				MessagePointsTable.COLUMN_XPOS, MessagePointsTable.COLUMN_YPOS };
		if (projection != null) {
			HashSet<String> requestedColumns = new HashSet<String>(
					Arrays.asList(projection));
			HashSet<String> availableColumns = new HashSet<String>(
					Arrays.asList(available));
			/* Check if all the columns that were requested are available */
			if (!availableColumns.containsAll(requestedColumns)) {
				throw new IllegalArgumentException(
						"Requested columns are unknown");
			}
		}

	}

}
