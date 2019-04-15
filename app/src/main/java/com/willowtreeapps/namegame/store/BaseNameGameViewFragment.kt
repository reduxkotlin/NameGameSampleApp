package com.willowtreeapps.namegame.store

import androidx.fragment.app.Fragment
import com.willowtreeapps.common.Presenter
import com.willowtreeapps.common.View
import com.willowtreeapps.common.ui.GameResultsPresenter
import com.willowtreeapps.namegame.NameGameApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class BaseNameGameViewFragment<TPresenter: Presenter<*>>: Fragment(), CoroutineScope, View<TPresenter> {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override lateinit var presenter: TPresenter

    override fun onResume() {
        super.onResume()
        NameGameApp.gameEngine().attachView(this)
    }

    override fun onPause() {
        super.onPause()
        NameGameApp.gameEngine().detachView(this)
    }
}
