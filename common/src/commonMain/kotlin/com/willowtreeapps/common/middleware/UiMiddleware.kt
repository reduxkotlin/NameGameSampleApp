package com.willowtreeapps.common.middleware

import com.willowtreeapps.common.*
import com.willowtreeapps.common.util.debounce
import com.willowtreeapps.common.util.isAndroid
import org.reduxkotlin.Dispatcher
import org.reduxkotlin.Middleware
import org.reduxkotlin.Store
import org.reduxkotlin.middleware
import kotlin.coroutines.CoroutineContext

fun uiMiddleware(networkThunks: NetworkThunks,
                 timerThunks: TimerThunks,
                 uiContext: CoroutineContext) = middleware<AppState> { store, next, action ->
    val dispatch = store.dispatch
    val debouncedNamePicked: ((String) -> Unit) =
            debounce(400, uiContext) { name ->
                Logger.d("choose name: $name")
                dispatch(Actions.NamePickedAction(name))
                dispatch(timerThunks.stopTimer())
                //view?.closeMic()
            }
    Unit

    when (action) {
        is UiActions.StartGameTapped -> {
            dispatch(Actions.ResetGameStateAction())
            dispatch(networkThunks.fetchItems(store.state.settings.categoryId, store.state.settings.numQuestions))
        }
        is UiActions.SettingsTapped -> dispatch(NavigationActions.NavigateTo(Screen.SETTINGS))
        is UiActions.VibrateAction -> {

        }
        is UiActions.NextQuestionDelayed ->
            dispatch(timerThunks.dispatchDelayed(4000, Actions.NextQuestionAction()))

        is UiActions.NextTapped -> {
            dispatch(timerThunks.cancelDelayed())
            dispatch(Actions.NextQuestionAction())
        }
        is UiActions.NamePicked -> {
            //check for android here, because performance is a bit different and better UX without debounce
            if (isAndroid()) {
                dispatch(Actions.NamePickedAction(action.name))
                dispatch(timerThunks.stopTimer())
//                    closeMic()
            } else {
                //TODO fix debounce middle func
                debouncedNamePicked(action.name)
            }
        }
        is UiActions.ProfileImageDidShow -> {
            if (!store.state.isCurrentQuestionAnswered()) {
                dispatch(timerThunks.startCountDownTimer(5))
                if (store.state.settings.microphoneMode) {
                    //TODO
//                        view?.openMic()
                }
            }
            next(action)
        }
        is UiActions.EndGameTapped -> {
            dispatch(Actions.GameCompleteAction())
            dispatch(NavigationActions.NavigateTo(Screen.GAME_COMPLETE))
        }
        is UiActions.StartOverTapped -> {
            dispatch(Actions.ResetGameStateAction())
            dispatch(NavigationActions.NavigateTo(Screen.START))
        }
        is UiActions.CategoryPicked -> dispatch(Actions.ChangeCategorySettingsAction(action.categoryId))
        is UiActions.NumQuestionsPicked -> dispatch(Actions.ChangeNumQuestionsSettingsAction(action.numQuestions))
        is UiActions.MicrophoneModeToggled -> dispatch(Actions.ChangeMicrophoneModeSettingsAction(action.enabled))
        else -> next(action)
    }
}

class UiActions {
    class StartGameTapped
    class SettingsTapped
    data class CategoryPicked(val categoryId: QuestionCategoryId)
    data class NumQuestionsPicked(val numQuestions: Int)
    data class MicrophoneModeToggled(val enabled: Boolean)
    class StartOverTapped
    class VibrateAction
    class NextQuestionDelayed
    class NextTapped
    class EndGameTapped
    class ProfileImageDidShow
    data class NamePicked(val name: String)
}

