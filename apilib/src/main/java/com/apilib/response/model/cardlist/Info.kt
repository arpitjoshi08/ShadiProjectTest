package com.apilib.response.model.cardlist

data class Info(
    val page: Int,
    val results: Int,
    val seed: String,
    val version: String
)