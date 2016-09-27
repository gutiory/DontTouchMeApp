package com.DontTouchMeApp.services

import android.content.Intent
import android.service.notification.{StatusBarNotification, NotificationListenerService}
import android.util.Log
import android.content.Context._
/**
  * Created by @gutiory on 21/3/16.
  */
class NotificationReceiverService extends NotificationListenerService{

  override def onNotificationPosted(sbn: StatusBarNotification) {
    Log.d("NotificationReceiverService", sbn.toString)
    val intent = new Intent()
    intent.setAction("DontTouchMeApp.sendPackage")
    intent.putExtra("package", sbn.getPackageName)
    sendBroadcast(intent)
  }

}
