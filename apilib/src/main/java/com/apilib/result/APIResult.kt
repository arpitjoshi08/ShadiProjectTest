package com.apilib.result

sealed class APIResult<T> {
    class Failure<T>(val details: APIError) : APIResult<T>()
    class Success<T>(val response: T) : APIResult<T>()

    val isSuccess:Int
        get() = 200

    val value: T?
        get() = (this as? Success)?.response

    val error: APIError?
        get() = (this as? Failure)?.details
}

sealed class APIErrorType {
    object General : APIErrorType()
}

data class APIError(
    val type: APIErrorType,
    val code: String,
    val thrownCause: Throwable?,
    override val message: String?
) : Exception(message ?: thrownCause?.message) {

}