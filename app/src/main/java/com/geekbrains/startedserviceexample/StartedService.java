package com.geekbrains.startedserviceexample;


import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


public class StartedService extends IntentService {

    public StartedService() {
        super("StartedService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        long endTime = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d("Dto", "done task");

        Toast.makeText(this, "onHandleIntent", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Dto", "onCreate");
        Toast.makeText(this, "service created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Dto", "onStartCommand");
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("Dto", "onDestroy");
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }
}
