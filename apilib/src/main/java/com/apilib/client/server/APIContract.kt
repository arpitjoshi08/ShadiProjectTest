package com.apilib.client.server

import com.apilib.response.model.cardlist.CardListItemResponse
import com.apilib.result.APIResult


abstract class APIContract {
    abstract suspend fun fetchList(): APIResult<CardListItemResponse>

}