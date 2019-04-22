package com.willowtreeapps.common.repo

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings


actual fun userSettings(context: Any?): Settings = AndroidSettings((context as Context).getSharedPreferences("settings", MODE_PRIVATE))
