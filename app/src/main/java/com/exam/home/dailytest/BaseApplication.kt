package com.exam.home.dailytest

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Created by Yangxy on 2019-11-22
 * description --
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initFresco()
    }

    private fun initFresco() {
        Fresco.initialize(this)
    }
}