package com.willowtreeapps.common.ui

import com.willowtreeapps.common.GameResultsViewState
import com.willowtreeapps.common.View

interface GameResultsView : View {
    fun showResults(viewState: GameResultsViewState)
}
