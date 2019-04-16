package com.willowtreeapps.namegame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.navigation.Navigation
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import kotlinx.android.synthetic.main.app_bar_main.*


@GlideModule
class MyAppGlideModule : AppGlideModule()

class MainActivity : AppCompatActivity() {

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
    }

    override fun onSupportNavigateUp(): Boolean = Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()

    override fun onBackPressed() {
//        val navHostFragment =
//                this.supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
//        val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)
//        if (currentFragment is IOnBackPressed)
//            (currentFragment as IOnBackPressed).onBackPressed()
        super.onBackPressed()
    }
}
