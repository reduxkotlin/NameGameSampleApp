package com.willowtreeapps.namegame.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beyondeye.reduks.StoreSubscriber
import com.beyondeye.reduks.StoreSubscription
import com.willowtreeapps.common.AppState
import com.willowtreeapps.common.view.StartScreen
import com.willowtreeapps.namegame.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class StartFragment : Fragment(), CoroutineScope, StartScreen {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.content_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        NameGameApp.instance.presenter.attachView(this)
        btn_start.setOnClickListener {
            activity?.runOnUiThread {
                NameGameApp.instance.presenter.startGame()
            }
        }
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

    override fun onDestroyView() {
        NameGameApp.instance.presenter.detachView()
        super.onDestroyView()
    }
}
