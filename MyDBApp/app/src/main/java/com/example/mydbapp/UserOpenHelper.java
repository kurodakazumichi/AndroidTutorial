package com.example.mydbapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by owner on 2017/05/19.
 */

public class UserOpenHelper extends SQLiteOpenHelper {
	public static final String DB_NAME = "myapp.db";
	public static final int DB_VERSION = 2;

	public static final String CREATE_TABLE =
			"create table " + UserContract.Users.TABLE_NAME + " (" +
					UserContract.Users._ID + " integer primary key autoincrement, " +
					UserContract.Users.COL_NAME + " text," +
					UserContract.Users.COL_SCORE + " integer)";

	public static final String INIT_TABLE =
			"insert into users (name, score) values " +
					"('Kuroda', 42), " +
					"('Hirata', 39)";

	public static final String DROP_TABLE =
			"drop table if exists users";

	public UserOpenHelper(Context c) {
		super(c, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		sqLiteDatabase.execSQL(CREATE_TABLE);
		sqLiteDatabase.execSQL(INIT_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
		sqLiteDatabase.execSQL(DROP_TABLE);
		onCreate(sqLiteDatabase);
	}
}
