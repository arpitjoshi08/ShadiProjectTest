package com.apilib.client.server.response

import com.google.gson.annotations.SerializedName


sealed class AppResponse<T> {
    abstract fun isSuccessful(): Boolean
}

open class ApiResponse<T>(
    var data: T
) : AppResponse<T>() {
    override fun isSuccessful(): Boolean {
        return data != null
    }
}

sealed class ErrorResponse<T> : AppResponse<T>() {
    override fun isSuccessful(): Boolean {
        return false
    }

    abstract fun getException(): Exception
}
