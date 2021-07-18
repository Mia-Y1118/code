package com.exam.home.dailytest.initSequence

import android.util.Log

/**
 * Created by Yangxy on 2019-11-22
 */
class ChildClass(var constructorChild : Boolean) : ParentClass(constructorChild) {

    constructor(a: Int, b: Int) : this(false) {
        Log.d("yxy", " ChildClass 次级构造方法 = $a, b=$b")
    }


    companion object {
        val parent by lazy {
            ChildClass(false)
            Log.d("yxy", "ChildClass companion object parent")
        }



        val compainA = false

        init {
            Log.d("yxy", "ChildClass companion object init")
        }

        val parent1 = ChildClass(false).apply {
            Log.d("yxy", "ChildClass companion object parent1")

        }
    }
    private var commenA = true


    init {
        Log.d("yxy", "ChildClass init parent 非静态变量=$commenA ; 伴生变量= $compainA ;构造函数变量=$constructorA ")
    }


}