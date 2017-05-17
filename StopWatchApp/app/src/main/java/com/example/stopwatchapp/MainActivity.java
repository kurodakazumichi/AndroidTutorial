package com.example.stopwatchapp;


import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {


	private long startTime;
	private long elapsedTime = 0l;

	private Button startButton;
	private Button stopButton;
	private Button resetButton;
	private TextView timerLabel;

	private Handler handler = new Handler();
	private Runnable updateTimer;


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
		// システムが軌道してからの経過時間(ミリ秒)
		startTime = SystemClock.elapsedRealtime();

		// 一定時間ごとに現在の経過時間を表示する
		updateTimer = new Runnable() {
			@Override
			public void run() {
				long t = SystemClock.elapsedRealtime() - startTime + elapsedTime;
				SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS", Locale.US);
				timerLabel.setText(sdf.format(t));

				// 念のためremoveしてからpostDelayedする。
				handler.removeCallbacks(updateTimer);
				handler.postDelayed(updateTimer, 10);
			}
		};

		// 10ミリ秒後にupdateTimerを呼ぶ
		handler.postDelayed(updateTimer, 10);

		// ボタンの状態を設定
		setButtonState(false, true, false);
	}

	public void stopTimer(View view)
	{
		elapsedTime = SystemClock.elapsedRealtime() - startTime;
		handler.removeCallbacks(updateTimer);
		setButtonState(true, false, true);
	}

	public void resetTimer(View view)
	{
		timerLabel.setText("00:00.000");
		elapsedTime = 0l;
		setButtonState(true, false, false);
	}
}
