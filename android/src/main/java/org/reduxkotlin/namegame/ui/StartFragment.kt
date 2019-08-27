package org.reduxkotlin.namegame.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.reduxkotlin.namegame.common.middleware.UiActions
import org.reduxkotlin.namegame.common.ui.StartView
import kotlinx.android.synthetic.main.fragment_start.*
import org.reduxkotlin.namegame.R
import org.reduxkotlin.namegame.dispatch

class StartFragment : BaseNameGameViewFragment<StartView>(), StartView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_start.setOnClickListener { dispatch(UiActions.StartGameTapped()) }
        btn_settings.setOnClickListener { dispatch(UiActions.SettingsTapped()) }
    }

    override fun hideLoading() {
        loading_spinner.visibility = View.GONE
    }

    override fun showLoading() {
        activity?.runOnUiThread {
            loading_spinner.visibility = View.VISIBLE
        }
    }

    override fun showError(msg: String) {
        txt_error.text = msg
    }
}
