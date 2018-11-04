package com.alekseisolovev.startedserviceexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartService(View view) {
        Intent intent = new Intent(this, StartedService.class);
        startService(intent);
    }

    public void onStopService(View view) {
        Intent intent = new Intent(this, StartedService.class);
        stopService(intent);
    }
}