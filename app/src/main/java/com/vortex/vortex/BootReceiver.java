package com.vortex.vortex;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    Context context;
    private final String BOOT_ACTION = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        String action = intent.getAction();
        if(action.equalsIgnoreCase(BOOT_ACTION)){
            Intent serviceIntent = new Intent(context, GetNewNewsService.class);
            context.startService(serviceIntent);
        }

    }
}
