package com.willowtreeapps.common.util

import kotlin.math.abs
import kotlin.random.Random


val random = Random(TimeUtil.systemTimeMs())

/**
 * Take N distict elements from the list.  Distinct is determined by a comparision of objects in the
 * list.
 * @throws IllegalStateException when n > number of distinct elements.
 * @return New immutable list containing N random elements from the given List.
 */
fun <T> List<T>.takeRandomDistinct(n: Int): List<T> {
    val newList = mutableListOf<T>()
    val uniqueItems = this.distinctBy { it }
    if (uniqueItems.size < n) {
        throw IllegalStateException("Unable to get $n unique random elements from given list.")
    }
    while (newList.size < n) {
        val randomIndex = abs(random.nextInt() % uniqueItems.size)
        val next = uniqueItems[randomIndex]
        if (newList.contains(next)) {
            continue
        } else {
            newList.add(next)
        }
    }
    return newList.toList()
}


fun <T> List<T>.takeRandom(): T =
        this[random.nextInt(this.size - 1)]