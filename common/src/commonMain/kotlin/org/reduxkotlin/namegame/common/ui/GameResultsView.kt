package org.reduxkotlin.namegame.common.ui

import org.reduxkotlin.namegame.common.boundary.toGameResultsViewState


interface GameResultsView : GameBaseView {
    fun showResults(viewState: GameResultsViewState)

    override fun presenter() = gameResultsPresenter
}

val gameResultsPresenter = presenter<GameResultsView> {{
    withAnyChange { showResults(state.toGameResultsViewState()) }
}}
