package com.DontTouchMeApp.ui.main

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import macroid.Tweak

/**
  * Created by @gutiory on 31/8/16.
  */
object MyTweaks {

  def setImageDrawable(img: Drawable) = Tweak[ImageView] {
    imageView => imageView.setImageDrawable(img)
  }
}
