package com.alekseisolovev.openweather.service;


import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;


public class AppService extends IntentService {
    
    public static final String LOG_TAG = "AppServiceLog";
    public static final String RESULT = "result";

    public AppService() {
        super("OpenWeatherService");
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, AppService.class);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(LOG_TAG, "onHandleIntent START");

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

        if (intent != null) {
            intent.putExtra(RESULT, "Calculation is done.");
        }

        Log.d(LOG_TAG, "onHandleIntent STOP");
        Toast.makeText(this, "onHandleIntent", Toast.LENGTH_SHORT).show();
    }

    /**
     * Override for logging purpose only.
     */

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
        Toast.makeText(this, "service created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "onStartCommand");
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(LOG_TAG, "onDestroy");
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }
}
