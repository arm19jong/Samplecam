package com.shoppening.samplecam

import android.app.Activity
import android.app.Fragment
import android.os.Bundle
import android.support.v4.app.FragmentActivity

/**
 * Created by root on 10/6/17.
 */
class CameraActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        if (null == savedInstanceState) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, Camera2BasicFragment.newInstance())
                    .commit()
        }
    }

}