package com.DontTouchMeApp.database

/**
  * Created by @gutiory on 15/8/16.
  */

case class Event(id: Option[Int] = None, pkgName: String, eventType: Int, timeTag: Double)