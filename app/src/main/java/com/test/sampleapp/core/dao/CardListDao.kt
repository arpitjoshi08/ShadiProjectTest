package com.test.sampleapp.core.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.apilib.response.model.cardlist.CardList


@Dao
interface CardListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addList(values: List<CardList>)

}