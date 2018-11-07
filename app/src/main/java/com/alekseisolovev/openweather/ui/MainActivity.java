package com.alekseisolovev.openweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alekseisolovev.openweather.service.AppService;
import com.alekseisolovev.openweather.R;

public class MainActivity extends AppCompatActivity {

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
        btnStart = findViewById(R.id.button_start);
        btnStop = findViewById(R.id.button_stop);
    }

    private void addListeners() {
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
