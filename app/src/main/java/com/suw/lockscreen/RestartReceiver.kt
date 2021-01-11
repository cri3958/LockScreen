package com.suw.lockscreen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class RestartReceiver : BroadcastReceiver() {
    val ACTION_RESTART_SERVICE:String = "RestartReceiver.restart"
    override fun onReceive(context: Context, intent: Intent) {
        if(intent.getAction().equals(ACTION_RESTART_SERVICE)){
            val intent:Intent = Intent(context,ScreenService::class.java)
            context.startService(intent)
        }
    }
}
