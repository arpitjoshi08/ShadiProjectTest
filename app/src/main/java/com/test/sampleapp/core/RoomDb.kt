package com.test.sampleapp.core

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.apilib.response.model.cardlist.CardList
import com.test.sampleapp.AppClass
import com.test.sampleapp.core.dao.CardListDao

@Database(entities = [CardList::class], version = 1)
abstract class RoomDb : RoomDatabase() {

    // region - DAO accessors

    abstract fun cardListDao():CardListDao
    // endregion

    companion object {
        val instance: RoomDb by lazy {
            Room.databaseBuilder(
                    AppClass.instance.applicationContext,
                    RoomDb::class.java, "Test.db").fallbackToDestructiveMigration()
                    .build()
        }
    }
}