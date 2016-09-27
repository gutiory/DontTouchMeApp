package com.DontTouchMeApp.database

import android.content.Context
import android.database.sqlite.{SQLiteDatabase, SQLiteOpenHelper}
import com.DontTouchMeApp.database.SlickDatabaseHelper._
import com.fortysevendeg.mvessel.AndroidDriver
import slick.driver.SQLiteDriver.api._

import scala.concurrent.{Future, ExecutionContext}


/**
  * Created by @gutiory on 15/8/16.
  */
class SlickDatabaseHelper(context: Context)
  extends SQLiteOpenHelper(context, databaseName, null, databaseVersion)
    with SlickDatabaseConfig {

  lazy val database = {
    val sqliteDatabase = getReadableDatabase
    AndroidDriver.register()
    Database.forURL(
      url = "jdbc:sqlite:" + sqliteDatabase.getPath,
      driver = driverName)
  }
  val driverName = "com.fortysevendeg.mvessel.AndroidDriver"


  override def onCreate(db: SQLiteDatabase): Unit = {
    eventsTable.schema.create.statements.foreach(db.execSQL)

  }

  override def onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int): Unit = {
    throw new IllegalStateException("Can't make an upgrade")

  }

  def insertEvent(event: Event)(implicit executionContext: ExecutionContext)  =
    database.run(
          eventsTable += event
    )


}

object SlickDatabaseHelper {
  val databaseName = "events.db"
  val databaseVersion = 1
}
