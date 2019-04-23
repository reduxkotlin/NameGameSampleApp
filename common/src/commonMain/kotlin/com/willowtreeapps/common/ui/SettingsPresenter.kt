package com.willowtreeapps.common.ui

import com.beyondeye.reduks.SelectorSubscriberFn
import com.willowtreeapps.common.*
import com.willowtreeapps.common.boundary.toViewState

class SettingsPresenter(private val engine: GameEngine): Presenter<SettingsView>() {

    override fun recreateView() {
        view?.showSettings(engine.state.settings.toViewState())
    }

    override fun makeSubscriber() = SelectorSubscriberFn(engine.appStore){
        withSingleField({ it.settings}) { view?.showSettings(state.settings.toViewState())}
    }

    fun numQuestionsChanged(numQuestions: Int) {
        engine.dispatch(Actions.ChangeNumQuestionsSettingsAction(numQuestions))
    }

    fun categoryChanged(categoryId: QuestionCategoryId) {
        engine.dispatch(Actions.ChangeCategorySettingsAction(categoryId))
    }

    fun microphoneModeChanged(enabled: Boolean) {
        if (enabled) {
            view?.askForMicPermissions()
        } else {
            engine.dispatch(Actions.ChangeMicrophoneModeSettingsAction(false))
        }
    }

    fun microphonePermissionGranted() {
        engine.dispatch(Actions.ChangeMicrophoneModeSettingsAction(true))
    }

    fun microphonePermissionDenied() {
        engine.dispatch(Actions.ChangeMicrophoneModeSettingsAction(false))
    }
}