package com.willowtreeapps.common.ui

import com.willowtreeapps.common.boundary.toGameResultsViewState

val gameResultsPresenter = presenter<GameResultsView> {{
    withAnyChange { showResults(state.toGameResultsViewState()) }
}}