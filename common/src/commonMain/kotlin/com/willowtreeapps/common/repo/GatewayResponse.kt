package com.willowtreeapps.common.repo

import kotlinx.coroutines.delay


/**
 * Wrapper around Gateway responses that allows returning an error state with code and message.
 */
class GatewayResponse<T, out S> {
    val response: T?
    val errorResponse: S?
    val status: Int
    val message: String?

    constructor(response: T) {
        this.response = response
        this.status = 200
        this.message = null
        this.errorResponse = null
    }

    constructor(response: T? = null, status: Int, message: String) {
        this.errorResponse = null
        this.status = status
        this.message = message
        this.response = response
    }

    constructor(status: Int, errorResponse: S? = null, message: String) {
        this.errorResponse = errorResponse
        this.status = status
        this.message = message
        this.response = null
    }

    val isSuccessful: Boolean
        get() = response != null

    val isFailure: Boolean
        get() = response == null

    companion object {
        fun <T, S> createSuccess(response: T? = null, status: Int, message: String) =
                GatewayResponse<T, S>(response, status, message)

        fun <T, S> createError(error: S, status: Int, message: String) =
                GatewayResponse<T, S>(status, error, message)

    }
}

data class GenericError(val message: String)


/**
 * Retries a function that returns a GatewayResponse the given number of times, or throws the
 * given exception if there is not a success
 * @param numRetries maximum number of retries before the exception is thrown
 * @param retryWaitInMs time to wait until retrying.  This will double after each retry.
 *                      This will call Thread.sleep() while waiting.
 * @param f function that will be retried
 * @param ex exception that will be thrown if a success full response is not received
 * @return GatewayResponse will only return GatewayResponse object if it is successful
 */
suspend fun <R, E> retrySuccessOrThrow(numRetries: Int, retryWaitInMs: Long, ex: Exception, f: suspend () -> GatewayResponse<R, E>): GatewayResponse<R, E> {
    val response = f()
    return if (response.isSuccessful) {
        response
    } else if (response.isFailure && numRetries > 0) {
        delay(retryWaitInMs)
        retrySuccessOrThrow(numRetries - 1, retryWaitInMs * 2, ex, f)
    } else {
        throw ex
    }

}