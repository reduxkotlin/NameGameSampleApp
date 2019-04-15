package com.willowtreeapps.common.ui

import com.willowtreeapps.common.View

interface StartView : View<StartPresenter> {
    fun showLoading()
    fun hideLoading()
    fun showError(msg: String)
}
