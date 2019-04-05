package com.willowtreeapps.common.ui

import com.beyondeye.reduks.SelectorSubscriberFn
import com.beyondeye.reduks.Store
import com.beyondeye.reduks.StoreSubscriberBuilderFn
import com.beyondeye.reduks.StoreSubscriberFn
import com.willowtreeapps.common.Actions
import com.willowtreeapps.common.AppState
import com.willowtreeapps.common.NetworkThunks
import com.willowtreeapps.common.Presenter


class StartPresenter(val store: Store<AppState>,
                     private val networkThunks: NetworkThunks) : Presenter<StartView>() {

    override fun makeSubscriber() = SelectorSubscriberFn(store) {
        withSingleField({ it.isLoadingProfiles }) {
            if (state.isLoadingProfiles) {
                view?.showLoading()
            } else {
                view?.hideLoading()
            }
        }

        withSingleField({ it.errorLoadingProfiles }) {
            view?.showError(state.errorMsg)
        }
    }

    fun startGame() {
        store.dispatch(Actions.ResetGameStateAction())
        store.dispatch(networkThunks.fetchProfiles())
    }
}