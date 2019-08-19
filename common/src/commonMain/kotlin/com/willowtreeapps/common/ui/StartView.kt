package com.willowtreeapps.common.ui

import com.willowtreeapps.common.AppState
import com.willowtreeapps.common.external.Presenter
import com.willowtreeapps.common.external.View


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
