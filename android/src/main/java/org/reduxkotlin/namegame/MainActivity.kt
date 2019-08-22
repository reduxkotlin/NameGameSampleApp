package org.reduxkotlin.namegame

import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.willowtreeapps.hyperion.core.Hyperion
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.activity_main.*


@GlideModule
class MyAppGlideModule : AppGlideModule()

class MainActivity : AppCompatActivity() {

    companion object {
        const val RECORD_REQUEST_CODE = 52
    }

    interface IOnBackPressed {
        fun onBackPressed(): Boolean
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN.or(View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        setContentView(R.layout.activity_main)
        scrimLayout.setOnInsetsCallback {
            rootContent.setPadding(0, -it.top, 0, 0)
        }
        drawer_layout.setOnTouchListener(tripleTapDetector)

    }

//    override fun onSupportNavigateUp(): Boolean = Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()

    override fun onBackPressed() {
        val navHostFragment =
                this.supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)
        if (currentFragment is IOnBackPressed)
            (currentFragment as IOnBackPressed).onBackPressed()
        super.onBackPressed()
    }

    var tripleTapDetector: View.OnTouchListener = object : View.OnTouchListener {
        internal var handler = Handler()

        internal var numberOfTaps = 0
        internal var lastTapTimeMs: Long = 0
        internal var touchDownMs: Long = 0

        override fun onTouch(v: View, event: MotionEvent): Boolean {

            when (event.action) {
                MotionEvent.ACTION_DOWN -> touchDownMs = System.currentTimeMillis()
                MotionEvent.ACTION_UP -> {
                    handler.removeCallbacksAndMessages(null)

                    if (System.currentTimeMillis() - touchDownMs > ViewConfiguration.getTapTimeout()) {
                        //it was not a tap

                        numberOfTaps = 0
                        lastTapTimeMs = 0
                        return true
                    }

                    if (numberOfTaps > 0 && System.currentTimeMillis() - lastTapTimeMs < ViewConfiguration.getDoubleTapTimeout()) {
                        numberOfTaps += 1
                    } else {
                        numberOfTaps = 1
                    }

                    lastTapTimeMs = System.currentTimeMillis()

                    if (numberOfTaps == 3) {
                        Hyperion.open()
                        //handle triple tap
                    } else if (numberOfTaps == 2) {
                        handler.postDelayed({
                            //handle double tap
                        }, ViewConfiguration.getDoubleTapTimeout().toLong())
                    }
                }
            }

            return true
        }
    }
}
