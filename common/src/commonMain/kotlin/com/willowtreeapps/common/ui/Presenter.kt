package com.willowtreeapps.common.ui

import com.willowtreeapps.common.AppState
import org.reduxkotlin.*


typealias GameBaseView = ViewWithProvider<AppState>

//a Presenter typed to our app's State type for convenience
fun <V: GameBaseView> presenter(actions: PresenterBuilder<AppState, V>): Presenter<View, AppState> {
    @Suppress("UNCHECKED_CAST")
    return createGenericPresenter(actions) as Presenter<View, AppState>
}

fun <V: GameBaseView> presenterWithViewArg(actions: PresenterBuilderWithViewArg<AppState, V>): Presenter<View, AppState> {
    @Suppress("UNCHECKED_CAST")
    return createGenericPresenter(actions) as Presenter<View, AppState>
}
