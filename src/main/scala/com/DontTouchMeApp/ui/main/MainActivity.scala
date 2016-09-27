package com.DontTouchMeApp.ui.main

import java.util.concurrent.Executor

import android.app.Activity
import android.content.{BroadcastReceiver, Context, Intent, IntentFilter}
import android.os.{Bundle, Handler, Looper}
import android.util.Log
import com.DontTouchMeApp.database.SlickDatabaseHelper
import com.DontTouchMeApp.receivers.MyBCReceiver
import com.DontTouchMeApp.services._
import com.DontTouchMeApp.ui.main.MyTweaks._

import scala.concurrent.ExecutionContext
import scala.language.postfixOps

// import macroid stuff
import macroid.FullDsl._
import macroid._

/**
 * Created by @gutiory on 17/1/16.
 */
// mix in Contexts for Activity
class MainActivity extends Activity with Contexts[Activity] with Layouts{

  private val iFilter: IntentFilter = new IntentFilter(Intent.ACTION_SCREEN_OFF)

  private val bcReceiver : MyBCReceiver = new MyBCReceiver

  private val slickDBHelper = new SlickDatabaseHelper(this)

  lazy val serviceIntent : Intent = new Intent(this, classOf[NotificationReceiverService])

  private val receiver : BroadcastReceiver = new BroadcastReceiver {
    override def onReceive(context: Context, intent: Intent): Unit = {
      Log.d("NotificationsBCReceiver", "onReceive")
      runUi( imgApp <~ setImageDrawable(
        getPackageManager.getApplicationIcon(intent.getStringExtra("package"))))
    }
  }

  // UI thread executor
  lazy val Ui = ExecutionContext.fromExecutor(new Executor {
    private val handler = new Handler(Looper.getMainLooper)

    override def execute(command: Runnable) = handler.post(command)
  })

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)
    Log.d("MyBCReceiver", "onCreate")

    setContentView {
        mainLayout
    }

    // TODO: Lo dejo en TBD.
    startActivity(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"))

    //serviceIntent.setAction()
    startService(serviceIntent)

    // When the service is created, the BCReceiver must be registered
    // All desired actions
    iFilter.addAction(Intent.ACTION_SCREEN_ON)
    iFilter.addAction(Intent.ACTION_CONFIGURATION_CHANGED)
    iFilter.addAction(android.provider.Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
    registerReceiver(bcReceiver, iFilter) 



    //slickDBHelper.insertEvent(new Event(Some(1),1,1200.23))(Ui)

  }

  override def onDestroy(): Unit = {
    super.onDestroy()
    unregisterReceiver(bcReceiver)
  }

  override def onResume(): Unit = {
    val filter = new IntentFilter()
    filter.addAction("DontTouchMeApp.sendPackage")
    registerReceiver(receiver, filter)
    super.onResume()
  }

  override def onPause(): Unit ={
    unregisterReceiver(receiver)
    super.onPause()
  }
  /*
  class NotificationsBCReceiver extends BroadcastReceiver {
    override def onReceive(context: Context, intent: Intent): Unit = {
      Log.d("NotificationsBCReceiver", "onReceive")
      runUi( imgApp <~ setImageDrawable(
        getPackageManager.getApplicationIcon(intent.getStringExtra("package"))))
    }
  }
  */

}