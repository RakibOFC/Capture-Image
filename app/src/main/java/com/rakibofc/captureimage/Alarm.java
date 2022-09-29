package com.rakibofc.captureimage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Alarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String extra = intent.getStringExtra("extra");
        Toast.makeText(context, extra, Toast.LENGTH_SHORT).show();
    }
}