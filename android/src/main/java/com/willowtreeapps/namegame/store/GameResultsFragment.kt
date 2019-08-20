package com.willowtreeapps.namegame.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.willowtreeapps.common.middleware.UiActions
import com.willowtreeapps.common.ui.GameResultsViewState
import com.willowtreeapps.common.ui.GameResultsView
import com.willowtreeapps.namegame.MainActivity
import com.willowtreeapps.namegame.R
import com.willowtreeapps.namegame.dispatch
import kotlinx.android.synthetic.main.fragment_game_results.*

class GameResultsFragment : BaseNameGameViewFragment<GameResultsView>(), GameResultsView, MainActivity.IOnBackPressed {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_game_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
    }

    private fun initViews() {
        btn_start_over.setOnClickListener {
            //TODO is this needed?
//            NameGameApp.gameEngine().detachView(this)
            dispatch(UiActions.StartOverTapped())
        }
    }

    override fun showResults(viewState: GameResultsViewState) {
        txt_questionTitle.text = viewState.resultsText
        txt_message.text = viewState.messageText
    }

    override fun onBackPressed(): Boolean {
//        presenter.onBackPressed()
        return false
    }

}