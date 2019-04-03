package com.willowtreeapps.namegame.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.willowtreeapps.common.ui.StartPresenter
import com.willowtreeapps.common.ui.StartView
import com.willowtreeapps.namegame.*
import kotlinx.android.synthetic.main.fragment_start.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class StartFragment : Fragment(), CoroutineScope, StartView {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    var presenter: StartPresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_start.setOnClickListener {
            activity?.runOnUiThread {
                presenter?.startGame()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        presenter = NameGameApp.gameEngine().attachView(this) as StartPresenter
    }

    override fun onPause() {
        super.onPause()
        NameGameApp.gameEngine().detachView(presenter!!)
    }

    override fun hideLoading() {
        activity?.runOnUiThread {
            loading_spinner.visibility = View.GONE
        }
    }

    override fun showLoading() {
        activity?.runOnUiThread {
            loading_spinner.visibility = View.VISIBLE
        }
    }
}
