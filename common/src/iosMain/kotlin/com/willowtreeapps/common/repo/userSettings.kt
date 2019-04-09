package com.willowtreeapps.common.repo

import com.russhwolf.settings.PlatformSettings
import com.russhwolf.settings.Settings

actual fun userSettings(context: Any?): Settings = PlatformSettings.Factory().create()

