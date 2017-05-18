package com.example.mymemoapp;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FromActivity extends AppCompatActivity {

	private long memoId;
	private EditText titleText;
	private EditText bodyText;
	private TextView updatedText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_from);

		titleText = (EditText) findViewById(R.id.titleText);
		bodyText = (EditText) findViewById(R.id.bodyText);
		updatedText = (TextView) findViewById(R.id.updatedText);

		Intent intent = getIntent();
		memoId = intent.getLongExtra(MainActivity.EXTRA_MYID, 0L);

		if (memoId == 0) {
			// new memo
		} else {
			// show memo
			Uri uri = ContentUris.withAppendedId(
					MemoContentProvider.CONTENT_URI,
					memoId
			);

			String[] projection = {
					MemoContract.Memos.COL_TITLE,
					MemoContract.Memos.COL_BODY,
					MemoContract.Memos.COL_UPDATED
			};

			Cursor c = getContentResolver().query(
					uri,
					projection,
					MemoContract.Memos._ID + " = ?",
					new String[] { Long.toString(memoId) },
					null
			);

			c.moveToFirst();

			titleText.setText(c.getString(c.getColumnIndex(MemoContract.Memos.COL_TITLE)));
			bodyText.setText(c.getString(c.getColumnIndex(MemoContract.Memos.COL_BODY)));
			updatedText.setText(c.getString(c.getColumnIndex(MemoContract.Memos.COL_UPDATED)));

			c.close();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_form, menu);
		return true;
	}

	private void deleteMemo(){

	}

	private void saveMemo() {

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
			case R.id.action_delete:
				deleteMemo();
				break;
			case R.id.action_save:
				saveMemo();
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
