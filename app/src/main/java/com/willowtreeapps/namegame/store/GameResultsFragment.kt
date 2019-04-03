package com.willowtreeapps.namegame.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.willowtreeapps.common.GameResultsViewState
import com.willowtreeapps.common.ui.GameResultsPresenter
import com.willowtreeapps.common.ui.GameResultsView
import com.willowtreeapps.namegame.MainActivity
import com.willowtreeapps.namegame.NameGameApp
import com.willowtreeapps.namegame.R
import kotlinx.android.synthetic.main.fragment_game_results.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class GameResultsFragment : Fragment(), CoroutineScope, GameResultsView, MainActivity.IOnBackPressed {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var presenter: GameResultsPresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_game_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
    }

    private fun initViews() {
        btn_start_over.setOnClickListener {
            NameGameApp.gameEngine().detachView(presenter!!)
            presenter?.startOverTapped()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter = NameGameApp.gameEngine().attachView(this) as GameResultsPresenter
    }

    override fun onPause() {
        super.onPause()
        NameGameApp.gameEngine().detachView(presenter!!)
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