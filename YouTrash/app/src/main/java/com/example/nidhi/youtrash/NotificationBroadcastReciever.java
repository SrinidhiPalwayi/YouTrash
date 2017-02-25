package com.example.nidhi.youtrash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Randy on 2/25/2017.
 */

public class NotificationBroadcastReciever extends BroadcastReceiver {
    public NotificationBroadcastReciever() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {


        Intent intent1 = new Intent(context, BackgroundNotifier.class);
        context.startService(intent1);
    }
}