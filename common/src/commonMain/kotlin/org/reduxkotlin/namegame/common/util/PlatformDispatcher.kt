package org.reduxkotlin.namegame.common.util

import kotlinx.coroutines.CoroutineDispatcher

/**
 * A dispatchers for coroutines.  This will likely be removed once multithreaded coroutines
 * for Kotlin Native are supported.  For now each platform has an implementation.
 */
expect object PlatformDispatcher: CoroutineDispatcher