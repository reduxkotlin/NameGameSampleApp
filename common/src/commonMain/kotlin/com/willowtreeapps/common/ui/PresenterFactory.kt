package com.willowtreeapps.common.ui

import com.willowtreeapps.common.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.reduxkotlin.StoreSubscriber
import org.reduxkotlin.StoreSubscription
import kotlin.coroutines.CoroutineContext

/**
 * PresenterFactory that creates presenters for all views in the application.
 * Each view must attach/detach itself as it becomes visible/not visible.
 * Attaching returns a presenter to the view.
 * PresenterFactory subscribes to changes in state, and passes state to presenters.
 */
internal class PresenterFactory(private val gameEngine: GameEngine,
                                networkContext: CoroutineContext,
                                uiContext: CoroutineContext): CoroutineScope {

    private val timerThunks = TimerThunks(networkContext)
    private val networkThunks = NetworkThunks(networkContext)
    //    private val presenters = mutableSetOf<Presenter>()
    private var subscription: StoreSubscription? = null

    private val startPresenter by lazy { StartPresenter(gameEngine, networkThunks) }
    private val questionPresenter by lazy { QuestionPresenter(gameEngine, gameEngine.vibrateUtil, timerThunks) }
    private val gameResultsPresenter by lazy { GameResultsPresenter(gameEngine) }
    private val settingsPresenter by lazy { SettingsPresenter(gameEngine) }
    override val coroutineContext: CoroutineContext = uiContext + Job()

    fun <T : View<Presenter<*>>> attachView(view: T) {
        Logger.d("AttachView: $view", Logger.Category.LIFECYCLE)
        if (subscription == null) {
            subscription = gameEngine.appStore.subscribe(this::onStateChange)
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
        presenter.onStateChange()
    }

    fun detachView(view: View<*>) {
        Logger.d("DetachView: $view", Logger.Category.LIFECYCLE)
        if (view is StartView)
            startPresenter.detachView(view)
        if (view is QuestionView)
            questionPresenter.detachView(view)
        if (view is GameResultsView)
            gameResultsPresenter.detachView(view)
        if (view is SettingsView)
            settingsPresenter.detachView(view)

        if (hasAttachedViews()) {
            subscription!!()
            subscription = null
        }
    }

    private fun hasAttachedViews() = !startPresenter.isAttached() && !questionPresenter.isAttached() && !gameResultsPresenter.isAttached()

    private fun onStateChange() {
        launch {
            if (startPresenter.isAttached()) {
                startPresenter.onStateChange()
            }
            if (questionPresenter.isAttached()) {
                questionPresenter.onStateChange()
            }
            if (gameResultsPresenter.isAttached()) {
                gameResultsPresenter.onStateChange()
            }
            if (settingsPresenter.isAttached()) {
                settingsPresenter.onStateChange()
            }
        }
//        presenters.forEach { it.onStateChange(gameEngine.appStore.state) }
    }
}

interface View<TPresenter> {
    var presenter: TPresenter
}

abstract class Presenter<T : View<*>?> {
    var view: T? = null
    private var subscriber: StoreSubscriber? = null

    fun isAttached() = view != null

    open fun attachView(view: T) {
        Logger.d("Presenter attachView: $view", Logger.Category.LIFECYCLE)
        if (subscriber == null) {
            subscriber = makeSubscriber()
        }
        this.view = view
    }

    fun detachView(view: T) {
        Logger.d("Presenter DetachView: $view", Logger.Category.LIFECYCLE)
        if (this.view == view) {
            this.view = null
        }
    }

    abstract fun makeSubscriber(): StoreSubscriber

    fun onStateChange() {
        subscriber!!()
    }

    //on Android, this is when the view has been destroyed and must be recreated. (onConfig change, etc)
    //the state has not change, but the views must be set to the existing AppState
    abstract fun recreateView()

}
