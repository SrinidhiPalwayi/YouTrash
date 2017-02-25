package com.example.nidhi.youtrash;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by Randy on 2/25/2017.
 */

public class BackgroundNotifier extends IntentService {
    public BackgroundNotifier(String name) {
        super(name);
    }

    public void onHandleIntent(Intent intent) {
        // do stuff
    }
}
