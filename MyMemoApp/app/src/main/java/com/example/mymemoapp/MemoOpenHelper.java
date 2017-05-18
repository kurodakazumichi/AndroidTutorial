package com.example.mymemoapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;

/**
 * Created by owner on 2017/05/19.
 */

public class MemoOpenHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "myapp.db";
	public static final int DB_VERSION = 1;

	public static final String CREATE_TABLE = String.format(
			"create table %s (" +
					"%s integer primary key autoincrement, " +
					"%s text," +
					"%s text, " +
					"%s datetime default current_timestamp," +
					"%s datetime default current_timestamp)",
			MemoContract.Memos.TABLE_NAME,
			MemoContract.Memos._ID,
			MemoContract.Memos.COL_TITLE,
			MemoContract.Memos.COL_BODY,
			MemoContract.Memos.COL_CREATED,
			MemoContract.Memos.COL_UPDATED
	);

	public static final String INIT_TABLE = String.format(
			"insert into %s (%s, %s) values " +
					"('t1', 'b1'), " +
					"('t2', 'b2'), " +
					"('t3', 'b3') ",
			MemoContract.Memos.TABLE_NAME,
			MemoContract.Memos.COL_TITLE,
			MemoContract.Memos.COL_BODY
	);

	public static final String DROP_TABLE = String.format(
			"drop table if exists %s", MemoContract.Memos.TABLE_NAME
	);

	public MemoOpenHelper(Context c) {
		super(c, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
		db.execSQL(INIT_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DROP_TABLE);
		onCreate(db);
	}

}
