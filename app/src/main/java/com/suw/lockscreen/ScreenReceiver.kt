package com.suw.lockscreen

import android.app.KeyguardManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

class ScreenReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_SCREEN_OFF) {
            val i = Intent(context, LockScreenActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val intent:Intent = Intent(context,ScreenService::class.java)
            context.startForegroundService(intent)
        } else{
            val intent:Intent = Intent(context,ScreenService::class.java)
            context.startService(intent)
        }
    }
}