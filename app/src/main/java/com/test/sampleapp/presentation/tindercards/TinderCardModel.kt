package com.test.sampleapp.presentation.tindercards

import com.apilib.client.server.APIContract
import com.apilib.dispatchers.DispatcherProvider
import com.apilib.dispatchers.DispatcherProviderImpl
import com.apilib.response.model.cardlist.CardListItemResponse
import com.apilib.result.APIResult
import com.test.sampleapp.core.plateform.ActionLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TinderCardModel(
    val apiContract: APIContract,
    val dispatcher: DispatcherProvider = DispatcherProviderImpl()
) {
    var cardListResponse = ActionLiveData<CardListItemResponse>()
    var errorMessageResponse = ActionLiveData<String>()
    var showOrHideProgress = ActionLiveData<Boolean>()

    fun fetchTinderList() {
        GlobalScope.launch(dispatcher.io) {
            showOrHideProgress.postValue(true)
            val result = apiContract.fetchList()
            withContext(dispatcher.main) {
                when (result) {
                    is APIResult.Success -> {
                        showOrHideProgress.postValue(false)
                        cardListResponse.postValue(result.response)
                    }
                    is APIResult.Failure -> {
                        showOrHideProgress.postValue(false)
                        errorMessageResponse.postValue(result.details.message)
                    }
                }
            }
        }
    }
}