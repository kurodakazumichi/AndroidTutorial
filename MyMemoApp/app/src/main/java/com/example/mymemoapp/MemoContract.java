package com.example.mymemoapp;

import android.os.StrictMode;
import android.provider.BaseColumns;

/**
 * Created by owner on 2017/05/19.
 */

public class MemoContract {
	public MemoContract() {}

	public static abstract class Memos implements BaseColumns{
		public static final String TABLE_NAME = "memos";
		public static final String COL_TITLE = "title";
		public static final String COL_BODY = "body";
		public static final String COL_CREATED = "created";
		public static final String COL_UPDATED = "updated";
	}
}
