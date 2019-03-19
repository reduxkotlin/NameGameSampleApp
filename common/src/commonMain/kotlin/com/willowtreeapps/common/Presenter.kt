package com.willowtreeapps.common

import com.beyondeye.reduks.StoreSubscriber
import com.willowtreeapps.common.boundary.toRoundViewState
import com.willowtreeapps.common.view.QuestionScreen
import com.willowtreeapps.common.view.RoundCompleteScreen
import com.willowtreeapps.common.view.StartScreen
import com.willowtreeapps.common.view.View
import kotlin.coroutines.CoroutineContext

/**
 * Presenter for the entire application.  Each view must attach/detach itself as it becomes visible/not visible.
 */
class Presenter(val game: Game, networkContext: CoroutineContext) : StoreSubscriber<AppState> {

    init {
        game.appStore.subscribe(this)
    }

    val networkThunks = NetworkThunks(networkContext, game.appStore)

    var currentView: View? = null

    fun attachView(view: View) {
        currentView = view
        onStateChange()
    }

    fun detachView() {
        currentView = null
    }

    fun startGame() {
        game.appStore.dispatch(networkThunks.fetchProfiles())
    }

    override fun onStateChange() {
        val state = game.appStore.state
        val view = currentView
        when (view) {
            is StartScreen -> {
                if (state.isLoadingProfiles) {
                    view.showLoading()
                } else {
                    view.hideLoading()
                }
            }
            is QuestionScreen -> {
                view.showProfile(state.toRoundViewState())
            }
            is RoundCompleteScreen -> {
            }
            else -> throw IllegalStateException("Screen not handled in Presenter.onStateChanged()")
        }
    }
}
