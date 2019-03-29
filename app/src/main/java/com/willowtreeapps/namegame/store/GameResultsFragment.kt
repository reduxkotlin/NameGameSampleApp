package com.willowtreeapps.namegame.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.willowtreeapps.common.GameResultsPresenter
import com.willowtreeapps.common.GameResultsViewState
import com.willowtreeapps.common.view.GameResultsScreen
import com.willowtreeapps.namegame.MainActivity
import com.willowtreeapps.namegame.NameGameApp
import com.willowtreeapps.namegame.R
import kotlinx.android.synthetic.main.fragment_game_results.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class GameResultsFragment : Fragment(), CoroutineScope, GameResultsScreen, MainActivity.IOnBackPressed {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    var presenter: GameResultsPresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_game_results, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
    }

    private fun initViews() {
        btn_start_over.setOnClickListener {
            NameGameApp.instance.presenterFactory.detachView(presenter!!)
            presenter?.startOverTapped()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter = NameGameApp.instance.presenterFactory.attachView(this) as GameResultsPresenter
    }

    override fun onPause() {
        super.onPause()
        NameGameApp.instance.presenterFactory.detachView(presenter!!)
    }

    override fun showResults(viewState: GameResultsViewState) {
        txt_results.text = viewState.resultsText
        txt_message.text = viewState.messageText
    }

    override fun onBackPressed(): Boolean {
        presenter?.onBackPressed()
        return false
    }

}