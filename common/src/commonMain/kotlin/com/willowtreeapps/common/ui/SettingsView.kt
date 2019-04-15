package com.willowtreeapps.common.ui

import com.willowtreeapps.common.SettingsViewState
import com.willowtreeapps.common.View

interface SettingsView: View<SettingsPresenter> {
    fun showSettings(viewState: SettingsViewState)
}