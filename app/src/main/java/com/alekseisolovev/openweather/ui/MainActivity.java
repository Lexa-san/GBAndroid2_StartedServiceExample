package com.alekseisolovev.openweather.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alekseisolovev.openweather.model.TestModel;
import com.alekseisolovev.openweather.service.AppService;
import com.alekseisolovev.openweather.R;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "MainActivity";

    private Button btnRunInThread;
    private Button btnStart;
    private Button btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        addListeners();
    }

    private void initUI() {
        btnRunInThread = findViewById(R.id.button_run_thread);
        btnStart = findViewById(R.id.button_start);
        btnStop = findViewById(R.id.button_stop);
    }

    private void addListeners() {
        /**
         * Example: work in Thread.
         */
        btnRunInThread.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "btnRunInThread START");

                final Handler handler = new Handler();
                final Context context = v.getContext();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        TestModel model = new TestModel();
                        model.work();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "btnRunInThread DONE", Toast.LENGTH_SHORT).show();
                                Log.d(LOG_TAG, "btnRunInThread DONE");
                            }
                        });
                    }
                }).start();

                Log.d(LOG_TAG, "btnRunInThread STOP");
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartService();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStopService();
            }
        });
    }

    private void onStartService() {
        startService(AppService.newIntent(this));
    }

    private void onStopService() {
        stopService(AppService.newIntent(this));
    }
}
