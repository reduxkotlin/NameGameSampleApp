package com.willowtreeapps.namegame

import android.graphics.Rect
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.willowtreeapps.namegame.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

@GlideModule
class MyAppGlideModule : AppGlideModule()

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN.or(View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        setContentView(R.layout.activity_main)
        scrimLayout.setOnInsetsCallback {
            rootContent.setPadding(0, -it.top, 0, 0)
        }


        val navController = findNavController(R.id.nav_host_fragment)

    }

    private fun drawerToggleClickListener(view: View): Unit {
        drawer_layout.openDrawer(GravityCompat.START)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val view: View? = currentFocus
            if (view?.tag == "quantityPicker") {
                val outRect = Rect()
                view.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    view.clearFocus()
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    override fun onSupportNavigateUp(): Boolean = Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
