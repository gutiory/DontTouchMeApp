package com.DontTouchMeApp.services

import android.content.{ContextWrapper, Intent}
import android.service.notification.{NotificationListenerService, StatusBarNotification}
import android.util.Log
import android.content.Context._
import com.DontTouchMeApp.database.Event
/**
  * Created by @gutiory on 21/3/16.
  *
  * This service receives all notifications posted on device
  */
class NotificationReceiverService extends NotificationListenerService with ServerCommunication{

  override implicit val contextProvider: ContextWrapper = this

  override def onNotificationPosted(sbn: StatusBarNotification) {
    Log.d("NotificationReceiverService", sbn.toString)
    /*
    val intent = new Intent()
    intent.setAction("DontTouchMeApp.sendPackage")
    intent.putExtra("package", sbn.getPackageName)
    sendBroadcast(intent)
    */
    postNewEvent(new Event(Some(22),"pkg", 22,22222))
  }

}
