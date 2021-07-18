package com.exam.home.dailytest.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.exam.home.dailytest.R

class RxJavaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java)
        MergeOperationDemo().test1();
    }
}
