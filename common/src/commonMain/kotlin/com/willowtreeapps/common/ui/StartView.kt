package com.willowtreeapps.common.ui

interface StartView : View<StartPresenter> {
    fun showLoading()
    fun hideLoading()
    fun showError(msg: String)
}
