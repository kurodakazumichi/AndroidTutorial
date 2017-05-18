package com.example.mydbapp;

import android.provider.BaseColumns;
import android.util.StringBuilderPrinter;

/**
 * Created by owner on 2017/05/19.
 */

public final class UserContract {
	public UserContract() {}

	public static abstract class Users implements BaseColumns {
		public static final String TABLE_NAME = "users";
		public static final String COL_NAME = "name";
		public static final String COL_SCORE = "score";
	}
}
