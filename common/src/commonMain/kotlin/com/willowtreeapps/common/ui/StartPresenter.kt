package com.willowtreeapps.common.ui

import com.beyondeye.reduks.Store
import com.beyondeye.reduks.StoreSubscriberBuilderFn
import com.beyondeye.reduks.StoreSubscriberFn
import com.willowtreeapps.common.Actions
import com.willowtreeapps.common.AppState
import com.willowtreeapps.common.NetworkThunks
import com.willowtreeapps.common.Presenter


class StartPresenter(view: StartView,
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