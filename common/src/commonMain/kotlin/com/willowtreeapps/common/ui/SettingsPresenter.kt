package com.willowtreeapps.common.ui

import com.willowtreeapps.common.boundary.toViewState

val settingsPresenter = presenter<SettingsView> {{
    withSingleField({ it.settings }) { showSettings(state.settings.toViewState()) }
}}
