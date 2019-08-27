package org.reduxkotlin.namegame.common.ui

import org.reduxkotlin.namegame.common.AppState
import org.reduxkotlin.Presenter
import org.reduxkotlin.View


interface StartView : GameBaseView {
    fun showLoading()
    fun hideLoading()
    fun showError(msg: String)

    override fun presenter(): Presenter<View, AppState> = startPresenter
}

val startPresenter = presenter<StartView> {
    {
        select { state.isLoadingItems } then {
            if (state.isLoadingItems) {
                showLoading()
            } else {
                hideLoading()
            }
        }

        select { state.errorLoadingItems } then { showError(state.errorMsg) }
    }
}
