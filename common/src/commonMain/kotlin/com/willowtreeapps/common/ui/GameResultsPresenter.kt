package com.willowtreeapps.common.ui

import com.beyondeye.reduks.SelectorSubscriberFn
import com.willowtreeapps.common.Actions
import com.willowtreeapps.common.GameEngine
import com.willowtreeapps.common.boundary.toGameResultsViewState

class GameResultsPresenter(private val engine: GameEngine) : Presenter<GameResultsView>() {

    override fun recreateView() {
        view?.showResults(engine.state.toGameResultsViewState())
    }

    override fun makeSubscriber() = SelectorSubscriberFn(engine.appStore) {
        withAnyChange { view?.showResults(state.toGameResultsViewState()) }
    }

    fun startOverTapped() {
        engine.dispatch(Actions.StartOverAction())
    }

    fun onBackPressed() {
        TODO("Handle back press from Game Results")
    }
}