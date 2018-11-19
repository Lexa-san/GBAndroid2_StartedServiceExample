package com.alekseisolovev.openweather.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.alekseisolovev.openweather.R;
import com.alekseisolovev.openweather.service.browser.Browser;
import com.alekseisolovev.openweather.service.browser.OnRequestListener;

public class BrowserActivity extends AppCompatActivity {

    private static final String TAG = "BROWSER_ACTIVITY";

    private TextView tvUrl;
    private TextView tvStatus;
    private Button btnOk;
    private WebView wvBrowser;

    private Browser browser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.browser_title);
        setContentView(R.layout.activity_browser);

        initUI();
        addListeners();
        initBrowser();
    }

    private void initBrowser() {
        browser = new Browser(new OnRequestListener() {
            @Override
            public void onStatusProgress(String updateProgress) {
                tvStatus.setText(updateProgress);
            }

            @Override
            public void onComplete(String result) {
                Log.d(TAG, "listener.onComplete");
                int size = result.length();
                String strToLog;
                strToLog = (size > 100) ? (result.substring(0,100)) : (result);
                Log.d(TAG, "result length: " + size);
                Log.d(TAG, strToLog);

                wvBrowser.loadData(result, Browser.MIMETYPE_DEFAULT, Browser.ENCODING_DEFAULT);
//                wvBrowser.loadUrl("https://yandex.ru");
            }
        });
    }

    private void initUI() {
        tvUrl = findViewById(R.id.url);
        tvStatus = findViewById(R.id.status);
        btnOk = findViewById(R.id.ok);
        wvBrowser = findViewById(R.id.browser);
    }

    private void addListeners() {
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = tvUrl.getText().toString();
                if (uri.length() > 0) {
                    browser.request(uri);
                }
            }
        });
    }
}
