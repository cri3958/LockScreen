package com.suw.lockscreen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AutoStartReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val ACTION_AUTOSTART_SERVICE:String = "android.intent.action.BOOT_COMPLETED"
        Toast.makeText(context,"working AUTOSTART SERVICE : "+intent.getAction().toString(), Toast.LENGTH_LONG).show()
        if(intent.getAction().equals(ACTION_AUTOSTART_SERVICE)){
            Toast.makeText(context,"enter??", Toast.LENGTH_LONG).show()
            val intent:Intent = Intent(context.applicationContext,ScreenService::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startService(intent)

            val intent1:Intent = Intent(context.applicationContext,MainActivity::class.java)
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startService(intent1)
        }else{
            Toast.makeText(context,"ENTER AUTOSTART SERIVCE BUT DIFFERENT ACTION", Toast.LENGTH_LONG).show()
        }
    }
}
