package com.alekseisolovev.openweather.service.browser;

public class Browser {
    public static final String TAG = "BROWSER";
    public static final String MIMETYPE_DEFAULT = "text/html; charset=utf-8";
    public static final String ENCODING_DEFAULT = "utf-8";

    private OnRequestListener listener;

    public Browser(OnRequestListener listener) {
        this.listener = listener;
    }

    public void request(String uri) {
        new RequesterAsyncTask(listener)
                .execute(uri);
    }

}
