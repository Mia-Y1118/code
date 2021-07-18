package com.exam.home.dailytest.richtext

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.TextView
import com.exam.home.dailytest.Utils
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

/**
 * Created by Yangxy on 2019-11-22
 * description --
 */
class DrawableHolder(private var context: Context, var tv: TextView) : BitmapDrawable(), Target {

    private var drawable: Drawable? = null

    override fun draw(canvas: Canvas) {
        drawable?.draw(canvas)
    }

    private fun setDrawable(drawable: Drawable?) {
        Log.d("yxy", "${bounds.left} + ${bounds.top} + ${bounds.bottom} + ${bounds.right}")
        if (drawable == null) return
        val imgWidth = Utils.dip2px(context, drawable.intrinsicWidth.toFloat()).toInt()
        val imgHeight = Utils.dip2px(context, drawable.intrinsicHeight.toFloat()).toInt()
        setBounds(0, 0, imgWidth, imgHeight)
        Utils.setDounds(context, drawable, tv)
        this.drawable = drawable
    }

    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        setDrawable(placeHolderDrawable)
    }

    override fun onBitmapFailed(errorDrawable: Drawable?) {
        setDrawable(errorDrawable)
    }

    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        setDrawable(BitmapDrawable(context.resources, bitmap))
    }
}