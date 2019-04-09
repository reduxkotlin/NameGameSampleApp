package com.willowtreeapps.common.ui

import com.willowtreeapps.common.View

interface StartView : View {
    fun showLoading()
    fun hideLoading()
    fun showError(msg: String)
}
