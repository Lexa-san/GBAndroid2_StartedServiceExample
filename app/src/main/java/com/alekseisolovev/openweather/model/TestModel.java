package com.alekseisolovev.openweather.model;

import android.util.Log;

public class TestModel implements CalculationInterface {
    public static final String LOG_TAG = "TestModel";

    @Override
    public void work() {

        Log.d(LOG_TAG, "START working");

        long endTime = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                }
                catch (Exception e) {
                    Log.d(LOG_TAG, e.getMessage(), e);
                }
            }
        }

        Log.d(LOG_TAG, "STOP working");

    }
}
