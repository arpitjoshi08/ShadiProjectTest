package com.apilib.service

import com.apilib.response.model.cardlist.CardListItemResponse
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("?results=10")
    fun fetchList(): Call<CardListItemResponse>

}