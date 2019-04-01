package com.willowtreeapps.common

import com.beyondeye.reduks.*
import com.willowtreeapps.common.boundary.toGameResultsViewState
import com.willowtreeapps.common.boundary.toQuestionViewState
import com.willowtreeapps.common.util.VibrateUtil
import com.willowtreeapps.common.view.GameResultsScreen
import com.willowtreeapps.common.view.QuestionScreen
import com.willowtreeapps.common.view.StartScreen
import com.willowtreeapps.common.view.View
import kotlin.coroutines.CoroutineContext

/**
 * PresenterFactory that creates presenters for all views in the application.
 * Each view must attach/detach itself as it becomes visible/not visible.
 * Attaching returns a presenter to the view.
 */
class PresenterFactory(private val gameEngine: GameEngine, networkContext: CoroutineContext) : StoreSubscriber<AppState> {

    init {
        gameEngine.appStore.subscribe(this)
    }

    private val networkThunks = NetworkThunks(networkContext, gameEngine.appStore)
    private val presenters = mutableSetOf<Presenter>()


    fun attachView(view: View): Presenter {
        val presenter = when (view) {
            is StartScreen -> StartPresenter(view, gameEngine.appStore, networkThunks)
            is QuestionScreen -> QuestionPresenter(view, gameEngine.appStore, gameEngine.vibrateUtil)
            is GameResultsScreen -> GameResultsPresenter(view, gameEngine.appStore)
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
    }

    override fun onStateChange() {
        presenters.forEach { it.onStateChange(gameEngine.appStore.state) }
    }
}

interface Presenter {

    fun onStateChange(state: AppState)
}

class StartPresenter(view: StartScreen,
                     val store: Store<AppState>,
                     private val networkThunks: NetworkThunks) : Presenter {
    private val subscriber = StoreSubscriberBuilderFn<AppState> { store ->
        StoreSubscriberFn {
            val state = store.state
            if (state.isLoadingProfiles) {
                view.showLoading()
            } else {
                view.hideLoading()
            }
        }
    }.build(store)

    override fun onStateChange(state: AppState) {
        subscriber.onStateChange()
    }

    fun startGame() {
        store.dispatch(Actions.ResetGameStateAction())
        store.dispatch(networkThunks.fetchProfiles())
    }
}

class QuestionPresenter(view: QuestionScreen,
                        val store: Store<AppState>,
                        private val vibrateUtil: VibrateUtil) : Presenter {
    private val subscriber = StoreSubscriberBuilderFn<AppState> { store ->
        val selBuilder = SelectorBuilder<AppState>()
        val profileSelector = selBuilder.withSingleField { currentQuestion?.profileId?.id ?: Any() }
        StoreSubscriberFn {
            val state = store.state
            profileSelector.onChangeIn(state) {
                view.showProfile(state.toQuestionViewState())
            }

            if (state.isGameComplete()) {
                if (state.waitingForNextQuestion) {
                    when (state.currentQuestion?.status) {
                        Question.Status.CORRECT -> {
                            view.showCorrectAnswerEndGame(state.toQuestionViewState())
                        }
                        Question.Status.INCORRECT -> {
                            vibrateUtil.vibrate()
                            view.showWrongAnswerEndGame(state.toQuestionViewState())
                        }
                        Question.Status.UNANSWERED -> throw IllegalStateException("Question status cannot be Unanswered when waiting for next round == true")
                    }
                }
            } else {
                if (state.waitingForNextQuestion) {
                    when (state.currentQuestion?.status) {
                        Question.Status.CORRECT -> {
                            view.showCorrectAnswer(state.toQuestionViewState())
                        }
                        Question.Status.INCORRECT -> {
                            vibrateUtil.vibrate()
                            view.showWrongAnswer(state.toQuestionViewState())
                        }
                        Question.Status.UNANSWERED -> throw IllegalStateException("Question status cannot be Unanswered when waiting for next round == true")
                    }
                }
            }
        }
    }.build(store)

    override fun onStateChange(state: AppState) {
        subscriber.onStateChange()
    }

    fun namePicked(name: String) {
        store.dispatch(Actions.NamePickedAction(name))
    }

    fun nextTapped() {
        store.dispatch(Actions.NextQuestionAction())
    }

    fun endGameTapped() {
        store.dispatch(Actions.GameCompleteAction())
    }

    fun onBackPressed() {
        store.dispatch(Actions.StartOverAction())
    }

}

class GameResultsPresenter(val view: GameResultsScreen, val store: Store<AppState>) : Presenter {

    override fun onStateChange(state: AppState) {
        view.showResults(state.toGameResultsViewState())
    }

    fun startOverTapped() {
        store.dispatch(Actions.StartOverAction())
    }

    fun onBackPressed() {
        TODO("Handle back press from Game Results")
    }
}
