package com.willowtreeapps.common.ui

import com.willowtreeapps.common.GameResultsViewState

interface GameResultsView : View<GameResultsPresenter> {
    fun showResults(viewState: GameResultsViewState)
}
