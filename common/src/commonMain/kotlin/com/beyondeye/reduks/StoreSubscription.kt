package com.beyondeye.reduks

typealias StoreSubscription = () -> Unit

fun StoreSubscription.addToList(subscriptions:MutableList<StoreSubscription>) {
    subscriptions.add(this)
}

fun MutableList<StoreSubscription>.unsubscribe() {
    this.forEach { it() }
    this.clear()
}