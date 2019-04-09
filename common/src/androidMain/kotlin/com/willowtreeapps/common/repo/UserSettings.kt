package com.willowtreeapps.common.repo

import android.app.Application
import android.content.Context
import com.russhwolf.settings.PlatformSettings



actual fun userSettings(context: Any?) = PlatformSettings.Factory(context as Context).create()
