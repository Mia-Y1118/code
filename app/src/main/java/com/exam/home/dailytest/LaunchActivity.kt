package com.exam.home.dailytest

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_launch.*

import permissions.dispatcher.*


@RuntimePermissions
class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        checkPermission()
    }


    @NeedsPermission(Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun goMain() {
        tv.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }


    private fun checkPermission(){
        goMainWithPermissionCheck()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @OnPermissionDenied(Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun onPermissionDenied() {
        checkPermission()
    }
}
