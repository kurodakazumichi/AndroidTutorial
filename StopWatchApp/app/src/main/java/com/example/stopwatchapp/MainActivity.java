package com.example.stopwatchapp;

import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


	private long startTime;
	private Button startButton;
	private Button stopButton;
	private Button resetButton;
	private TextView timerLabel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		startButton = (Button)findViewById(R.id.startButton);
		stopButton = (Button)findViewById(R.id.stopButton);
		resetButton = (Button)findViewById(R.id.resetButton);
		timerLabel = (TextView)findViewById(R.id.timerLabel);

		setButtonState(true, false, false);

	}

	/**
	 * Start,Stop,Resetの３つのボタンに対して、有効、無効を設定する。
	 * 複数個所で同様の処理を行うため、メソッド化している。
	 *
	 * @param start startボタンの有効状態
	 * @param stop stopボタンの有効状態
	 * @param reset resetボタンの有効状態
	 */
	public void setButtonState(boolean start, boolean stop, boolean reset)
	{
		startButton.setEnabled(start);
		stopButton.setEnabled(stop);
		resetButton.setEnabled(reset);
	}

	/**
	 * ストップウォッチを起動する。
	 */
	public void startTimer(View view)
	{
		// システムが軌道してからの経過時間
		startTime = SystemClock.elapsedRealtime();

		// 一定時間ごとに現在の経過時間を表示する

		// ボタンの状態を設定
		setButtonState(false, true, false);
	}
}
