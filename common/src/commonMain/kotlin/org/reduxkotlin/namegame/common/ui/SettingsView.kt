package org.reduxkotlin.namegame.common.ui

import org.reduxkotlin.namegame.common.AppState
import org.reduxkotlin.namegame.common.boundary.toViewState
import org.reduxkotlin.Presenter
import org.reduxkotlin.View

interface SettingsView: GameBaseView {
    fun showSettings(viewState: SettingsViewState)
    fun askForMicPermissions()

    override fun presenter(): Presenter<View, AppState> = settingsPresenter
}

val settingsPresenter = presenter<SettingsView> {{
    withSingleField({ it.settings }) { showSettings(state.settings.toViewState()) }
}}
