package com.alekseisolovev.openweather.service.browser;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class RequesterAsyncTask extends AsyncTask<String, String, String> {

    public static final String TAG = "BROWSER_REQUESTER";

    private OnRequestListener listener;

    public RequesterAsyncTask(OnRequestListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onProgressUpdate(String... strings) {
        Log.d(TAG, "onProgressUpdate");
        listener.onStatusProgress((String) strings[0]);
    }

    @Override
    protected void onPostExecute(String str) {
        Log.d(TAG, "onPostExecute: " + str);
        listener.onComplete((String) str);
    }

    @Override
    protected String doInBackground(String... strings) {
        Log.d(TAG, "doInBackground");
        return executeQuery(strings[0]);
    }

    private String executeQuery(String uri) {
        publishProgress("Loading...");
        final StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(uri);
            final OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            publishProgress("Status: " + response);
            result.append(response.body().string());
            Log.d(TAG, "Response body:" + result.substring(0, 255) + "...");

        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            publishProgress("IO Error");
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            publishProgress("Error");
        }

        return result.toString();

    }

}
