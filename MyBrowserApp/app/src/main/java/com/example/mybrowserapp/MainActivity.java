package com.example.mybrowserapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

	private static final String INITIAL_WEBSITE = "http://dotinstall.com";

	WebView myWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myWebView = (WebView)findViewById(R.id.myWebView);

		// Javascriptを有効にする設定
		myWebView.getSettings().setJavaScriptEnabled(true);

		// アプリ内でページ遷移させるための設定
		myWebView.setWebViewClient(new WebViewClient());

		// 指定されたURLのページを表示する
		myWebView.loadUrl(INITIAL_WEBSITE);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}
}
