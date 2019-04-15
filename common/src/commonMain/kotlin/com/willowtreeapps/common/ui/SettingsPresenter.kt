package com.willowtreeapps.common.ui

import com.beyondeye.reduks.SelectorSubscriberFn
import com.beyondeye.reduks.Store
import com.willowtreeapps.common.Actions
import com.willowtreeapps.common.AppState
import com.willowtreeapps.common.Presenter
import com.willowtreeapps.common.QuestionCategoryId
import com.willowtreeapps.common.boundary.toViewState

class SettingsPresenter(val store: Store<AppState>): Presenter<SettingsView>() {

    override fun makeSubscriber() = SelectorSubscriberFn(store){
        withSingleField({ it.settings}) { view?.showSettings(state.settings.toViewState())}
    }

    fun numQuestionsChanged(numQuestions: Int) {
        store.dispatch(Actions.ChangeNumQuestionsSettingsAction(numQuestions))
    }

    fun categoryChanged(categoryId: QuestionCategoryId) {
        store.dispatch(Actions.ChangeCategorySettingsAction(categoryId))
    }
}