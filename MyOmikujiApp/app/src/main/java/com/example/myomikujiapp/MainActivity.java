package com.example.myomikujiapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void getOmikuji(View view) {
		// TextViewの取得
		TextView myTextView = (TextView) findViewById(R.id.myTextView);

		String[] results = {
				getString(R.string.result_daikichi),
				getString(R.string.result_kichi),
				getString(R.string.result_kyou),
		};

		// 乱数の生成
		Random randomGenerator = new Random();
		int num = randomGenerator.nextInt(results.length);

		// 結果の表示
		if (num == 0)
			myTextView.setTextColor(Color.RED);
		else
			myTextView.setTextColor(Color.rgb(0, 0, 0));

		myTextView.setText(results[num]);
	}
}
