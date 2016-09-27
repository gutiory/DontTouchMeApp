package com.DontTouchMeApp.receivers

import android.content.{Intent, Context, BroadcastReceiver}
import android.util.Log

/**
  * Created by @gutiory on 20/3/16.
  */
class MyBCReceiver extends BroadcastReceiver{
  override def onReceive(context: Context, intent: Intent): Unit = {
    Log.d("MyBCReceiver", intent.getAction)
    intent.getAction match {
      case Intent.ACTION_SCREEN_ON => Log.d("MyBCReceiver", Intent.ACTION_SCREEN_ON)
      case _ => Log.d("MyBCReceiver", "CHUNGALETI")
    }
  }
}
