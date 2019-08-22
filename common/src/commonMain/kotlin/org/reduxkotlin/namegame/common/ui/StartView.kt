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

val startPresenter = presenter<StartView> {{
    withSingleField({ it.isLoadingItems }) {
        if (state.isLoadingItems) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    withSingleField({ it.errorLoadingItems }) {
        showError(state.errorMsg)
    }
}}
