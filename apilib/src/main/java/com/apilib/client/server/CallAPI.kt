package com.apilib.client.server

import com.apilib.response.model.ErrorResponse
import com.apilib.response.model.cardlist.CardListItemResponse
import com.apilib.result.APIError
import com.apilib.result.APIErrorType
import com.apilib.result.APIResult
import com.apilib.service.UserService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Retrofit
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.awaitResult
import java.util.HashMap

class CallAPI(retrofit: Retrofit) : APIContract() {
    private val userService = retrofit.create(UserService::class.java)

    override suspend fun fetchList(): APIResult<CardListItemResponse> {
        return userService.fetchList().apiResult()
    }

    // region - Response Parsing
    private suspend fun <T : Any> Call<T>.apiResult(): APIResult<T> {
        val result = awaitResult()
        return when (result) {
            is Result.Ok -> APIResult.Success(result.value)
            is Result.Error -> {
                APIResult.Failure(errorFromHttpException(result.exception))
            }
            is Result.Exception -> {
                APIResult.Failure(errorFromAnyException(result.exception))
            }
        }
    }

    private fun errorFromAnyException(exception: Throwable): APIError {
        return APIError(APIErrorType.General, "", exception, "" + exception.message)
    }

    private fun errorFromHttpException(exception: HttpException): APIError {
        val errorBody = exception.response().errorBody()?.string()
      return if (errorBody != null) {
            var errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
          if(errorResponse.message.isNullOrEmpty()){
              return APIError(APIErrorType.General, errorResponse.code, exception, "" + errorResponse.code)
          }else
          {
              return APIError(APIErrorType.General, errorResponse.code, exception, "" + errorResponse.message)
          }
        } else {
          return APIError(APIErrorType.General, "-1", exception, "" + exception.message)
        }
    }

    private val errorMapType = object : TypeToken<HashMap<String, Any>>() {}.type
    private val decoder = Gson()

}

// endregion