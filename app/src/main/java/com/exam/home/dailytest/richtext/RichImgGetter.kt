package com.exam.home.dailytest.richtext

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.Html
import android.widget.TextView
import com.exam.home.dailytest.Utils
import com.squareup.picasso.Picasso


/**
 * Created by Yangxy on 2019-11-22
 * description -- 使用Glide加载网络图片，或者加载本地默认图片
 */
class RichImgGetter(var context: Context, private var tv: TextView) : Html.ImageGetter {

    override fun getDrawable(source: String?): Drawable? {
        //处理base64格式图片
        if (source?.contains(";base64") == true) {
            val bitmap = Utils.base64ToBitmap(source)
            val drawable = BitmapDrawable(context.resources, bitmap)
            Utils.setDounds(context, drawable, tv)
            return drawable

        } else {
            //替换成本地图片
//            val drawable = context.resources.getDrawable(R.drawable.ic_launcher_background)
//            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
//            return drawable

            // 替换成picasso加载的网络图片
            val urlDrawable = DrawableHolder(context, tv)
            Picasso.with(context).load(source).into(urlDrawable)
            return urlDrawable
        }
    }
}

