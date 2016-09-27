package com.DontTouchMeApp.database

import slick.driver.SQLiteDriver.api._

/**
  * Created by @gutiory on 15/8/16.
  */
trait SlickDatabaseConfig {

  class EventsTable(tag: Tag) extends Table[Event](tag, "EVENTS"){
    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def pkgName = column[String]("PKG_NAME")
    def eventType = column[Int]("EVENT_TYPE")
    def timeTag = column[Double]("TIMETAG")

    def * = (id.?, pkgName, eventType, timeTag) <> (Event.tupled, Event.unapply)

  }

  lazy val eventsTable = TableQuery[EventsTable]

}
