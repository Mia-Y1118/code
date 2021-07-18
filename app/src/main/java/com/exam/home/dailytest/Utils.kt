package com.exam.home.dailytest

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Base64
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager
import android.widget.TextView
import kotlin.math.roundToInt

/**
 * Created by Yangxy on 2019-11-22
 * description --
 */
class Utils {
    companion object {
        /**
         * base64转化成Bitmap
         *
         * @param string
         * @return
         */
        fun base64ToBitmap(string: String?): Bitmap? {
            if (string == null) return null
            var bitmap: Bitmap? = null
            try {
                val bitmapArray = Base64.decode(string, Base64.DEFAULT)
                bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.size)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return bitmap
        }

        /**
         * dp值转化为px
         *
         * @param context
         * @param dp
         * @return
         */
        fun dip2px(context: Context?, dp: Float): Float {
            if (context == null) return 0f
            val metrics = getDisplayMetrics(context) ?: return 0f
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics)
        }

        private fun getDisplayMetrics(context: Context): DisplayMetrics? {
            val wm: WindowManager? = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            if (wm?.defaultDisplay == null) return null
            val display = wm.defaultDisplay
            val metrics = DisplayMetrics()
            display.getMetrics(metrics)
            return metrics
        }


        fun setDounds(context: Context, drawable: Drawable?, tv: TextView) {

            val intrinsicWidth = drawable?.intrinsicWidth ?: return
            val intrinsicHeight = drawable.intrinsicHeight

            val defaultProportion = intrinsicWidth / intrinsicHeight

            val displayMetrics = context.resources.displayMetrics

            val textWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 72f, displayMetrics).toInt()

            val drawableWidth = dip2px(context, intrinsicWidth.toFloat()).roundToInt()
            val width = (displayMetrics.widthPixels - textWidth).coerceAtMost(drawableWidth)
            val height = (width.toFloat() / defaultProportion).toInt()

            if (drawable.bounds.right != width || drawable.bounds.bottom != height) {
                drawable.setBounds(0, 0, width, height)
                tv.text = tv.text //refresh text
            }
        }
    }


}