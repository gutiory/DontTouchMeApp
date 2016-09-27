package com.DontTouchMeApp.ui.main

import android.widget.{ImageView, TextView, Button}
import macroid.Tweak
import macroid.contrib.LpTweaks._
/**
  * Created by @gutiory on 20/3/16.
  */
trait Styles {

  val buttonStl : Tweak[Button] = matchWidth
  val textStl : Tweak[TextView] = matchWidth
  val imageStl : Tweak[ImageView] = matchParent
}
