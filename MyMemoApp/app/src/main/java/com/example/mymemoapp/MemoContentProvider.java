package com.example.mymemoapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.IntentFilter;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.widget.AutoScrollHelper;

public class MemoContentProvider extends ContentProvider {

	public static final String AUTHORITY = "com.example.mymemoapp.MemoContentProvider";

	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + MemoContract.Memos.TABLE_NAME);

	private static final int MEMOS = 1;
	private static final int MEMO_ITEM = 2;
	private static final UriMatcher uriMatcher;
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI(AUTHORITY, MemoContract.Memos.TABLE_NAME, MEMOS);
		uriMatcher.addURI(AUTHORITY, MemoContract.Memos.TABLE_NAME+"/#", MEMO_ITEM);
	}

	private MemoOpenHelper memoOpenHelper;

	public MemoContentProvider() {
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// Implement this to handle requests to delete one or more rows.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public String getType(Uri uri) {
		// TODO: Implement this to handle requests for the MIME type of the data
		// at the given URI.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO: Implement this to handle requests to insert a new row.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public boolean onCreate() {
		// TODO: Implement this to initialize your content provider on startup.
		memoOpenHelper = new MemoOpenHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
						String[] selectionArgs, String sortOrder) {

		switch(uriMatcher.match(uri)){
			case MEMOS:
			case MEMO_ITEM:
				break;
			default:
				throw new IllegalArgumentException("Invalid URI: " + uri);
		}

		SQLiteDatabase db = memoOpenHelper.getReadableDatabase();
		Cursor c = db.query(
				MemoContract.Memos.TABLE_NAME,
				projection,
				selection,
				selectionArgs,
				null,
				null,
				sortOrder
		);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
					  String[] selectionArgs) {
		// TODO: Implement this to handle requests to update one or more rows.
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
