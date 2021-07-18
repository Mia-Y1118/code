package com.exam.home.dailytest.richtext

import android.content.Context
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.text.Html
import android.util.Log
import android.widget.TextView
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.generic.GenericDraweeHierarchy
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder
import com.facebook.drawee.view.DraweeHolder
import com.facebook.imagepipeline.image.ImageInfo


/**
 * Created by Yangxy on 2019-11-25
 * description -- Fresco的图片ImageGetter
 */
class FrescoImageGetter(private var context: Context, private var tv: TextView) : Html.ImageGetter {

    override fun getDrawable(source: String?): Drawable? {

//        val mHierarchy = GenericDraweeHierarchyBuilder(context.resources).build()
//        val draweeHolder = DraweeHolder(mHierarchy)
//        val controller = Fresco.newDraweeControllerBuilder()
//            .setUri(source)
//            .setOldController(draweeHolder.controller)
//            .setControllerListener(object : BaseControllerListener<ImageInfo>() {
//                override fun onFinalImageSet(id: String, imageInfo: ImageInfo?, animatable: Animatable?) {
//                    val drawable = draweeHolder.hierarchy.topLevelDrawable
//                    val width = imageInfo?.width ?: 0
//                    val height = imageInfo?.height ?: 0
//                    drawable.setBounds(0, 0, width, height)
//                    Log.i("yxy", "width:" + imageInfo?.width + ",height:" + imageInfo?.height + "tv.text = ${tv.text}")
//                    tv.text = tv.text
//                }
//            })
//            .build()
//        draweeHolder.controller = controller
        val mHierarchy = GenericDraweeHierarchyBuilder(context.resources).build()
        val draweeHolder = DraweeHolder.create(mHierarchy, context)
        val controller = Fresco.newDraweeControllerBuilder()
            .setUri(source)
            .setOldController(draweeHolder.controller)
            .setControllerListener(object : BaseControllerListener<ImageInfo>() {
                override fun onFinalImageSet(id: String, imageInfo: ImageInfo?, animatable: Animatable?) {
                    val drawable = draweeHolder.hierarchy.topLevelDrawable
                    val width = imageInfo?.width ?: 0
                    val height = imageInfo?.height ?: 0
                    drawable.setBounds(0, 0, width, height)
                    Log.i("yxy", "width:" + imageInfo?.width + ",height:" + imageInfo?.height + "tv.text = ${tv.text}")
                }
            })
            .build()

        draweeHolder.controller = controller
        val drawable = draweeHolder.hierarchy.topLevelDrawable
        return draweeHolder.hierarchy.topLevelDrawable
    }
}