package com.apilib.utils
import androidx.annotation.StringRes
import com.apilib.ApiApplication

object StringUtil {
    @JvmStatic
    fun getString(@StringRes messageId: Int): String = ApiApplication.context?.getString(messageId)
    ?: ""
}