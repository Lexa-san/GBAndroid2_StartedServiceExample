package com.alekseisolovev.openweather.service.browser;

public interface OnRequestListener {

    void onStatusProgress(String updateProgress);

    void onComplete(String result);
}
