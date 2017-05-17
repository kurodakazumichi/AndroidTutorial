package com.example.mybrowserapp;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

	private static final String INITIAL_WEBSITE = "http://dotinstall.com";

	WebView myWebView;
	EditText urlText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myWebView = (WebView) findViewById(R.id.myWebView);
		urlText = (EditText) findViewById(R.id.urlText);

		// Javascriptを有効にする設定
		myWebView.getSettings().setJavaScriptEnabled(true);

		// アプリ内でページ遷移させるための設定
		myWebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				getSupportActionBar().setSubtitle(view.getTitle());
				urlText.setText(url);
			}
		});

		// 指定されたURLのページを表示する
		myWebView.loadUrl(INITIAL_WEBSITE);

	}

	public void showWebSite(View view) {
		String url = urlText.getText().toString().trim();

		if (!Patterns.WEB_URL.matcher(url).matches()) {
			urlText.setError("Invalid URL");
		} else {
			if(!url.startsWith("http://") && !url.startsWith("https://"))
			{
				url = "http://" + url;
			}
			myWebView.loadUrl(url);
		}
	}

	public void clearUrl(View view) {
		urlText.setText("");
	}

	@Override
	public void onBackPressed() {
		if (myWebView.canGoBack()) {
			myWebView.goBack();
			return;
		}
		super.onBackPressed();
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (myWebView != null) {
			myWebView.stopLoading();
			myWebView.setWebViewClient(null);
			myWebView.destroy();
		}
		myWebView = null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu)
	{
		MenuItem forward = (MenuItem)menu.findItem(R.id.action_forward);
		MenuItem back = (MenuItem)menu.findItem(R.id.action_back);
		forward.setEnabled(myWebView.canGoForward());
		back.setEnabled(myWebView.canGoBack());
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// 押されたアイテムのIDを取得
		int id = item.getItemId();

		switch (id){
			case R.id.action_back:
				myWebView.goBack();
				return true;
			case R.id.action_forward:
				myWebView.goForward();
				return true;
			case R.id.action_reload:
				myWebView.reload();
				return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
