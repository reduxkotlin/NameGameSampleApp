package com.willowtreeapps.common

import com.beyondeye.reduks.*
import com.willowtreeapps.common.ui.*
import kotlin.coroutines.CoroutineContext

/**
 * PresenterFactory that creates presenters for all views in the application.
 * Each view must attach/detach itself as it becomes visible/not visible.
 * Attaching returns a presenter to the view.
 */
internal class PresenterFactory(private val gameEngine: GameEngine, networkContext: CoroutineContext) : StoreSubscriber<AppState> {

    private val networkThunks = NetworkThunks(networkContext, gameEngine.appStore)
    private val presenters = mutableSetOf<Presenter>()
    private var subscription: StoreSubscription? = null


    fun attachView(view: View): Presenter {
        if (subscription == null) {
            subscription = gameEngine.appStore.subscribe(this)
        }
        val presenter = when (view) {
            is StartView -> StartPresenter(view, gameEngine.appStore, networkThunks)
            is QuestionView -> QuestionPresenter(view, gameEngine.appStore, gameEngine.vibrateUtil)
            is GameResultsView -> GameResultsPresenter(view, gameEngine.appStore)
            else -> throw IllegalStateException("Screen $view not handled")
        }
        presenters.add(presenter)
        presenter.onStateChange(gameEngine.appStore.state)
        return presenter
    }

    fun detachView(presenter: Presenter) {
        if (presenters.contains(presenter)) {
            presenters.remove(presenter)
        }
        if (presenters.isEmpty()) {
            subscription?.unsubscribe()
            subscription = null
        }
    }

    override fun onStateChange() {
        presenters.forEach { it.onStateChange(gameEngine.appStore.state) }
    }
}

interface View

interface Presenter {

    fun onStateChange(state: AppState)
}



