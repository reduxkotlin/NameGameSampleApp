package com.willowtreeapps.common.ui

import com.willowtreeapps.common.boundary.toGameResultsViewState


interface GameResultsView : GameBaseView {
    fun showResults(viewState: GameResultsViewState)

    override fun presenter() = gameResultsPresenter
}

val gameResultsPresenter = presenter<GameResultsView> {{
    withAnyChange { showResults(state.toGameResultsViewState()) }
}}
