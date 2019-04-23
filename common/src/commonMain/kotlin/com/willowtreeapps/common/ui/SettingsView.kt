package com.willowtreeapps.common.ui

import com.willowtreeapps.common.SettingsViewState

interface SettingsView: View<SettingsPresenter> {
    fun showSettings(viewState: SettingsViewState)
    fun askForMicPermissions()
}