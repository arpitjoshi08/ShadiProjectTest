package com.apilib.response.model.cardlist

data class CardListItemResponse(
    val info: Info,
    val results: List<CardList>
)