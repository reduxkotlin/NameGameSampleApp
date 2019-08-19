package com.willowtreeapps.namegame.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.willowtreeapps.common.middleware.UiActions
import com.willowtreeapps.common.ui.StartView
import com.willowtreeapps.namegame.*
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : Fragment(), StartView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_start.setOnClickListener { dispatch(UiActions.StartGameTapped()) }
        btn_settings.setOnClickListener { dispatch(UiActions.SettingsTapped()) }
    }

    override fun hideLoading() {
        loading_spinner.visibility = View.GONE
    }

    override fun showLoading() {
        loading_spinner.visibility = View.VISIBLE
    }

    override fun showError(msg: String) {
        txt_error.text = msg
    }
}
