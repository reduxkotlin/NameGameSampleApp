package com.willowtreeapps.common.repo

import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

actual fun userSettings(context: Any?): Settings = AppleSettings(NSUserDefaults.standardUserDefaults)

