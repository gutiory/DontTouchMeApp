package com.DontTouchMeApp.services

import android.content.ContextWrapper
import android.util.Log
import com.DontTouchMeApp.database.Event
import com.loopj.android.http.{SyncHttpClient, TextHttpResponseHandler}
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONObject
/**
  * Created by @gutiory on 28/9/16.
  *
  * This object will manage all data sent to the server
  */
trait ServerCommunication {
  val url : String = "http://192.168.1.69:9000/events/addJson"
  implicit val contextProvider : ContextWrapper

  // A new event is sent to the server by a POST request
  def postNewEvent(e: Event) {
    Log.d("ServerCommunication postNewEvent => ", "postNewEvent")
    val client = new SyncHttpClient()
    val reqParams = new JSONObject()
    reqParams.put("id", e.id.getOrElse(1))
    reqParams.put("pkgName", e.pkgName.toString)
    reqParams.put("eventType", e.eventType)
    reqParams.put("timeTag", e.timeTag)

    val entity = new StringEntity(reqParams.toString)

    client.post(contextProvider, url, entity, "application/json", new TextHttpResponseHandler() {
      override def onFailure(i: Int, headers: Array[Header], s: String, throwable: Throwable): Unit = {
        Log.d("ServerCommunication response => ", s.toString)
      }

      override def onSuccess(i: Int, headers: Array[Header], s: String): Unit = {
        Log.d("ServerCommunication response error=> ", s.toString)
      }
    })
  }
}
