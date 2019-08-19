package com.willowtreeapps.common.ui

import com.willowtreeapps.common.*
import com.willowtreeapps.common.external.*
import com.willowtreeapps.common.util.isAndroid
import org.reduxkotlin.middleware


typealias GameBaseView = ViewWithProvider<AppState>

//a Presenter typed to our app's State type for convenience
fun <V: GameBaseView> presenter(actions: PresenterBuilder<AppState, V>): Presenter<View, AppState> {
    return createGenericPresenter(actions) as Presenter<View, AppState>
}

fun <V: GameBaseView> presenterWithViewArg(actions: PresenterBuilderWithViewArg<AppState, V>): Presenter<View, AppState> {
    return createGenericPresenter(actions) as Presenter<View, AppState>
}

val startPresenter = presenter<StartView> {{
    withSingleField({ it.isLoadingItems }) {
        if (state.isLoadingItems) {
            showLoading()
        } else {
            hideLoading()
        }
    }

    withSingleField({ it.errorLoadingItems }) {
        showError(state.errorMsg)
    }
}}
