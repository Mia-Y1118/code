package com.exam.home.dailytest.initSequence

import android.util.Log

/**
 * Created by Yangxy on 2019-11-22
 */
open class ParentClass constructor(var constructorA: Boolean = true) {



    constructor(a: Int, b: Int) : this() {
        Log.d("yxy", "次级构造方法 = $a, b=$b")
    }


    private var commenA = true

    companion object {
        val parent by lazy {
            ParentClass(false)
            Log.d("yxy", "companion object parent")
        }



        val compainA = false

        init {
            Log.d("yxy", "companion object init")
        }

        val parent1 = ParentClass(false).apply {
            Log.d("yxy", "companion object parent1")

        }
    }

    init {
        Log.d("yxy", "init parent 非静态变量=$commenA ; 伴生变量= $compainA ;构造函数变量=$constructorA ")
    }

}