package com.willowtreeapps.common.ui

import com.willowtreeapps.common.AppState
import com.willowtreeapps.common.boundary.toViewState
import com.willowtreeapps.common.external.Presenter
import com.willowtreeapps.common.external.View

interface SettingsView: GameBaseView {
    fun showSettings(viewState: SettingsViewState)
    fun askForMicPermissions()

    override fun presenter(): Presenter<View, AppState> = settingsPresenter
}

val settingsPresenter = presenter<SettingsView> {{
    withSingleField({ it.settings }) { showSettings(state.settings.toViewState()) }
}}
