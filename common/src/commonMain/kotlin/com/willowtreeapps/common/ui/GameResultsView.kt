package com.willowtreeapps.common.ui


interface GameResultsView : GameBaseView {
    fun showResults(viewState: GameResultsViewState)

    override fun presenter() = gameResultsPresenter
}
