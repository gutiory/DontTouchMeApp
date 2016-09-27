package com.DontTouchMeApp.ui.main

import android.graphics.Bitmap
import android.view.ViewGroup.LayoutParams._
import android.widget.{ImageView, Button, LinearLayout, TextView}
import macroid.{IdGeneration, ActivityContext}
import macroid.FullDsl._

/**
  * Created by @gutiory on 15/3/16.
  */
trait Layouts extends Styles
  with IdGeneration{

  var imgApp = slot[ImageView]
  var btnPepe = slot[Button]
  var tvTexto = slot[TextView]

  def mainLayout(implicit ctx: ActivityContext) = {
    getUi(
      l[LinearLayout](
        //w[Button] <~ wire(btnPepe) <~ id(Id.btnPepe) <~ buttonStl <~ text("PEPE"),
        //w[TextView] <~ wire(tvTexto) <~ id(Id.tvTexto) <~ textStl <~ text("HOLA"),
        w[ImageView] <~ wire(imgApp) <~ id(Id.imgApp) <~ imageStl
      )
    )
  }


}
