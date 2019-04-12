package com.willowtreeapps.common.ui

import com.beyondeye.reduks.SelectorSubscriberFn
import com.beyondeye.reduks.Store
import com.willowtreeapps.common.Actions
import com.willowtreeapps.common.AppState
import com.willowtreeapps.common.NetworkThunks
import com.willowtreeapps.common.Presenter


class StartPresenter(val store: Store<AppState>,
                     private val networkThunks: NetworkThunks) : Presenter<StartView>() {

    override fun makeSubscriber() = SelectorSubscriberFn(store) {
        withSingleField({ it.isLoadingItems }) {
            if (state.isLoadingItems) {
                view?.showLoading()
            } else {
                view?.hideLoading()
            }
        }

        withSingleField({ it.errorLoadingItems }) {
            view?.showError(state.errorMsg)
        }
    }

    fun startGame() {
        store.dispatch(Actions.ResetGameStateAction())
        store.dispatch(networkThunks.fetchItems(store.state.settings.categoryId))
    }

    fun settingsTapped() {
        store.dispatch(Actions.SettingsTappedAction())
    }
}