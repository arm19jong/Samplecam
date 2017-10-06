package com.shoppening.samplecam

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.util.Log
import android.view.View
import com.github.florent37.camerafragment.CameraFragment
import com.github.florent37.camerafragment.configuration.Configuration
import com.github.florent37.camerafragment.listeners.CameraFragmentResultListener
import com.github.florent37.camerafragment.listeners.CameraFragmentStateListener
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {
    private val REQUEST_CAMERA_PERMISSIONS = 931
    private val REQUEST_PREVIEW_CODE = 1001

    val FRAGMENT_TAG = "camera"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @SuppressLint("MissingPermission")
    fun addCamera(){
        addCameraButton.visibility = View.GONE
        cameraLayout.visibility = View.VISIBLE
        val builder = Configuration.Builder()
        builder.setCamera(Configuration.CAMERA_FACE_FRONT)
                .setFlashMode(Configuration.FLASH_MODE_OFF)
                .setMediaAction(Configuration.MEDIA_ACTION_PHOTO)
                .setMediaQuality(Configuration.MEDIA_QUALITY_MEDIUM)

        val cameraFragment = CameraFragment.newInstance(builder.build())
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, cameraFragment, FRAGMENT_TAG)
                .commit()

        if(cameraFragment != null){
            cameraFragment.setResultListener(object :CameraFragmentResultListener{
                override fun onVideoRecorded(filePath: String?) {
                    Log.d("test", filePath)
                }

                override fun onPhotoTaken(bytes: ByteArray?, filePath: String?) {
                    Log.d("test", filePath)
                }
            })

            cameraFragment.setStateListener(object : CameraFragmentStateListener{
                override fun onFlashAuto() {
                    flash_switch_view.text = "auto"
                }

                override fun onFlashOn() {
                    flash_switch_view.text = "on"
                }

                override fun onFlashOff() {
                    flash_switch_view.text = "off"
                }

                override fun onCurrentCameraFront() {
                    front_back_camera_switcher.text = "font"
                }

                override fun onCurrentCameraBack() {
                    front_back_camera_switcher.text = "back"
                }

                override fun onCameraSetupForVideo() {
                    photo_video_camera_switcher.text = "video"
                    record_button.text = "capture video"
                    flash_switch_view.visibility = View.GONE
                }

                override fun onRecordStateVideoReadyForRecord() {
                    record_button.text = "take video"
                }

                override fun onStartVideoRecord(outputFile: File?) {

                }

                override fun onRecordStateVideoInProgress() {
                    record_button.text = "stop"
                }

                override fun onStopVideoRecord() {
                    settings_view.visibility = View.VISIBLE
                }

                override fun onCameraSetupForPhoto() {
                    photo_video_camera_switcher.text = "photo"
                    record_button.text = "take photo"
                    flash_switch_view.visibility = View.VISIBLE
                }

                override fun shouldRotateControls(degrees: Int) {
//                    ViewCompat.setRotation(cameraSwitchView, degrees.toFloat())
//                    ViewCompat.setRotation(mediaActionSwitchView, degrees.toFloat())
//                    ViewCompat.setRotation(flashSwitchView, degrees.toFloat())
                }

                override fun onRecordStatePhoto() {
                    record_button.text = "take photo"
                }

            })
        }
    }
}
