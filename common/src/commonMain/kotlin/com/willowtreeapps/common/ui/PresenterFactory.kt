package com.willowtreeapps.common

import com.beyondeye.reduks.*
import com.willowtreeapps.common.ui.*
import kotlin.coroutines.CoroutineContext

/**
 * PresenterFactory that creates presenters for all views in the application.
 * Each view must attach/detach itself as it becomes visible/not visible.
 * Attaching returns a presenter to the view.
 * PresenterFactory subscribes to changes in state, and passes state to presenters.
 */
internal class PresenterFactory(private val gameEngine: GameEngine, networkContext: CoroutineContext) : StoreSubscriber<AppState> {

    private val timerThunks = TimerThunks(networkContext, gameEngine.appStore)
    private val networkThunks = NetworkThunks(networkContext, gameEngine.appStore)
    //    private val presenters = mutableSetOf<Presenter>()
    private var subscription: StoreSubscription? = null

    private val startPresenter by lazy { StartPresenter(gameEngine.appStore, networkThunks) }
    private val questionPresenter by lazy { QuestionPresenter(gameEngine.appStore, gameEngine.vibrateUtil, timerThunks) }
    private val gameResultsPresenter by lazy { GameResultsPresenter(gameEngine.appStore) }
    private val settingsPresenter by lazy { SettingsPresenter(gameEngine.appStore) }

    fun <T : View<Presenter<*>>> attachView(view: T) {
        Logger.d("AttachView: $view")

        if (subscription == null) {
            subscription = gameEngine.appStore.subscribe(this)
        }
        //TODO find generic way to handle
        val presenter = when (view) {
            is StartView -> {
                startPresenter.attachView(view)
                startPresenter
            }
            is QuestionView -> {
                questionPresenter.attachView(view)
                questionPresenter
            }
            is GameResultsView -> {
                gameResultsPresenter.attachView(view)
                gameResultsPresenter
            }
            is SettingsView -> {
                settingsPresenter.attachView(view)
                settingsPresenter
            }
            else -> throw IllegalStateException("Screen $view not handled")
        }
        view.presenter = presenter
        presenter.onStateChange(gameEngine.appStore.state)
    }

    fun detachView(view: View<*>) {
        Logger.d("DetachView: $view")
        if (view is StartView)
            startPresenter.detachView(view)
        if (view is QuestionView)
            questionPresenter.detachView(view)
        if (view is GameResultsView)
            gameResultsPresenter.detachView(view)
        if (view is SettingsView)
            settingsPresenter.detachView(view)

        if (hasAttachedViews()) {
            subscription?.unsubscribe()
            subscription = null
        }
    }

    private fun hasAttachedViews() = !startPresenter.isAttached() && !questionPresenter.isAttached() && !gameResultsPresenter.isAttached()

    override fun onStateChange() {
        if (startPresenter.isAttached()) {
            startPresenter.onStateChange(gameEngine.appStore.state)
        }
        if (questionPresenter.isAttached()) {
            questionPresenter.onStateChange(gameEngine.appStore.state)
        }
        if (gameResultsPresenter.isAttached()) {
            gameResultsPresenter.onStateChange(gameEngine.appStore.state)
        }
        if (settingsPresenter.isAttached()) {
            gameResultsPresenter.onStateChange(gameEngine.appStore.state)
        }
//        presenters.forEach { it.onStateChange(gameEngine.appStore.state) }
    }
}

interface View<TPresenter> {
    var presenter: TPresenter
}

abstract class Presenter<T : View<*>?> {
    var view: T? = null
    var subscriber: StoreSubscriber<AppState>? = null

    fun isAttached() = view != null

    open fun attachView(view: T) {
        Logger.d("Presenter attachView: $view")
        subscriber = makeSubscriber()
        this.view = view
    }

    fun detachView(view: T) {
        Logger.d("Presenter DetachView: $view")
        if (this.view == view) {
            this.view = null
        }
    }

    abstract fun makeSubscriber(): StoreSubscriber<AppState>

    fun onStateChange(state: AppState) {
        subscriber?.onStateChange()
    }
}
