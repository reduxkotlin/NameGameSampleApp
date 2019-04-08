package com.willowtreeapps.common.ui

import com.willowtreeapps.common.SettingsViewState
import com.willowtreeapps.common.View

interface SettingsView: View {
    fun showSettings(viewState: SettingsViewState)
}