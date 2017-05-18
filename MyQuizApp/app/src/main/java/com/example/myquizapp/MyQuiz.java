package com.example.myquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class MyQuiz extends AppCompatActivity {

	public final static String EXTRA_MYSCORE = "example.com.myquizapp.myscore";

	private ArrayList<String[]> quizSet = new ArrayList<String[]>();

	private TextView scoreText;
	private TextView quizText;
	private Button a0Button;
	private Button a1Button;
	private Button a2Button;
	private Button nextButton;

	private int currentQuiz = 0;
	private int score = 0;

	/**
	 * クイズデータのロード、ビューの取得、クイズデータのセットを行います。
	 *
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_quiz);

		loadQuizSet();
		getViews();
		setQuiz();
	}

	/**
	 * クイズアプリを最初の状態にします。
	 */
	@Override
	public void onResume() {
		super.onResume();
		reset();
	}

	/**
	 * スコア、現在のクイズ番号、各種Viewを初期状態にします。
	 */
	private void reset() {
		currentQuiz = 0;
		score = 0;
		setQuiz();
		nextButton.setText("Next");
	}

	/**
	 * 答えが選択された時に呼ばれる。
	 * 成否の判定を行い、次の問題を設定する。
	 * @param view
	 */
	public void checkAnswer(View view) {
		Button clickedButton = (Button) view;

		String clickedAnswer = clickedButton.getText().toString();

		if (clickedAnswer.equals(quizSet.get(currentQuiz)[1])) {
			clickedButton.setText("〇 " + clickedAnswer);
			score++;
		} else {
			clickedButton.setText("× " + clickedAnswer);
		}

		setEnableOfAnsers(false);
		nextButton.setEnabled(true);
		showScore();

		// クイズを進める
		currentQuiz++;

		if (currentQuiz == quizSet.size()) {
			nextButton.setText("Check result");
		}

	}

	/**
	 * NEXTボタンが押されたときに呼ばれる。
	 * 次の問題を表示する、最後の問題の場合は結果ページへ遷移する。
	 * @param view
	 */
	public void goNext(View view) {
		if (currentQuiz == quizSet.size()) {
			Intent intent = new Intent(this, MyResult.class);
			intent.putExtra(EXTRA_MYSCORE, score + "/" + quizSet.size());
			startActivity(intent);
		} else {
			setQuiz();
		}
	}

	/**
	 * 現在のスコアを表示する。
	 */
	private void showScore() {
		scoreText.setText("Score: " + score + "/" + quizSet.size());
	}

	/**
	 * 答えボタンの状態を一括で設定する。
	 * @param enable
	 */
	private void setEnableOfAnsers(boolean enable) {
		a0Button.setEnabled(enable);
		a1Button.setEnabled(enable);
		a2Button.setEnabled(enable);
	}

	/**
	 * 各種Viewを取得し、メンバー変数に保存する。
	 * 一度だけ呼び出す。
	 */
	private void getViews() {
		scoreText = (TextView) findViewById(R.id.scoreText);
		quizText = (TextView) findViewById(R.id.quizText);
		a0Button = (Button) findViewById(R.id.a0Button);
		a1Button = (Button) findViewById(R.id.a1Button);
		a2Button = (Button) findViewById(R.id.a2Button);
		nextButton = (Button) findViewById(R.id.nextButton);
	}

	/**
	 * 現在のクイズの質問と答えを表示する。
	 */
	private void setQuiz() {
		quizText.setText(quizSet.get(currentQuiz)[0]);

		ArrayList<String> answers = new ArrayList<String>();

		for (int i = 1; i <= 3; ++i) {
			answers.add(quizSet.get(currentQuiz)[i]);
		}
		Collections.shuffle(answers);
		a0Button.setText(answers.get(0));
		a1Button.setText(answers.get(1));
		a2Button.setText(answers.get(2));

		setEnableOfAnsers(true);
		nextButton.setEnabled(false);
		showScore();
	}

	/**
	 * クイズデータをロードする。
	 */
	private void loadQuizSet() {
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;

		try {
			inputStream = getAssets().open("quiz.txt");
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String s;
			while ((s = bufferedReader.readLine()) != null) {
				quizSet.add(s.split("\t"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) inputStream.close();
				if (bufferedReader != null) bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
